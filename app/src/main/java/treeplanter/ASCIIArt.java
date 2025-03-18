package treeplanter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ASCIIArt {

    static String hike;
    static String tree;

    ASCIIArt() {
        try {
            hike = new String(Files.readAllBytes(Paths.get("app\\src\\main\\resources\\hike.txt")));
            tree = new String(Files.readAllBytes(Paths.get("app\\src\\main\\resources\\tree.txt")));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    public static String getHike() {
        return hike;
    }

    public static String getTree() {
        return tree;
    }

}
