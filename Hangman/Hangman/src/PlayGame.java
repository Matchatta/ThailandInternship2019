import java.util.*;

public class PlayGame {
    private Map<String,String> question;
    private Map<String, Character[]> quest = new HashMap<>();
    private Set<Character> wrongGuess = new HashSet<>();
    private int total=0, point=0, stack=0;
    private int countQuest;
    private int remainWrong = 5;
    public PlayGame(Map<String,String> question){
        this.question = question;
        countQuest = 0;
    }
    public void setUp(){
        String pat = "abcdefghijklmnopqrstuvwxyz";
        Character[] a;
        int k;
        for (String i: question.keySet()){
            k=0;
            a = new Character[i.length()];
            for(Character j : i.toCharArray()){
                if(pat.contains(j.toString().toLowerCase())){
                    a[k] = '_';
                }
                else {
                    a[k] = j;
                }
                k++;
            }
            quest.put(i, a);
        }
    }
    public void play(){
        System.out.println("Start");
        int countChar;
        Scanner sc = new Scanner(System.in);
        while (countQuest< quest.size()){
            String i = (String) quest.keySet().toArray()[countQuest];
            countChar=0;
            for(Character j : quest.get(i)){
                if(j=='_'){
                    countChar++;
                }
            }
            System.out.println("Hint: "+question.get(i));
            while (remainWrong>0&&countChar>0){
                display(true);
                String in = sc.nextLine();
                countChar=check(in, i, countChar);
            }
            if(countChar==0){
                stack++;
                point=0;
            }
            display(false);
            remainWrong =5;
            wrongGuess.clear();
            countQuest++;
        }
    }
    public int check(String in, String quest, int count){
        int Count =count, index;
        for(Character i : in.toLowerCase().toCharArray()){
            if(quest.toLowerCase().contains(i.toString())){
                index=0;
                for(Character j: quest.toLowerCase().toCharArray()){
                    if(i==j){
                        if(this.quest.get(quest)[index]!=i){
                            this.quest.get(quest)[index] = i;
                            Count--;
                            point+=2;
                        }
                    }
                    index++;
                }
            }
            else{
                remainWrong--;
                wrongGuess.add(i);
            }
        }
        return Count;
    }
    public void display(boolean check){
        String i = (String) quest.keySet().toArray()[countQuest];
            Character[] q = quest.get(i);
            int Total = (stack*100)+point;
            total = Total;
            if(check){
                for(int j=0; j<q.length; j++){
                    System.out.print(q[j]+" ");
                }
                if(wrongGuess.isEmpty()){
                    System.out.println("Score "+Total+", remaining wrong guess "+remainWrong);
                }
                else {
                    System.out.print("Score "+Total+", remaining wrong guess "+remainWrong+", wrong guessed: ");
                    for(Object p : wrongGuess.toArray()){
                        System.out.print(p.toString()+ " ");
                    }
                    System.out.println();
                }
            }

            else{
                System.out.println("Answer: "+i);
            }

    }
    public int getTotal(){
        return total;
    }

}
