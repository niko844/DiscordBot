package org.example.listeners;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.example.guessthenumber.Activity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandListener extends ListenerAdapter {
    private final Activity activity = new Activity();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (message.contains("!")) {
            boolean hasPerm = Objects.requireNonNull(event.getMember()).getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"));
            if (event.getMember().getUser().isBot()) {
                return;
            }
            if (hasPerm) {
                String[] args = event.getMessage().getContentRaw().split("\\s+");
                String commandName = args[0].toLowerCase();

                Role mutedRole = event.getGuild().getRoleById("1087098912369819738");
                Member member = tryCatch(event);
                if (member == null){
                    return;
                }
                switch (commandName) {
                    case "!mute" -> activity.mute(event, mutedRole, member);
                    case "!unmute" -> activity.unMute(event, mutedRole, member);
                    case "!kick" -> activity.kickUser(event, member, args);
                }

            } else {
                write(event, "You can't use this command!");
            }
        }
    }

    private Member tryCatch(MessageReceivedEvent event) {
        try {
            return event.getMessage().getMentions().getMembers().get(0);
        } catch (Exception ex) {
            write(event, "Can't find that user");
        }
        return null;
    }

    private void write(MessageReceivedEvent event, String content) {
        String user = event.getMessage().getAuthor().getAsMention();
        event.getChannel().sendMessage(user + " " + content).queue();
    }
}
