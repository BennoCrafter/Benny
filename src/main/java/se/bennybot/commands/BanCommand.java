package se.bennybot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.bennybot.templates.EmbedBuilderTemplate;

import java.util.concurrent.TimeUnit;

public class BanCommand extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().startsWith("ban")) {

            if (event.getMember().hasPermission(Permission.ADMINISTRATOR) || event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
                Member user = event.getOption("user").getAsMember();
                String reason = event.getOption("reason").getAsString();
                int deldays = event.getOption("deldays").getAsInt();

                user.ban(deldays, TimeUnit.DAYS).reason(reason).queue();

                EmbedBuilder embed = EmbedBuilderTemplate.simpleEmbedBuilderTemplate("Success :white_check_mark:", event.getGuild().getName(), "The User: " + user.getUser().getName() + " is now banned!", "", "", "", "");
                event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            }
            else {
                event.reply("You do not have the permission to ban a member!").setEphemeral(true).queue();
            }

        }

    }

}
