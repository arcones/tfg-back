package es.upm.tfgapi.repository;

import es.upm.tfgapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
    Optional<User> findById(long id);
    List<User> findByRole(String role);
}