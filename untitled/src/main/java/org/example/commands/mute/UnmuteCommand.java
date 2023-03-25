package org.example.commands.mute;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.example.BotApplication;
import org.example.commands.Command;

public class UnmuteCommand extends Command {
    private final Role muted;

    public UnmuteCommand() {
        super("unmute");
        this.muted = BotApplication.jda.getRoleById("1087098912369819738");
    }

    @Override
    public void handleMessageCommand(MessageReceivedEvent event, String[] args) {
        Member member = getMember(event,args);

        if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))) {//member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))
            throw new IllegalStateException("You don't have permission!");
        }

        if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087098870665846854"))) {
            throw new IllegalStateException("is not muted");
        }

        muted.getGuild().removeRoleFromMember(member.getUser(), muted).queue();
        announce(event, member);

    }

    private Member getMember(MessageReceivedEvent event, String[] args) {
        try {
            return event.getMessage().getMentions().getMembers().get(0);
        } catch (Exception ex) {
            if (args.length == 2) {
                throw new IllegalStateException("Can't find that user!");
            } else {
                throw new IllegalStateException("You need to mention the user you want to unmute!");
            }
        }
    }

    private void announce(MessageReceivedEvent event, Member member) {
        event.getChannel().sendMessage(member.getAsMention() + " " + "has been unmuted").queue();
    }

}
