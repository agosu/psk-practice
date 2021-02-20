package psk.practice.jpa.components;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import psk.practice.jpa.entities.Book;
import psk.practice.jpa.entities.Character;
import psk.practice.jpa.dao.BookDAO;

@Model
public class JpaCharactersForBook implements Serializable {

    @Inject
    private BookDAO bookDAO;

    @Getter @Setter
    private Book book;

    @Getter @Setter
    private Character characterToCreate = new Character();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = bookDAO.findOne(bookId);
    }
}
