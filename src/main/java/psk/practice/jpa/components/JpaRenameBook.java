package psk.practice.jpa.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.jpa.dao.BookDAO;
import psk.practice.jpa.dao.CharacterDAO;
import psk.practice.jpa.entities.Book;
import psk.practice.jpa.entities.Character;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@Model
public class JpaRenameBook implements Serializable {
    @Inject
    private BookDAO bookDAO;

    @Getter @Setter
    private Book book;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = bookDAO.findOne(bookId);
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String renameBook() {
        try {
            bookDAO.update(this.book);
        } catch (OptimisticLockException ex) {
            return "/rename.xhtml?faces-redirect=true&bookId=" + this.book.getId() + "&error=optimistic-lock-exception";
        }
        return "/jpa/books?faces-redirect=true";
    }
}
