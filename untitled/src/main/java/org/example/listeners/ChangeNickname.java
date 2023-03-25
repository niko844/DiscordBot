package org.example.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.member.GuildMemberUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ChangeNickname extends ListenerAdapter {
    @Override
    public void onGuildMemberUpdate(@NotNull GuildMemberUpdateEvent event) {
        Member member = event.getMember();
        String newNickname = event.getMember().getNickname();
        if (newNickname == null) {
            return;
        }

        if (!newNickname.equals("dupe davec") && member.getId().equals("340461184576913418")) {
            String oldNickname = "dupe davec";
            Guild guild = member.getGuild();
            guild.modifyNickname(member, oldNickname).queue();
            System.out.println(member);
        }
    }
}