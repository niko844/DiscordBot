package org.example.commands.ban;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.UserSnowflake;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.commands.Command;


public class BanCommand extends Command {
    public BanCommand() {
        super("ban");
    }

    @Override
    public void handleMessageCommand(MessageReceivedEvent event, String[] args) {
        Member member = getMember(event,args);
        if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))) {
            throw new IllegalStateException("You don't have permission!");
        }
        if (args.length == 4) {
            int banDelay = Integer.parseInt(args[2]);
            event.getGuild().ban(UserSnowflake.fromId(member.getId()),banDelay).reason(args[3]).queue();
            announce(event, member);
        } else {
            throw new IllegalStateException("!ban @user 'ban delay' 'reason'");
        }
    }

    private Member getMember(MessageReceivedEvent event, String[] args) {
        try {
            return event.getMessage().getMentions().getMembers().get(0);
        } catch (Exception ex) {
            if (args.length == 2) {
                throw new IllegalStateException("Can't find that user!");
            } else {
                throw new IllegalStateException("You need to mention the user you want to ban!");
            }
        }
    }

    private void announce(MessageReceivedEvent event, Member member) {
        event.getChannel().sendMessage(member.getAsMention() + " has been banned!").queue();
    }
}
