package psk.practice.mybatis.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.mybatis.dao.BookMapper;
import psk.practice.mybatis.model.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@Model
public class MbCharactersForBook {

    @Inject
    BookMapper bookMapper;

    @Getter @Setter
    private Book book;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer bookId = Integer.parseInt(requestParameters.get("bookId"));
        this.book = bookMapper.selectByPrimaryKey(bookId);
    }
}
