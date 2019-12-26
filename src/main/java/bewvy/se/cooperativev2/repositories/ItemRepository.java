package bewvy.se.cooperativev2.repositories;


import bewvy.se.cooperativev2.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(value = "SELECT * FROM item WHERE shop_name = ?", nativeQuery = true)
    List<Item> findItemsByShopName(String shopName);
}
