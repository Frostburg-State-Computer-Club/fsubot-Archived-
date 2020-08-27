package components.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MostHandler extends ListenerAdapter{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Cancel this action if the author was the bot,
        // or cancel if the message didn't start with our command character.
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }
        String message = event.getMessage().getContentRaw().substring(1);
        if (message.equals("helloBot")) {
            event.getChannel().sendMessage("Heya Darryl!").queue();

        }
        else if(message.equals("getInfo")) {
            event.getChannel().sendMessage((CharSequence) event).queue();
            event.getChannel().sendMessage(event.getMessage()).queue();
        }
    }
}
