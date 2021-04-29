package psk.practice.qualifiers;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Specializes;

@Model
@Specializes
@Alternative
public class WeatherFriendlyWelcome extends FriendlyWelcomeImpl {
    @Override
    public String getWelcome() {
        return "Hi, how is the weather today?";
    }
}
