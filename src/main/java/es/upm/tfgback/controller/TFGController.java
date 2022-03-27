package es.upm.tfgback.controller;

import java.util.List;

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

    @CrossOrigin
    @GetMapping("/tfgs/students/{studentId}")
    public ResponseEntity<List<TFG>> getTFGByStudentId(@PathVariable("studentId") long studentId) {
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

    @CrossOrigin
    @GetMapping("/tfgs/director/{directorId}")
    public ResponseEntity<List<TFG>> getTFGByDirectorId(@PathVariable("directorId") long directorId) {
        try {
            List<TFG> tfgs = tfgRepository.findByDirectorId(directorId);
            if (tfgs.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tfgs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping("/tfgs")
    public ResponseEntity<TFG> createTFG(@RequestBody TFG tfg) {
        try {
            TFG _tfg = tfgRepository.save(new TFG(tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "INIT_REQUESTED"));
            return new ResponseEntity<>(_tfg, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}