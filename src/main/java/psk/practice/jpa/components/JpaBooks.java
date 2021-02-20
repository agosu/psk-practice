package psk.practice.jpa.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.jpa.dao.BookDAO;
import psk.practice.jpa.dao.CharacterDAO;
import psk.practice.jpa.entities.Book;
import psk.practice.jpa.entities.Character;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class JpaBooks {

    @Inject
    private BookDAO bookDAO;

    @Inject
    private CharacterDAO characterDAO;

    @Getter
    List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        this.loadAllBooks();
    }

    private void loadAllBooks() {
        this.allBooks = bookDAO.loadAll();
    }

    @Transactional
    public String createBook() {
        bookDAO.persist(bookToCreate);
        return "/jpa/books?faces-redirect=true";
    }

    public List<Character> getCharactersByBook(Integer bookId) {
        return characterDAO.findByBook(bookId);
    }
}
