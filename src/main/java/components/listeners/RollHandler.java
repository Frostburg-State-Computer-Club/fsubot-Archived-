package components.listeners;

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
        if (event.getAuthor().isBot()
            || !event.getMessage().getContentRaw()
                .startsWith("$")
            || !event.getMessage().getContentRaw()
                .substring(1).startsWith("roll")
        ) {
            return;
        }
        String command = event.getMessage().getContentRaw().substring(1);
        String[] args = command.split(" ");
        if (args.length <= 1) {
            int roll = getRandomNumber(20);
            event.getChannel().sendMessage(
                getResponse(roll, event.getAuthor().getName(), 20)
            ).queue();
        }
        else {
            int dice = Integer.parseInt(args[1]);
            int roll = getRandomNumber(dice);
            event.getChannel().sendMessage(
                getResponse(roll, event.getAuthor().getName(), dice)
            ).queue();
        }
    }

    private MessageEmbed getResponse(int roll, String author, int dice) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setDescription("They got a " + roll + "!");
        int low = dice/4;
        int high = low*3;
        if (roll < low) {
            builder.setColor(Color.RED);
        }
        if (roll >= low && roll < high) {
            builder.setColor(Color.YELLOW);
        }
        if (roll >= high) {
            builder.setColor(Color.GREEN);
        }
        builder.setTitle(author + " rolls a d" + dice + "...");
        return builder.build();
    }

    private int getRandomNumber(int max) {
        return rand.nextInt((max-1) + 1) + 1;
    }
}
