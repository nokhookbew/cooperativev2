package bewvy.se.cooperativev2.repositories;

import bewvy.se.cooperativev2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<User> findByEmailAddress(String email);

    @Query(value = "SELECT * FROM users WHERE tokens = ?1", nativeQuery = true)
    Optional<User> findUserByToken(String token);
}

