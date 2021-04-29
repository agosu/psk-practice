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
import psk.practice.qualifiers.FormalWelcome;
import psk.practice.qualifiers.FriendlyWelcome;
import psk.practice.qualifiers.FriendlyWelcomeImpl;
import psk.practice.qualifiers.Welcome;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
@Dependent
public class JpaBooks {

    @Inject
    private BookDAO bookDAO;

    @Inject
    private CharacterDAO characterDAO;

    @Inject
    private Title title;

    @Inject //@FormalWelcome
    //private FriendlyWelcomeImpl welcome;
    private Welcome welcome;

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

    public String getWelcome() {
        return welcome.getWelcome();
    }

    public List<Character> getCharactersByBook(Integer bookId) {
        return characterDAO.findByBook(bookId);
    }
}
