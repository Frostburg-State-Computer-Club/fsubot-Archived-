package components.listeners.weatherapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import config.TokenLoader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherAPI extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getMessage().getContentRaw().startsWith("$")) {
            return;
        }
        if (event.getMessage().getContentRaw().startsWith("$weatherAPI")) {
            String accessKey = new TokenLoader().getWeatherAPIKey();
            if (accessKey.equalsIgnoreCase("none")) {
                event.getChannel().sendMessage("Your WeatherAPI key is not set up!").queue();
                return;
            }
            String request = "http://api.weatherstack.com/current?access_key="+accessKey+"&units=f&query=";
            String[] args = event.getMessage().getContentRaw().split(" ");
            String city;
            if (args.length < 2) {
                city = "Frostburg";
                request += city;
            }
            else {
                city = args[1];
                request += (city );
            }
            try {
                System.out.println(request);
                URL url = new URL(request);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(content.toString());
                event.getChannel().sendMessage(getWeatherEmbed(node).build()).queue();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private EmbedBuilder getWeatherEmbed(JsonNode node) {
        String name = node.get("location").get("name").asText();
        String state = node.get("location").get("region").asText();

        JsonNode current = node.get("current");
        String image = current.get("weather_icons").get(0).asText();
        String temperature = current.get("temperature").asText();
        String feelsLike = current.get("feelslike").asText();
        String desc = current.get("weather_descriptions").get(0).asText();
        String humidity = current.get("humidity").asText();
        String precip = current.get("precip").asText();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("The Weather in " + name +", "+state+":");
        builder.setImage(image);
        builder.setColor(Color.BLUE);
        builder.setDescription(
            "**Temperature:** " + temperature + "°F\n" +
            "**Feels Like:** " + feelsLike + "°F\n" +
            "**" + desc + "**\n" +
            "**Humidity:** " + humidity + "\n" +
            "**Chance of Precip:** " + precip
        );
        return builder;
    }
}
