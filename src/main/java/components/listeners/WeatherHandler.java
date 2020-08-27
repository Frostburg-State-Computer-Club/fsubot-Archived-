package components.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WeatherHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
       // Cancel this action if the author was the bot,
       // or cancel if the message didn't start with our command character.
       if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
           return;
       }
       String message = event.getMessage().getContentRaw();
       if (message.equals("$weather")) {
           event.getChannel().sendMessage("I don't know ... look outside").queue();
       }
    }
}