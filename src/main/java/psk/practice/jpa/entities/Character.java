package psk.practice.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter @Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 25)
    private String firstname;

    @Size(max = 25)
    private String lastname;

    @ManyToOne
    private Book book;
}
