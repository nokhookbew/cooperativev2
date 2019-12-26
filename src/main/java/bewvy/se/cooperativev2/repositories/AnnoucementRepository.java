package bewvy.se.cooperativev2.repositories;

import bewvy.se.cooperativev2.models.Annoucement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnnoucementRepository extends JpaRepository<Annoucement, Integer> {
    @Query(value = "SELECT * FROM annoucement WHERE shop_name = ?", nativeQuery = true)
    List<Annoucement> findAnnoucementsByShopNameByShopName(String shopName);
}
