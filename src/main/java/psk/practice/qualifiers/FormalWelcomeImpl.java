package psk.practice.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

//@FormalWelcome
@Model
@Alternative
public class FormalWelcomeImpl implements Welcome {
    @Override
    public String getWelcome() {
        return "Hello, Mr/Mrs/Ms,";
    }
}
