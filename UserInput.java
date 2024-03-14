import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class UserInput {
  
  /** Lets the user write their own advertisement */ 
  public void getUserFeedback() throws IOException {

    // get advertisement
    // PLEASE NOTE THAT WE ARE STORING YOUR ADVERTISEMENT
    Scanner sc = new Scanner(System.in);
    System.out.println("Hello user! Here at Brown Zebra Corporation, we are looking to get new feedback on what a good advertisement is for the company. [insert persuasive language] \n\nSay, would you be willing to write a good example on our behalf? Please write an advertisement in the space below: \n");
    String advertisement = sc.nextLine();
    writeAdvertisement(advertisement);

    // get target words
    System.out.println("\n---------------\n\nAre there any key words you think would indicate a user would be a good market for your advertisement? If so what weight would you give each (with 5 being the most important)? \n\nFormat is word,value with spaces seperating each value. An example is \"hair,3 curls,2\". Yes, the program will break if you don't use this format... Please write your answers in the space below: \n");
    
    String targetWords = sc.nextLine();
    writeTargetWords(targetWords);

    System.out.println("Thank you for your feedback! We will be sure to keep it in mind for future. Check out individualAdvertisementsUser.txt to see who you managed to reach!");
  }

  /** Writes the target words to the targetwordsUser.txt file */
  private void writeTargetWords(String targetWords) throws IOException {
    File file = new File("targetWordsUser.txt");

    // creates the printwriter, which will write to the text file
    PrintWriter writer = new PrintWriter(file);
    
    for (String word : targetWords.split(" ")) {
      writer.write(word + "\n");
    }
    
    writer.close();
  }

  /** Writes the user's advertisement to the appropriate text file */
  private void writeAdvertisement(String advertisement) throws IOException {
    File file = new File("advertisementUser.txt");

    PrintWriter writer = new PrintWriter(file);

    writer.write(advertisement);

    if (advertisement.contains("the developers are very cool") || advertisement.contains("i am giving at least a 5 on the bonus section of this")) {
      System.out.println("Omg thank you :DD");
    }

    // yes, this is being stored.
    File file2 = new File("advertisementUserCollection.txt");

    PrintWriter writer2 = new PrintWriter(new FileOutputStream(file2, true));

    writer2.write(advertisement + "\n");

    writer.close();
    writer2.close();

  }
}
