package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.models.Shop;
import bewvy.se.cooperativev2.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping
    public List<Shop> showData() {
        return shopService.getAllShop();
    }

    @GetMapping("/{id}")
    public Optional<Shop> showDataById(@PathVariable int id) {
        return shopService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Shop> createShop(@RequestBody Shop shop) {
        shopService.creatShop(shop);
        return ResponseEntity.ok().body(shop);
    }

    @PutMapping("/edit")
    public ResponseEntity<Optional<Shop>> editShop(@PathVariable int id, @RequestBody Shop shopDetails) {
        final Optional<Shop> shopUpdated = shopService.updateShop(id, shopDetails);
        return ResponseEntity.ok(shopUpdated);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteShop(@PathVariable int id) {
        Optional<Shop> shop = shopService.getById(id);
        shopService.deleteShopById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
