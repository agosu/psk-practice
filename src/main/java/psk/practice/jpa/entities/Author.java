package psk.practice.jpa.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter @Setter
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 25)
    private String firstname;

    @Size(max = 25)
    private String lastname;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;
}
