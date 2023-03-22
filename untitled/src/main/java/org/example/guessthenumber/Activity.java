package org.example.guessthenumber;


import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Activity {
    public void mute(MessageReceivedEvent event, Role muted, Role memberRole, Member member) {
        String content;
        if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087098912369819738"))) {
            content = "is already muted";
            announce(event, member, content);
        } else if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))) {
            content = "You can't mute this user!";
            announce(event, member, content);
        } else {
            muted.getGuild().removeRoleFromMember(member.getUser(), memberRole).queue();
            muted.getGuild().addRoleToMember(member.getUser(), muted).queue();
            content = "has been muted";
            announce(event, member, content);
        }

    }

    public void unMute(MessageReceivedEvent event, Role muted, Role memberRole, Member member) {
        String content;
        if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))) {//member.getRoles().stream().anyMatch(x -> x.getId().equals("1087489669333262407"))
            content = "You can't unmute this user!";
            announce(event, member, content);
        } else if (member.getRoles().stream().anyMatch(x -> x.getId().equals("1087098870665846854"))) {
            content = "is not muted";
            announce(event, member, content);
        } else {
            muted.getGuild().removeRoleFromMember(member.getUser(), muted).queue();
            muted.getGuild().addRoleToMember(member.getUser(), memberRole).queue();
            content = "has been unmuted";
            announce(event, member, content);
        }
    }


    private void announce(MessageReceivedEvent event, Member member, String content) {
        event.getChannel().sendMessage(member.getAsMention() + " " + content).queue();
    }

}
