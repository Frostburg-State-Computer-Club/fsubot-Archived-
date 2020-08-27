package components.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class GeneralCommandHandler extends ListenerAdapter {

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
                break;
            case "help":
                EmbedBuilder builder2 = new EmbedBuilder();
                builder2.setTitle("FSU Bot Help Menu");
                builder2.setColor(Color.RED);
                builder2.setDescription(
                    "> » `$hello` - Say hello to the bot!\n" +
                    "> » `$help` - View this menu.\n" +
                    "> » `$ping` - Pong.\n" +
                    "> » `$swallow` - Determine if African or European.\n" +
                    "> » `$weather` - View the weather outside!\n" +
                    "> » `$hackSteve` - 'Nuff said.\n" +
                    "> » `$What time is it?` - ...\n" +
                    "> » `$I'm hungry` - ...\n" +
                    "> » `$pizza` - ...pizza\n" +
                    "> » `$pig [args]` - Translate a phrase into pig latin.\n" +
                    "> » `$roll [amount]` - Roll a dice!\n"
                );
                event.getChannel().sendMessage(builder2.build()).queue();
                break;
        }
    }
}
