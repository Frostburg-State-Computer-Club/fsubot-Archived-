package components.listeners;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PigLatinListener extends ListenerAdapter {
    
@Override
public void onMessageReceived(MessageReceivedEvent event) {
   // Cancel this action if the author was the bot,
   // or cancel if the message didn't start with our command character.
   if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
       return;
   }
   String command = event.getMessage().getContentRaw().substring(1);
   if (command.startsWith("pig")) {
       if(!command.matches("[^a-zA-Z\\s]+")) {
           event.getChannel().sendMessage("Letters only! Numbers and punctuation do not translate to pig latin").queue();
           return;
       }
       String[] words = command.split(" ");
       String pigLatinMessage = "";

       for(int i = 1; i < words.length; i++){
           // word starts with a vowel
           if(words[i].matches("[aeiouAEIOU].+")) {
                pigLatinMessage += words[i] + "way";
           } else {
               //switch the first group of consonants with the rest of the letter and add "ay"
               pigLatinMessage += words[i].replaceAll("(?i)(^[^aeiou]+)(.*)", "$2$1ay");
           }
       }
       event.getChannel().sendMessage(pigLatinMessage).queue();
   }
}

}