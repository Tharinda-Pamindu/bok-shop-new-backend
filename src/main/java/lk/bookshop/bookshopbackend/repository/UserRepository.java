package lk.bookshop.bookshopbackend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import lk.bookshop.bookshopbackend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}