package org.example.commands.ban;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.commands.Command;

public class UnbanCommand extends Command {
    public UnbanCommand() {
        super("unban");
    }

    @Override
    public void handleMessageCommand(MessageReceivedEvent event, String[] args) {
       //todo
    }

    private Member getMember(MessageReceivedEvent event, String[] args) {
        try {
            return event.getMessage().getMentions().getMembers().get(0);
        } catch (Exception ex) {
            if (args.length == 2) {
                throw new IllegalStateException("Can't find that user!");
            } else {
                throw new IllegalStateException("You need to mention the user you want to unban!");
            }
        }
    }

    private void announce(MessageReceivedEvent event, Member member) {
        event.getChannel().sendMessage(member.getAsMention() + " has been unbanned!").queue();
    }
}