package components.listeners;

import java.util.Random;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SwallowHandler extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Cancel this action if the author was the bot,
        // or cancel if the message didn't start with our command character.
        if (event.getAuthor().isBot()|| !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }

        Random rand = new Random();

        String message = event.getMessage().getContentRaw().substring(1);
        if(message.contains("Swallow")) {
            float randomFloat = rand.nextFloat();
            if (randomFloat < 0.5){
                event.getChannel().sendMessage("European Swallow").queue();
            }else{
                event.getChannel().sendMessage("African Swallow").queue();
            }
        }
    }
}