package es.upm.tfgback.repository;

import es.upm.tfgback.model.Student;
import es.upm.tfgback.model.TFG;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {}