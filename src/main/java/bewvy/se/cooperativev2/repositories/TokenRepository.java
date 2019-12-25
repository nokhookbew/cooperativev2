package bewvy.se.cooperativev2.repositories;

import bewvy.se.cooperativev2.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
}
