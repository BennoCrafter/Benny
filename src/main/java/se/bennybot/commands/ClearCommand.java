package se.bennybot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import se.bennybot.templates.EmbedBuilderTemplate;

public class ClearCommand extends ListenerAdapter {
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if (event.getName().startsWith("clear")) {

            if (event.getMember().hasPermission(Permission.ADMINISTRATOR) || event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                Integer count = event.getOption("count").getAsInt();
                String reason = event.getOption("reason").getAsString();
                event.getChannel().getHistory().retrievePast(count + 1).queue(messages -> {
                    messages.forEach(message -> message.delete().queue());
                });
                EmbedBuilder embed = EmbedBuilderTemplate.simpleEmbedBuilderTemplate("Success :white_check_mark:", event.getGuild().getName(), count + " messages were deleted, because of: " + reason + "!","", "", "", "");
                event.replyEmbeds(embed.build()).setEphemeral(true).queue();
            } else {
                event.reply("You do not have the Permission to clear messages!").setEphemeral(true).queue();
            }

        }
    }
}
