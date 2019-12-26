package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.models.Annoucement;
import bewvy.se.cooperativev2.services.AnnoucementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/annouce")
public class AnnouceController {

    @Autowired
    AnnoucementService annoucementService;

    @PostMapping("/create")
    public ResponseEntity<Annoucement> creatAnnoucement(@RequestBody Annoucement annoucement) {
        annoucementService.creatAnnouce(annoucement);
        return ResponseEntity.ok().body(annoucement);
    }

    @GetMapping
    public List<Annoucement> showAllData() {
        return annoucementService.getAllAnn();
    }

    @GetMapping("/shop")
    public List<Annoucement> showDataByShopName(@RequestBody Annoucement annoucement) {
        return annoucementService.getAnnByShop(annoucement.getShopName());
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable int id) {
        Optional<Annoucement> annoucement = annoucementService.getById(id);
        annoucementService.deleteAnnById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PutMapping("/edit")
    public ResponseEntity<Optional<Annoucement>> editShop(@PathVariable int id, @RequestBody Annoucement annDetails) {
        final Optional<Annoucement> annUpdated = annoucementService.updateAnnouce(id, annDetails);
        return ResponseEntity.ok(annUpdated);
    }



}
