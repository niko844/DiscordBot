package org.example;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.listeners.ChangeNickname;
import org.example.listeners.CommandListener;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

public class BotApplication {
    public void start(final String token) throws LoginException {
        JDABuilder.createDefault(token, getIntents())
                .addEventListeners(new CommandListener(), new ChangeNickname())
                .enableCache(CacheFlag.ACTIVITY)
                .build();

    }

    private List<GatewayIntent> getIntents() {
        return Arrays.asList(GatewayIntent.values());
    }
}
