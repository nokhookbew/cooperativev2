package bewvy.se.cooperativev2.services;

import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import bewvy.se.cooperativev2.models.Shop;
import bewvy.se.cooperativev2.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ShopService {
    @Autowired
    ShopRepository shopRepository;

    public List<Shop> getAllShop(){
        return shopRepository.findAll();
    }

    public Optional<Shop> getById(int id){
        return shopRepository.findById(id);
    }

    public boolean deleteShopById(int id) {
        shopRepository.deleteById(id);
        return true;
    }

    public Shop creatShop(Shop shop){
        shopRepository.save(shop);
        return shop;
    }

    public Optional<Shop> updateShop(int id, Shop shopDetails) throws ResourceNotFoundException {
        Optional<Shop> shop = Optional.ofNullable(shopRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("shop not found on id: " + id)));
        if (!shop.isPresent()) {
            return shop;
        }
        shopDetails.setId(id);
        return Optional.of(shopRepository.save(shopDetails));
    }
}
