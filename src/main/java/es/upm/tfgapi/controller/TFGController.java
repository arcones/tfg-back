package es.upm.tfgapi.controller;

import java.util.List;
import java.util.Optional;

import es.upm.tfgapi.model.TFG;
import es.upm.tfgapi.repository.TFGRepository;
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

    @CrossOrigin
    @PutMapping("/tfgs/{tfgId}/accept")
    public ResponseEntity<TFG> acceptTFGRequest(@PathVariable("tfgId") long tfgId) {
        try {
            Optional<TFG> _tfg = tfgRepository.findById(tfgId);
            if (_tfg.isPresent()) {
                TFG tfg = _tfg.get();
                TFG updatedTFG = new TFG(tfgId, tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "INIT_APPROVED");
                tfgRepository.save(updatedTFG);
                return new ResponseEntity<>(updatedTFG, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/tfgs/{tfgId}/reject")
    public ResponseEntity<TFG> rejectTFGRequest(@PathVariable("tfgId") long tfgId) {
        try {
            Optional<TFG> _tfg = tfgRepository.findById(tfgId);
            if (_tfg.isPresent()) {
                TFG tfg = _tfg.get();
                TFG updatedTFG = new TFG(tfgId, tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "INIT_REJECTED");
                tfgRepository.save(updatedTFG);
                return new ResponseEntity<>(updatedTFG, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/tfgs/{tfgId}/readRequest")
    public ResponseEntity<TFG> requestTFGRead(@PathVariable("tfgId") long tfgId) {
        try {
            Optional<TFG> _tfg = tfgRepository.findById(tfgId);
            if (_tfg.isPresent()) {
                TFG tfg = _tfg.get();
                TFG updatedTFG = new TFG(tfgId, tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "READ_REQUESTED");
                tfgRepository.save(updatedTFG);
                return new ResponseEntity<>(updatedTFG, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/tfgs/{tfgId}/acceptRead")
    public ResponseEntity<TFG> acceptTFGRead(@PathVariable("tfgId") long tfgId) {
        try {
            Optional<TFG> _tfg = tfgRepository.findById(tfgId);
            if (_tfg.isPresent()) {
                TFG tfg = _tfg.get();
                TFG updatedTFG = new TFG(tfgId, tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "READ_APPROVED");
                tfgRepository.save(updatedTFG);
                return new ResponseEntity<>(updatedTFG, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PutMapping("/tfgs/{tfgId}/rejectRead")
    public ResponseEntity<TFG> rejectFGRead(@PathVariable("tfgId") long tfgId) {
        try {
            Optional<TFG> _tfg = tfgRepository.findById(tfgId);
            if (_tfg.isPresent()) {
                TFG tfg = _tfg.get();
                TFG updatedTFG = new TFG(tfgId, tfg.getTitle(), tfg.getStudentId(), tfg.getDirectorId(), "READ_REJECTED");
                tfgRepository.save(updatedTFG);
                return new ResponseEntity<>(updatedTFG, HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}