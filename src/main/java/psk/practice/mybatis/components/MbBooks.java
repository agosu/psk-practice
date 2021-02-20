package psk.practice.mybatis.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.mybatis.dao.BookMapper;
import psk.practice.mybatis.dao.CharacterMapper;
import psk.practice.mybatis.model.Book;
import psk.practice.mybatis.model.Character;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MbBooks {

    @Inject
    private BookMapper bookMapper;

    @Inject
    private CharacterMapper characterMapper;

    @Getter
    List<Book> allBooks;

    @Getter @Setter
    private Book bookToCreate = new Book();

    @PostConstruct
    public void init() {
        this.loadAllBooks();
    }

    public List<Character> getCharactersByBook(Integer bookId) {
        return characterMapper.selectCharactersByBook(bookId);
    }

    private void loadAllBooks() {
        this.allBooks = bookMapper.selectAll();
    }

    @Transactional
    public String createBook() {
        bookMapper.insert(bookToCreate);
        return "/mybatis/books?faces-redirect=true";
    }
}
