package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.example.listeners.ChangeNickname;
import org.example.commands.CommandListener;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.List;

public class BotApplication {
    public static JDA jda;

    public void start(final String token) throws LoginException, InterruptedException {
        CommandListener commandListener = new CommandListener();
        jda = JDABuilder.createDefault(token, getIntents())
                .addEventListeners(commandListener, new ChangeNickname())
                .enableCache(CacheFlag.ACTIVITY)
                .build()
                .awaitReady();

        commandListener.addCommands();
    }

    private List<GatewayIntent> getIntents() {
        return Arrays.asList(GatewayIntent.values());
    }
}
