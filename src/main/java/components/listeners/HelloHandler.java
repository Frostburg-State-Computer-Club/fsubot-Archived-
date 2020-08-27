package components.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Cancel this action if the author was the bot,
        // or cancel if the message didn't start with our command character.
        if (event.getAuthor().isBot()) {
            return;
        }
        String message = event.getMessage().getContentRaw();
        if(message.contains("This bot sucks")) {
            event.getChannel().sendMessage("I saw that!");
        }
    }
}
