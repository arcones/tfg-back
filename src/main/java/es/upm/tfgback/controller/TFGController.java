package es.upm.tfgback.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.TFGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TFGController {

    @Autowired
    TFGRepository tfgRepository;

    @GetMapping("/tfgs")
    public ResponseEntity<List<TFG>> getAllTFGs(@RequestParam(required = false) String title) {
        try {
            List<TFG> tfgs = new ArrayList<TFG>();
            if (title == null)
                tfgs.addAll(tfgRepository.findAll());
            else {
                tfgs.addAll(tfgRepository.findByTitleContaining(title));
            }
            if (tfgs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tfgs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tfgs/{id}")
    public ResponseEntity<TFG> getTFGById(@PathVariable("id") long id) {
        Optional<TFG> tfgData = tfgRepository.findById(id);
        return tfgData.map(tfg -> new ResponseEntity<>(tfg, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/tfgs") // TODO no autoincrementa la secuencia
    public ResponseEntity<TFG> createTFG(@RequestBody TFG tfg) {
        try {
            TFG _tfg = tfgRepository.save(new TFG(tfg.getTitle()));
            return new ResponseEntity<>(_tfg, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tfgs/{id}")
    public ResponseEntity<TFG> updateTFG(@PathVariable("id") long id, @RequestBody TFG tfg) {
        Optional<TFG> tfgData = tfgRepository.findById(id);
        if (tfgData.isPresent()) {
            TFG _tfg = tfgData.get();
            _tfg.setTitle(tfg.getTitle());
            return new ResponseEntity<>(tfgRepository.save(_tfg), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tfgs/{id}")
    public ResponseEntity<HttpStatus> deleteTFG(@PathVariable("id") long id) {
        try {
            tfgRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}