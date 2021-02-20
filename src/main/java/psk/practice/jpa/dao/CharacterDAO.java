package psk.practice.jpa.dao;

import psk.practice.jpa.entities.Character;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CharacterDAO {

    @Inject
    private EntityManager em;

    public void persist(Character character){
        this.em.persist(character);
    }

    public Character findOne(Integer id){
        return em.find(Character.class, id);
    }

    public Character update(Character character){
        return em.merge(character);
    }

    public List<Character> findByBook(Integer bookId) {
        return em.createQuery("SELECT c FROM Character c WHERE c.book.id = :bookId")
                .setParameter("bookId", bookId)
                .getResultList();
    }
}
