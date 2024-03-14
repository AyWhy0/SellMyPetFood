import java.io.IOException;

//import com.opencsv.CSVWriter;

// jsoup
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// to write in the text file
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Main {
  public static void main(String[] args) throws IOException {

    UpdateCSV updateCSV = new UpdateCSV();

    // advertise to people who have curly hair

    TargetedAd targetedAd = new TargetedAd("targetWords.txt");
    TargetMarket.writeToTargetMarket(updateCSV, targetedAd, "targetmarket.txt");
    DataCollector.prepareAdvertisement("individualAdvertisements.txt", TargetMarket.getTargetUsers("targetmarket.txt"), "advertisement.txt");

    System.out.println("Round 1 Advertisements done...");

    // advertise to people who have thin / frizzy hair

    TargetedAd targetedAd2 = new TargetedAd("targetWords2.txt");
    TargetMarket.writeToTargetMarket(updateCSV, targetedAd2, "targetmarket2.txt");
    DataCollector.prepareAdvertisement("individualAdvertisements2.txt", TargetMarket.getTargetUsers("targetmarket2.txt"), "advertisement2.txt");
    
    System.out.println("Round 2 Advertisements done...");

    // get the user's advertisement
    UserInput userInput = new UserInput();
    userInput.getUserFeedback();

    TargetedAd targetedAdUser = new TargetedAd("targetWordsUser.txt");
    TargetMarket.writeToTargetMarket(updateCSV, targetedAdUser, "targetmarketUser.txt");
    DataCollector.prepareAdvertisement("individualAdvertisementsUser.txt", TargetMarket.getTargetUsers("targetmarketUser.txt"), "advertisementUser.txt");


    
  }
}
