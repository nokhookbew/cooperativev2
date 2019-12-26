package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.models.Item;
import bewvy.se.cooperativev2.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<Item> showData() {
        return itemService.getAllItem();
    }

    @GetMapping("/{id}")
    public Optional<Item> showDataById(@PathVariable int id) {
        return itemService.getById(id);
    }

    @GetMapping("/shop")
    public List<Item> showDataByShopName(@RequestBody  Item item) {
        return itemService.getItemByShop(item.getShopName());
    }

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        itemService.creatItem(item);
        return ResponseEntity.ok().body(item);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable int id) {
        Optional<Item> item = itemService.getById(id);
        itemService.deleteItemById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PutMapping("/edit")
    public ResponseEntity<Optional<Item>> editShop(@PathVariable int id, @RequestBody Item itemDetails) {
        final Optional<Item> itemUpdated = itemService.updateItem(id, itemDetails);
        return ResponseEntity.ok(itemUpdated);
    }
}
