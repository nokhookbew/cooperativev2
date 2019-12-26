package bewvy.se.cooperativev2.services;

import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import bewvy.se.cooperativev2.models.Item;
import bewvy.se.cooperativev2.models.Shop;
import bewvy.se.cooperativev2.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }

    public Optional<Item> getById(int id){
        return itemRepository.findById(id);
    }

    public boolean deleteItemById(int id) {
        itemRepository.deleteById(id);
        return true;
    }

    public Item creatItem(Item item){
        itemRepository.save(item);
        return item;
    }

    public List<Item> getItemByShop(String shopName) {
        return itemRepository.findItemsByShopName(shopName);
    }

    public Optional<Item> updateItem(int id, Item itemDetails) throws ResourceNotFoundException {
        Optional<Item> item = Optional.ofNullable(itemRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("shop not found on id: " + id)));
        if (!item.isPresent()) {
            return item;
        }
        itemDetails.setId(id);
        return Optional.of(itemRepository.save(itemDetails));
    }
}
