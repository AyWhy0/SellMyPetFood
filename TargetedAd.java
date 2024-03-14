import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;


public class TargetedAd {
  private ArrayList<String> targetWords;
  
  public TargetedAd (String fileName){
    // arraylist of each target word and its value (in string form)
    ArrayList<String> valuesList = new ArrayList<String>();

    // reads targetwords.txt
    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader(fileName));
      String line; 
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");

        // adds each target word and its value to the arraylist
        for (String value : values) {
          valuesList.add(value.trim());
        }
      }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                // closes the file
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
      }
    // returns the arraylist
    targetWords = valuesList;
  }

  /** Returns the string arraylist of target words from the targetwords text file */
  public ArrayList<String> getTargetWords() {
    return targetWords;
  }

  /** Returns the suitability rating for a review based on the targetwords */
  public int getMatch(String review) {
    // "weight" of the review and if it matches the advertisement
    int match = 0;

    // if a target word is found in the review, its value is added to match
    for (int i = 0; i < targetWords.size()/2; i+=2){
      if (review.contains(targetWords.get(i))){
        match += Integer.parseInt(targetWords.get(i+1));;
      }
    }
    return match;
  }
}
