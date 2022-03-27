package es.upm.tfgback.controller;

import java.util.List;
import java.util.Optional;

import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.TFGRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TFGController {

    final TFGRepository tfgRepository;

    public TFGController(TFGRepository tfgRepository) {
        this.tfgRepository = tfgRepository;
    }

    //TODO borrar controladores que no se usen

    @CrossOrigin
    @GetMapping("/tfgs")
    public ResponseEntity<List<TFG>> getAllTFGs() {
        try {
            List<TFG> tfgs = tfgRepository.findAll();
            if (tfgs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tfgs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/tfgs/students/{studentId}")
    public ResponseEntity<List<TFG>> getTFGByAuthor(@PathVariable("studentId") long studentId) {
        try {
            List<TFG> tfgs = tfgRepository.findByStudentId(studentId);
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

    @PostMapping("/tfgs")
    public ResponseEntity<TFG> createTFG(@RequestBody TFG tfg) {
        try {
            TFG _tfg = tfgRepository.save(new TFG(tfg.getStudentId(), tfg.getTitle()));
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
            Optional<TFG> tfgData = tfgRepository.findById(id);
            if (tfgData.isPresent()) {
                tfgRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}