package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SwallowHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        // Cancel this action if the author was the bot,
        // or cancel if the message didn't start with our command character.
        if (event.getAuthor().isBot()|| !event.getMessage.getContentRaw().startWith("$")) {
            return;
        }
        String message = event.getMessage().getContentRaw().substring(1);
        if(message.contains("$Swallow")) {
            int random = getRandomNumber(2);
            if (random == 2){
                event.getChannel().sendMessage("European Swallow").queue();;
            }else{
                event.getChannel().sendMessage("African Swallow").queue();;
            }
        }
    }
}