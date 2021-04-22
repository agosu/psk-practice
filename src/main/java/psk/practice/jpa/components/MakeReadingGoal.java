package psk.practice.jpa.components;

import psk.practice.jpa.services.ReadingDaysGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class MakeReadingGoal implements Serializable {

    @Inject
    ReadingDaysGenerator readingDaysGenerator;

    private CompletableFuture<Integer> readingDaysGenerationTask = null;

    public String makeReadingGoal() {
        Map<String, String> requestParameters = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        readingDaysGenerationTask = CompletableFuture.supplyAsync(() -> readingDaysGenerator.makeReadingGoal());
        return "/jpa/rename.xhtml?faces-redirect=true&bookId=" + requestParameters.get("bookId");
    }

    public boolean isMakingGoal() {
        return readingDaysGenerationTask != null && !readingDaysGenerationTask.isDone();
    }

    public String getReadingDaysGeneratorStatus() throws ExecutionException, InterruptedException {
        if (readingDaysGenerationTask == null) {
            return null;
        } else if (isMakingGoal()) {
            return "Generator is making goal. Please wait...";
        }
        return "You have to read a book in " + readingDaysGenerationTask.get() + " days.";
    }
}
