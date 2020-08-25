package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Random;

public class RollHandler extends ListenerAdapter {

    private Random rand = new Random();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }
        String command = event.getMessage().getContentRaw().substring(1);
        if (command.startsWith("roll")) {
            String[] args = command.split(" ");
            if (args.length <= 1) {
                event.getChannel().sendMessage("You must provide a number! ($roll 20)").queue();
                return;
            }
            int roll = getRandomNumber(Integer.parseInt(args[1]));
            event.getChannel().sendMessage(getResponse(roll, event.getAuthor().getName())).queue();
        }
    }

    private MessageEmbed getResponse(int roll, String author) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription("They got a " + roll + "!");
        builder.setColor(Color.GREEN);
        builder.setTitle(author + " rolls...");
        return builder.build();
    }

    private int getRandomNumber(int max) {
        return rand.nextInt((max-1) + 1) + 1;
    }
}
