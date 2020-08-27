package components.listeners.corecommands;

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
        String[] args = command.split(" ");
        command = args[0];
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
                String redirect = "";
                if (args.length > 1) {
                    redirect = args[1];
                }
                switch (redirect) {
                    case "tools":
                        builder2.setDescription(
                            "> » `VSCode` - The best Text Editor Ever. https://code.visualstudio.com/download\n" +
                            "> » `IntelliJ Idea` - Super powerful JAVA IDE. https://www.jetbrains.com/idea/download/#section=windows\n" +
                            "> » `NetBeans` - Most common professor requested IDE. https://netbeans.apache.org/download/index.html\n" +
                            "> » `Java SDKs` - https://www.oracle.com/java/technologies/javase-downloads.html\n" +
                            "> » `Github` - https://github.com/\n" +
                            "> » `Free Student Resources` - https://e5.onthehub.com/WebStore/ProductsByMajorVersionList.aspx?ws=dd8bb52b-d79b-e011-969d-0030487d8897&vsro=8&JSEnabled=1\n" +
                            "> » `Paws` - https://csprodweb2.frostburg.edu/psp/GoBobcats/?cmd=login"
                        );
                        builder2.setAuthor("Tools + Links");
                        break;
                    case "fun":
                        builder2.setDescription(
                            "> » `$hello` - Say hello to the bot!\n" +
                            "> » `$ping` - Pong.\n" +
                            "> » `$swallow` - Determine if African or European.\n" +
                            "> » `$weather` - View the weather outside!\n" +
                            "> » `$hackSteve` - 'Nuff said.\n" +
                            "> » `$What time is it?` - ...\n" +
                            "> » `$I'm hungry` - ...\n" +
                            "> » `$pizza` - ...pizza\n"
                        );
                        builder2.setAuthor("Fun Commands");
                        break;
                    case "features":
                        builder2.setDescription(
                            "> » `$pig [args]` - Translate a phrase into pig latin.\n" +
                            "> » `$roll [amount]` - Roll a dice!\n" +
                            "> » `$weatherAPI [zipcode]` - Show weather data for your area.\n"
                        );
                        builder2.setAuthor("Features");
                        break;
                    default:
                        builder2.setDescription(
                            "> » `$help [fun | features | tools]` - show specific help!"
                        );
                        builder2.setAuthor("General Help");
                        break;
                }
                event.getChannel().sendMessage(builder2.build()).queue();
                break;
        }
    }
}
