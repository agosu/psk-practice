package psk.practice.jpa.components;

import lombok.Getter;
import lombok.Setter;
import psk.practice.jpa.dao.BookDAO;
import psk.practice.jpa.entities.Book;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

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
            //parallel();
            bookDAO.update(this.book);
        } catch (OptimisticLockException ex) {
            return "/rename.xhtml?faces-redirect=true&bookId=" + this.book.getId() + "&error=optimistic-lock-exception";
        }
        return "/jpa/books?faces-redirect=true";
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    private void parallel() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        this.book.setTitle("Parallel-" + new Random().nextInt());
        executor.execute(() -> bookDAO.update(this.book));
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
