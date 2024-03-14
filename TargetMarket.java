import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.FileReader;


public class TargetMarket {

  /** Writes each suitable author & their suitability value to targetmarket.txt */
  public static void writeToTargetMarket(UpdateCSV csv, TargetedAd targetedAd, String fileName) throws IOException {
    // credit to: https://www.youtube.com/watch?v=dHZaqMmQNO4
    ArrayList<String> authors = csv.getAuthors();
    ArrayList<String> reviews = csv.getReviews();

    ArrayList<String> text = new ArrayList<String>();
    int value = 0;

    for (int i = 0; i < authors.size(); i++) {
      // get the suitability rating of the user's review
      value = targetedAd.getMatch(reviews.get(i));

      // if it's below the threshold skip adding the user
      if (value < 2) {
        continue;
      }

      // add the user & their value to the text file
      text.add(authors.get(i) + "," + value);
    }

    // sorts the users from most to least suitable & removes duplicate users
    text = sort(text);
    text = removeDuplicates(text);
    
    File file = new File(fileName);

    // creates the printwriter, which will write to the text file
    PrintWriter writer = new PrintWriter(file);

    for (int i = 0; i < text.size(); i++) {
      // writes each user + suitability row in the text file
      writer.write(text.get(i) + "\n");

    }

    // closes the text file so it updates
    writer.close();
  }

  /** sorts an arraylist of strings (with an integer as a second comma seperated value) from greatest to least */
  private static ArrayList<String> sort(ArrayList<String> text) {
    // bubble sort
    for (int i = 0; i < text.size(); i++) {
      for (int j = 0; j < text.size() - i - 1; j++) {
        if (Integer.parseInt(text.get(j).split(",")[1]) < Integer.parseInt(text.get(j + 1).split(",")[1])) {
          String temp = text.get(j);
          text.set(j, text.get(j + 1));
          text.set(j + 1, temp);
        }
      }
    }
    return text;
  }

  /** removes duplicates from an arraylist of strings (with a string as a first comma seperated value)*/
  private static ArrayList<String> removeDuplicates(ArrayList<String> text) {
    for (int i = 0; i < text.size()-1; i++) {
      for (int j = i+1; j < text.size(); j++) {
        if (text.get(i).split(",")[0].equals(text.get(j).split(",")[0])) {
          text.remove(j);
          j--;
        }
      }
    }
    return text;
  }

  /** Returns a string of the users that are in a target market (in a format that can be used by DataCollector.java's prepareAdvertisement method) */
  public static String getTargetUsers (String fileName) throws IOException {
    // arraylist of each user
    String users = "";

    // creates a reader
    BufferedReader br = null;
    br = new BufferedReader(new FileReader(fileName));
    String line; 
    
    while ((line = br.readLine()) != null) {
      String[] values = line.split(",");

      // adds each target word and its value to the arraylist
      users += values[0] + " ";
    }
    
    br.close();
    // returns the users
    return users;
  }
}
