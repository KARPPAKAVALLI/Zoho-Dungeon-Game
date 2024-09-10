import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //get input
        MinSteps minSteps = new MinSteps();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Dimensions:");
        int m = sc.nextInt(), n = sc.nextInt();
        System.out.println("Enter adventurer positions:");
        int adventurer1 = sc.nextInt(), adventurer2 = sc.nextInt();
        System.out.println("Enter monster positions:");
        int monster1 = sc.nextInt(), monster2 = sc.nextInt();
        System.out.println("Enter gold positions:");
        int gold1 =sc.nextInt(), gold2 = sc.nextInt();

        //get adventurer min path
        ArrayList<ArrayList<String>> adventurerPaths = new ArrayList<>();
        int adventurer = minSteps.getMinSteps(adventurer1-1,adventurer2-1,gold1-1,gold2-1,m,n,adventurerPaths);

        //get monster min path
        ArrayList<ArrayList<String >> monsterPaths = new ArrayList<>();
        int monster = minSteps.getMinSteps(monster1-1,monster2-1,gold1-1,gold2-1,m,n,monsterPaths);

        //Check clashing
        boolean flag = true;
        ArrayList<String> ans = new ArrayList<>();
        for(ArrayList<String> paths1 : adventurerPaths){
            flag = true;
            for(int i=0;i<paths1.size()-1;i++){
                for(ArrayList<String> paths2 : monsterPaths){
                    //check each i with each j since, according to question,
                    //the monster may / may not move.
                    for(int j=0;j<paths2.size()-1;j++){
                        if(paths1.get(i).equals(paths2.get(j))){
                            flag = false;
                            break;
                        }
                    }
                }
            }
            if(flag){
                ans = paths1;
                break;
            }
        }
        if(!flag){
            System.out.println("No possible solution");
        }
        else {
            System.out.println("Path :" + ans);
            System.out.println("Minimum no. of steps = "+adventurer);
        }
    }
}