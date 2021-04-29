package psk.practice.qualifiers;

import javax.enterprise.inject.Model;

@Model
public class StandardWelcomeImpl implements Welcome {
    @Override
    public String getWelcome() {
        return "Welcome!";
    }
}
