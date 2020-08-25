package config;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TokenLoader {
    private String token;

    public TokenLoader() {
        File file = new File("./token.txt");
        try {
            Scanner in = new Scanner(file);
            while(in.hasNextLine()) {
                this.token = in.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return this.token;
    }
}
