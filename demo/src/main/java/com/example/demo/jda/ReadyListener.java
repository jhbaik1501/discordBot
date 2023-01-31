package com.example.demo.jda;

import static java.util.concurrent.TimeUnit.*;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.RestAction;

public class ReadyListener implements EventListener
{

	@Override
	public void onEvent(GenericEvent event)
	{

		if (event instanceof ReadyEvent)
			System.out.println("API is ready!");
		else if (event instanceof MessageReceivedEvent) {
			Message msg = ((MessageReceivedEvent)event).getMessage();
			if (msg.getContentRaw().equals("!ping"))
			{
				MessageChannel channel = ((MessageReceivedEvent)event).getChannel();
				long time = System.currentTimeMillis();
				channel.sendMessage("Pong!")
					.queue(response -> {
						response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
					});
			}
		}
	}
}