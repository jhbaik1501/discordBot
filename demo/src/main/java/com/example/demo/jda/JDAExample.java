package com.example.demo.jda;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

@Component
public class JDAExample implements ApplicationRunner {

	@Value("${discord.token}")
	private String token;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		JDA jda = JDABuilder
			.createDefault(token)
			.addEventListeners(new MessageListener(), new ReadyListener())
			.setActivity(Activity.watching("메시지"))
			.build();

		jda.updateCommands().addCommands(
			Commands.slash("ping", "Calculate ping of the bot"),
			Commands.slash("ban", "Ban a user from the server")
				.setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.BAN_MEMBERS))
				.setGuildOnly(true)
				.addOption(OptionType.USER, "user", "The user to ban", true)
				.addOption(OptionType.STRING, "reason", "The ban reason"))
			.queue();
	}
}
