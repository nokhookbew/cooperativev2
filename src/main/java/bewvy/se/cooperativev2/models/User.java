package bewvy.se.cooperativev2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username", nullable=false, unique = true)
    private String username;
    @Column(name="password", nullable=false)
    private String password;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="tel", nullable=false)
    private String tel;
    @Column(name="address", nullable=false)
    private String address;
    @Column(name="email", nullable=false)
    @Email
    private String email;
    @Column(name="citizenID", nullable=false)
    private String citizenID;
    @Column(name="photo")
    private String photo;
    @Column(name="host", nullable=false)
    private boolean host;
    @Column(name="type", nullable=false)
    private String type;

}
