package es.upm.tfgback.controller;

import es.upm.tfgback.model.Student;
import es.upm.tfgback.model.TFG;
import es.upm.tfgback.repository.StudentRepository;
import es.upm.tfgback.repository.TFGRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    final StudentRepository studentRepository;
    final TFGRepository tfgRepository;

    public StudentController(StudentRepository studentRepository, TFGRepository tfgRepository) {
        this.studentRepository = studentRepository;
        this.tfgRepository = tfgRepository;
    }

    @CrossOrigin
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentRepository.findAll();
            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @GetMapping("/students/login")
    public ResponseEntity<Student> login(@RequestHeader("User") String user, @RequestHeader("Password") String password) {
        try {
            Optional<Student> studentData = studentRepository.findByName(user);
            if (studentData.isPresent() && Objects.equals(studentData.get().getPassword(), password)) {
                return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
        Optional<Student> studentData = studentRepository.findById(id);
        return studentData.map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        try {
            Student _student = studentRepository.save(new Student(student.getName(), student.getPassword()));
            return new ResponseEntity<>(_student, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
        Optional<Student> studentData = studentRepository.findById(id);
        if (studentData.isPresent()) {
            Student _student = studentData.get();
            _student.setName(student.getName());
            return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        try {
            Optional<Student> studentData = studentRepository.findById(id);
            if (studentData.isPresent()) {
                List<TFG> tfgs = tfgRepository.findByStudentId(id);
                if (tfgs.isEmpty()) {
                    studentRepository.deleteById(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } else {
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}