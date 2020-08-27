package components.listeners.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAPI extends ListenerAdapter {
    private String accessKey = "6000ec28a1f906a0e2e94916761b37b7";
    private String request = "https://api.weatherstack.com/current?access_key="+accessKey+"&query=";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }
        if (event.getMessage().getContentRaw().startsWith("$weatherAPI")) {
            String city = "Frostburg";
            request += city;
            try {
                URL url = new URL(request);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(con.getInputStream());

                String response = node.asText();

                System.out.println("Got Response: "+response);
//                event.getChannel().sendMessage(response).queue();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
