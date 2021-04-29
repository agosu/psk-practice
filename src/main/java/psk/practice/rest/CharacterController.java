package psk.practice.rest;

import lombok.Getter;
import lombok.Setter;
import psk.practice.jpa.dao.BookDAO;
import psk.practice.jpa.dao.CharacterDAO;
import psk.practice.jpa.entities.Book;
import psk.practice.jpa.entities.Character;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.RollbackException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/characters")
public class CharacterController {
    @Inject
    @Getter @Setter
    private CharacterDAO characterDAO;

    @Inject
    @Getter @Setter
    private BookDAO bookDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Character character = characterDAO.findOne(id);
        if (character == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        CharacterDTO characterDTO = new CharacterDTO();
        characterDTO.setFirstname(character.getFirstname());
        characterDTO.setLastname(character.getLastname());
        characterDTO.setBookId(character.getBook().getId());

        return Response.ok(characterDTO).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(CharacterDTO characterDTO) {
        Character character = new Character();
        Book book = bookDAO.findOne(characterDTO.getBookId());
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        character.setFirstname(characterDTO.getFirstname());
        character.setLastname(characterDTO.getLastname());
        character.setBook(book);
        characterDAO.persist(character);

        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public Response update(@PathParam("id") final Integer id, CharacterDTO characterDTO) throws InterruptedException {
        try {
            Character character = characterDAO.findOne(id);
            Book book = bookDAO.findOne(characterDTO.getBookId());
            if (character == null || book == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            Thread.sleep(7000);

            character.setFirstname(characterDTO.getFirstname());
            character.setLastname(characterDTO.getLastname());
            character.setBook(book);
            characterDAO.update(character);
            return Response.ok().build();
        } catch (OptimisticLockException /*| RollbackException*/ ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
