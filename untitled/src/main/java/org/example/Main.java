package org.example;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        BotApplication bot = new BotApplication();
        bot.start("");
    }
}