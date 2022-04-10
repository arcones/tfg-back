package es.upm.tfgback.controller;

import java.util.List;

import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.TFGRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "TFG", description = "The TFG controller")
@RequestMapping("/api")
public class TFGController {

    final TFGRepository tfgRepository;

    public TFGController(TFGRepository tfgRepository) {
        this.tfgRepository = tfgRepository;
    }

    @CrossOrigin
    @Operation(summary = "Get TFG requests by student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "There are TFG requests for the student"),
            @ApiResponse(responseCode = "204", description = "There aren't TFG requests for the student")
    })
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
    @Operation(summary = "Get TFG requests by director")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "There are TFG requests for the director"),
            @ApiResponse(responseCode = "204", description = "There aren't TFG requests for the director")
    })
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