package bewvy.se.cooperativev2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title", unique = true)
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="coverImage")
    private String coverImage;
    @Column(name="category")
    private String category;
}
