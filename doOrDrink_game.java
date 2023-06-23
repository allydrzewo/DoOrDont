// Java Program to illustrate Reading from FileReader using BufferedReader Class
 
// Importing input output classes
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
 
// Main class
public class Main {
    // main driver method
    static ArrayList<String> prompts1 = new ArrayList<String>();
    static ArrayList<String> prompts2 = new ArrayList<String>();
    static ArrayList<String> prompts3 = new ArrayList<String>();
    static Scanner scan = new Scanner(System.in);
    static String player1, player2;
    static int pt1=0;
    static int pt2=0;
    static int turn;
        
    public static void main(String[] args) throws Exception
    {
        System.out.println("\n\n\n\n\n\n......................................................................\n----------------------------------------------------------------------\n");
        System.out.println("WELCOME TO DO OR DRINK\nIt's a game for couples! You can pick intensity level of each prompt you receive. \nThe options are: do what the prompt says or drink. Pretty simple. You get points for however many you complete successfully.\nPress [enter] to begin the game\n");
        System.out.println("----------------------------------------------------------------------\n......................................................................");
        String input = scan.nextLine();
        System.out.println();
        // File path is passed as parameter
        File file = new File("questions.txt");
        if (input == ""){
            System.out.println("Enter Player 1's name:");
            player1 = scan.nextLine();
            System.out.println("Enter Player 2's name:");
            player2 = scan.nextLine();
        }
        else{
            player1 = "Player 1";
            player2 = "Player 2";
        }
 
        // Creating an object of BufferedReader class
        BufferedReader r = new BufferedReader(new FileReader(file));
        // Declaring a string variable
        String str;
        // Condition holds true until there is character in a string
        while ((str = r.readLine()) != null) // Add to arraylist and Print the string
        { 
            if(str.substring(0,1).equals("1"))
            {
               prompts1.add(str.substring(2));
            }
            else if(str.substring(0,1).equals("2"))
            {
               prompts2.add(str.substring(2));
            }
            else
            {
               prompts3.add(str.substring(2));
            }
        }
            
        Collections.shuffle(prompts1);
        Collections.shuffle(prompts2);
        Collections.shuffle(prompts3);
        
        System.out.println("\nGreat! The game has different levels of risque prompts 1 being lowest intensity and 3 being the highest.\nHere is what you should enter into the command line:\n\t[o]    to accept the card\n\t[i]    to drink\n\t[end]  to end the game\n----------------------------------------------------------------------");
     
      //While loop to continue the game until all Arraylists (total of 3) are empty
      while(!prompts1.isEmpty() || !prompts2.isEmpty() || !prompts3.isEmpty())  
        {
            if(turn%2==0){
               askLevel(player1);
            }
            else{
               askLevel(player2);
            }
          turn++;
        }
      endOfGame();
    }
    //Prompt user to choose a level 
    public static void askLevel(String player)
    {
        System.out.println("\n\n"+player+", enter a prompt level: ");
        String choice = scan.nextLine(); 
        if(choice.equals("1")){
             getPrompt(prompts1,player);
         }
         else if(choice.equals("2")){
             getPrompt(prompts2,player);
         }
         else if(choice.equals("3")){
             getPrompt(prompts3,player);
         }
         else if(choice.equals("end")){
             clearAll();
         }
         else{
            System.out.println("Enter either 1, 2, 3, or end");
            askLevel(player);
          }
    }
    
    public static void getPrompt(ArrayList promptList, String player)
    {           
        System.out.println("\n......................................................................");
      try{
        System.out.println(promptList.get(promptList.size()-1));
        promptList.remove(promptList.size()-1);
        System.out.println("\n                 +++ DO +++    or    +++ DRINK +++\n");
        askChoice(player);
      }
      //Catches error for when the given list runs out of prompts
      catch(IndexOutOfBoundsException e){
        turn--;
        System.out.println("Ran out of prompts for this level try entering a different number.");
        askLevel(player);
        turn++;
      }
    }
    //Waits for input of user to choose DO or DRINK
    public static void askChoice(String player)
    {
      System.out.println("CHOICE: ");
      String choice = scan.nextLine();
      pointsAward(choice, player);
    }
    //Award points only if user picks DO and update how many points they now have
    public static void pointsAward(String answer, String player)
    {
       if(answer.equals("o"))
       {
         if (player1.equals(player))
         {
           pt1++;
           System.out.println("Nice! "+player1+" now has "+pt1+" points.\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
         }
         else
          {
            pt2++;
            System.out.println("Nice! "+player2+" now has "+pt2+" points.\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
          }
       }
      else if (answer.equals("i"))
       {
         System.out.println("Aw too bad, hopefully you'll do better on your next turn\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
       }

      //else if that breaks the while loop if user wants to end game early
      //clears all Arraylists
      else if (answer.equals("end"))
      {
        clearAll();
      }
      else
      {
        //turn--;
        System.out.println("... pressed wrong key try again ...");
        askChoice(player);
      }
    }
  public static void clearAll(){
    prompts1.clear();
    prompts2.clear();
    prompts3.clear();
  }
    //Ends game when called
    //Lets players know the winner before terminating the program  
  public static void endOfGame()
    {
      System.out.println("\n\n\n\nEND OF GAME\n----------------------------------------------------------------------\nthank you for playing do or drink don't be too upset about the scores\n\nSCORES: \n\t"+player1+" has "+pt1+" points");
      System.out.println("\t"+player2+" has "+pt2+" points");
        if (pt2<pt1)
        {
          System.out.println(player1+" wins!");
        }
        else if(pt1<pt2)
        {
          System.out.println(player2+" wins!");
        }
        else
        {
          System.out.println("It's a tie!");
        }
    }
}
