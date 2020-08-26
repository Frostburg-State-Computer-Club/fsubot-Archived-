package app;

import config.TokenLoader;
import listeners.EventHandler;
import listeners.RollHandler;
import listeners.SteveKHandler;
import listeners.WeatherHandler;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = new TokenLoader().getToken();
        builder.setToken(token);
        builder.addEventListeners(
            new EventHandler(),
            new RollHandler(),
            new SteveKHandler(),
            new WeatherHandler()
        );
        try {
            builder.build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
