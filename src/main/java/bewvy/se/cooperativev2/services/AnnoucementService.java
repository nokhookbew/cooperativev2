package bewvy.se.cooperativev2.services;

import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import bewvy.se.cooperativev2.models.Annoucement;
import bewvy.se.cooperativev2.models.Item;
import bewvy.se.cooperativev2.repositories.AnnoucementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnoucementService {

    @Autowired
    AnnoucementRepository annoucementRepository;

    public List<Annoucement> getAllAnn(){
        return annoucementRepository.findAll();
    }

    public Optional<Annoucement> getById(int id){
        return annoucementRepository.findById(id);
    }

    public List<Annoucement> getAnnByShop(String shopName) {
        return annoucementRepository.findAnnoucementsByShopNameByShopName(shopName);
    }

    public boolean deleteAnnById(int id) {
        annoucementRepository.deleteById(id);
        return true;
    }

    public Annoucement creatAnnouce(Annoucement annoucement){
        annoucementRepository.save(annoucement);
        return annoucement;
    }

    public Optional<Annoucement> updateAnnouce(int id, Annoucement annouceDetails) throws ResourceNotFoundException {
        Optional<Annoucement> annoucement = Optional.ofNullable(annoucementRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("annoucement not found on id: " + id)));
        if (!annoucement.isPresent()) {
            return annoucement;
        }
        annouceDetails.setId(id);
        return Optional.of(annoucementRepository.save(annouceDetails));
    }
}
