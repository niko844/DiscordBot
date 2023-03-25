package org.example.commands;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.commands.ban.BanCommand;
import org.example.commands.ban.UnbanCommand;
import org.example.commands.kick.KickCommand;
import org.example.commands.mute.MuteCommand;
import org.example.commands.mute.UnmuteCommand;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandListener extends ListenerAdapter {
    private final String prefix;
    private final List<Command> commands;

    public CommandListener() {
        this.prefix = "!";
        this.commands = new ArrayList<>();
    }
    public void addCommands(){
        this.commands.add(new BanCommand());
        this.commands.add(new MuteCommand());
        this.commands.add(new UnmuteCommand());
        this.commands.add(new KickCommand());
        this.commands.add(new UnbanCommand());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getMember() == null || event.getMember().getUser().isBot()) {
            return;
        }
        String message = event.getMessage().getContentRaw();
        if (!message.startsWith(this.prefix)) {
            return;
        }
        String[] args = message.split("\\s+");
        if (args.length == 0) {
            return;
        }
        String commandName = args[0].substring(this.prefix.length()).toLowerCase();
        Command command = getCommandByName(commandName);
        if (command == null){
            return;
        }
        try {
            command.handleMessageCommand(event, args);
        } catch (Exception ex) {
            write(event, ex.getMessage());
        }

    }

    public Command getCommandByName(String name) {
        for (Command command : commands) {
            if (command.getName().equals(name)) {
                return command;
            }
            String[] aliases = command.getAliases();
            for (String alias : aliases) {
                if (alias.equals(name)) {
                    return command;
                }
            }
        }
        return null;
    }

    public void write(MessageReceivedEvent event, String content) {
        event.getChannel().sendMessage(content).queue();
    }
}
