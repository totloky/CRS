package mod.totloky.crs;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigManager {

    // this method creates a config for the mod
    public static void configCreate() {

        File config = new File("config/crs.cfg");

        try {
            if (config.createNewFile()) {
                System.out.println("[CRS] config created");
                configWrite();
            } else {
                System.out.println("[CRS] config already exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // this method writes information to the new config
    public static void configWrite() {
        try(FileWriter writer = new FileWriter("config/crs.cfg", false)) {
            String text = "URL=\nTABLE=\nUSER=\nPASSWORD=";
            writer.write(text);
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    // this method read information from config
    public static String[] configRead() {

        File config = new File("config/crs.cfg");

        try {
            FileReader fileReader = new FileReader(config);
            BufferedReader reader = new BufferedReader(fileReader);
            String[] params = new String[4];
            Pattern pattern = Pattern.compile("[=].+$");
            Matcher matcher;
            String line;
            for (int i = 0; i<4; i++) {
                line = reader.readLine();
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    params[i] = line.substring(matcher.start(), matcher.end()).replaceAll("=","");
                }
            }
            return params;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}