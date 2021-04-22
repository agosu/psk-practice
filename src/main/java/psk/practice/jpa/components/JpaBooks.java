package psk.practice.jpa.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.decorators.SimpleTitle;
import psk.practice.decorators.Title;
import psk.practice.interceptors.Logged;
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

    @Inject
    private SimpleTitle title;

    @Getter
    List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @Getter @Setter
    private Integer renameId;

    @PostConstruct
    public void init() {
        this.loadAllBooks();
    }

    private void loadAllBooks() {
        this.allBooks = bookDAO.loadAll();
    }

    @Transactional
    @Logged
    public String createBook() {
        title.logTitle(bookToCreate.getTitle());
        bookDAO.persist(bookToCreate);
        return "/jpa/books?faces-redirect=true";
    }

    public List<Character> getCharactersByBook(Integer bookId) {
        return characterDAO.findByBook(bookId);
    }
}
