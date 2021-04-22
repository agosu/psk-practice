package psk.practice.jpa.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class ReadingDaysGenerator {
    public Integer makeReadingGoal() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {

        }
        Integer readingGoal = new Random().nextInt(50);
        return readingGoal;
    }
}
