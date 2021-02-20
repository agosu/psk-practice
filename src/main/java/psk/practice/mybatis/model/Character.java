package psk.practice.mybatis.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Character {

    private Integer id;

    private String firstname;

    private String lastname;

    private Integer bookId;

    //TODO Black magic
    //private Book book;
}