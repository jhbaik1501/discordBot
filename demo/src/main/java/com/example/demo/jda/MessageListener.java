package com.example.demo.jda;

import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;

public class MessageListener extends ListenerAdapter
{
	@Override
	public void onMessageReceived(MessageReceivedEvent event)
	{
		String contentRaw = event.getMessage().getContentRaw();

		if (event.isFromType(ChannelType.PRIVATE))
		{
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
				event.getMessage().getContentDisplay());
		}
		else
		{
			System.out.printf("[%s] %s: %s\n", event.getGuild().getName(),
				event.getMember().getEffectiveName(),
				event.getMessage().getContentDisplay());
		}
	}

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		switch (event.getName()) {
			case "ping":
				long time = System.currentTimeMillis();
				event.reply("Pong!").setEphemeral(true) // reply or acknowledge
					.flatMap(v ->
							event.getHook().editOriginalFormat("Pong: %d ms", System.currentTimeMillis() - time)
					).queue();
				break;
			case "ban":
				event.reply("밴이요").queue();
		}
	}


}
