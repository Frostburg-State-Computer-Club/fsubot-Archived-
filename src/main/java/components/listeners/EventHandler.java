package components.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EventHandler extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }
        String command = event.getMessage().getContentRaw().substring(1);
        switch (command) {
            case "ping":
                event.getChannel().sendMessage("Pong!").queue();
                break;
            case "hello":
                event.getChannel().sendMessage("Hey there!").queue();
                break;
            case "embed":
                EmbedBuilder builder = new EmbedBuilder();
                builder.setAuthor("Author");
                builder.setDescription("Description");
                builder.setFooter("footer");
                builder.setTitle("Title!");
                event.getChannel().sendMessage(builder.build()).queue();
        }
    }
}
