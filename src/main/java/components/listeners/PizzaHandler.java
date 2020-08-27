package components.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PizzaHandler extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
   // Cancel this action if the author was the bot,
   // or cancel if the message didn't start with our command character.
   if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
       return;
   }
   String message = event.getMessage().getContentRaw().substring(1);
   if (message.equals("What time is it?")) {
       event.getChannel().sendMessage("Pizza Time!").queue();
   }
   if (message.equals("I'm hungry")) {
       event.getChannel().sendMessage("Pizza Time!").queue();
   }
   if (message.contains("pizza")) {
       event.getChannel().sendMessage("Pizza Time!").queue();
   }

}


}