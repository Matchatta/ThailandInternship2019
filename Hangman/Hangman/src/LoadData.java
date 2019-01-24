import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoadData {
    FileReader fr;
    private Map<String, String> question = new HashMap<>();
    public LoadData(String file){
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void trainData() throws IOException {
        String pattern = "(.+),(.+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m;
        String data;
        BufferedReader bf;
        bf = new BufferedReader(fr);
        while ((data=bf.readLine())!=null){
            m= p.matcher(data);
            if(m.find()){
                question.put(m.group(1), m.group(2));
            }
        }
        System.out.println("Loaded data");
    }

    public Map<String, String> getQuestion() {
        return question;
    }
}
