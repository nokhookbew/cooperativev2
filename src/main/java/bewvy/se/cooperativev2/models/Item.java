package bewvy.se.cooperativev2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="unit")
    private String unit;
    @Column(name="price")
    private Double price;
    @Column(name="image_url")
    private String image;
    @Column(name="shop_name")
    private String shopName;

}
