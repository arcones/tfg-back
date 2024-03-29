package es.upm.tfgapi.repository;

import es.upm.tfgapi.model.TFG;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TFGRepository extends JpaRepository<TFG, Long> {
    List<TFG> findByStudentId(long studentId);
    List<TFG> findByDirectorId(long directorId);
}