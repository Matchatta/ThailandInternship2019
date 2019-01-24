import java.io.IOException;
import java.util.Scanner;

public class main {
    static LoadData l;
    static PlayGame p;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose category\n[1]Country\n[2]Music");
        int choice = sc.nextInt();
        String file=null;

        if(choice==1){
            file="group1.txt";
        }
        else if(choice==2){
            file="group2.txt";
        }
        else{
            System.out.println("Error");
        }
        l = new LoadData(file);
        try {
            l.trainData();
            p = new PlayGame(l.getQuestion());
            p.setUp();
            p.play();
            System.out.println("You can get "+ p.getTotal()+" of " + l.getQuestion().size()*100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
