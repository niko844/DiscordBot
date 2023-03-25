package org.example.commands.kick;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.commands.Command;

public class KickCommand extends Command {
    public KickCommand() {
        super("kick");
    }

    @Override
    public void handleMessageCommand(MessageReceivedEvent event, String[] args) {
        Member member = getMember(event,args);

        if (args.length - 1 == 2) {
            member.kick(args[2]).queue();
            announce(event, member);
        } else if (args.length - 1 == 1) {
            throw new IllegalStateException("No reason specified!");
        }
    }

    private Member getMember(MessageReceivedEvent event, String[] args) {
        try {
            return event.getMessage().getMentions().getMembers().get(0);
        } catch (Exception ex) {
            if (args.length == 2) {
                throw new IllegalStateException("Can't find that user!");
            } else {
                throw new IllegalStateException("You need to mention the user you want to kick!");
            }
        }
    }

    private void announce(MessageReceivedEvent event, Member member) {
        event.getChannel().sendMessage(member.getAsMention() + " " + "has been kicked!").queue();
    }
}
