package psk.practice.jpa.dao;

import psk.practice.jpa.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class BookDAO {

    @Inject
    private EntityManager em;

    public List<Book> loadAll() {
        return em.createNamedQuery("findAllBooks", Book.class).getResultList();
    }

    public List<Book> findLike(String fragment) {
        return em.createQuery("SELECT b FROM Book b WHERE b.title LIKE :fragment")
                .setParameter("fragment", fragment)
                .getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(Book book){
        this.em.persist(book);
    }

    public Book findOne(Integer id) {
        return em.find(Book.class, id);
    }

    public Book update(Book book) {
        return em.merge(book);
    }
}
