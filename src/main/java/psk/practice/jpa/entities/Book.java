package psk.practice.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "findAllBooks", query = "select a from Book as a")
})
@Getter @Setter
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "NAME")
    private String title;

    @ManyToMany
    private List<Author> authors;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Character> characters;
}
