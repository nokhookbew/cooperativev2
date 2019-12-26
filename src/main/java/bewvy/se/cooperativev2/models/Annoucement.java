package bewvy.se.cooperativev2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Annoucement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title", unique = true)
    private String title;
    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime createDateTime;
    @Column(name="content")
    private String content;
    @Column(name="shop_name")
    private String shopName;
}
