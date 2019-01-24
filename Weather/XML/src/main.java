import java.io.File;
import java.util.Scanner;

public class main {
    public static void main(String[] args){
        System.out.println("Convert XML file to JSON file");
        File f = new File("weather.xml");
        convertData c = new convertData(f);
        c.process();
    }
}
