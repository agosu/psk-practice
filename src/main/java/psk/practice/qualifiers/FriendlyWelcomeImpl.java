package psk.practice.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;

//@FriendlyWelcome
@Model
@Alternative
public class FriendlyWelcomeImpl implements Welcome {
    @Override
    public String getWelcome() {
        return "Hi,";
    }
}
