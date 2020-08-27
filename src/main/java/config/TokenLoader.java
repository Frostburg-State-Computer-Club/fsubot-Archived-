package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TokenLoader {
    private String token;
    private String weatherAPIKey = "none";

    public TokenLoader() {
        File file = new File("./token.txt");
        ArrayList<String> tokens = new ArrayList<>();
        try {
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                tokens.add(in.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.token = tokens.get(0);
        if (tokens.size() > 1) {
            this.weatherAPIKey = tokens.get(1);
        }
    }

    public String getToken() {
        return this.token;
    }
    public String getWeatherAPIKey() { return this.weatherAPIKey; }
}
