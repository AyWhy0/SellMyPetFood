// import static org.junit.jupiter.api.Assertions.assertEquals;

// import org.junit.jupiter.api.Test;
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


public class UpdateCSV {

  private ArrayList<String> reviews;
  private ArrayList<String> authors;
  private ArrayList<String> stars;
  
  public UpdateCSV() throws IOException {
    // initializes the url string variable
    String url = "https://www.walmart.com/reviews/product/50229053";

    // creates an arraylist of review pages
    ArrayList<Document> documents = new ArrayList<Document>();
    for (int i = 1; i <= 4; i++) {
      // adds each document from each review website page to the arraylist
      url = "https://www.walmart.com/reviews/product/50229053?page=" + i;
      documents.add(Jsoup.connect(url).get());
    }

    // creates arraylists of review & author elements respectively from each of documents' pages
    ArrayList<Elements> reviewElements = new ArrayList<Elements>();
    ArrayList<Elements> authorElements = new ArrayList<Elements>();
    ArrayList<Elements> starElements = new ArrayList<Elements>();

    // updates each of the previous arraylists with their respective elements
    for (int i = 0; i < documents.size(); i++) {
      reviewElements.add(documents.get(i).getElementsByClass("tl-m mb3 db-m"));
      authorElements.add(documents.get(i).getElementsByClass("f6 gray pr2 mb2"));
      starElements.add(documents.get(i).getElementsByClass("w_iUH7"));
    }

    // creates arraylists of authors and their reviews (in string form)
    authors = new ArrayList<String>();
    reviews = new ArrayList<String>();
    stars = new ArrayList<String>();

    // updates the string arraylist of reviews
    for (Elements elements : reviewElements) {
      for (Element element : elements) {
        // lots of reviews have commas (:[) so we need to add double quotes around each of the reviews so that it doesn't create a new column by accident
        // credit to: https://stackoverflow.com/questions/4617935/is-there-a-way-to-include-commas-in-csv-columns-without-breaking-the-formatting
        reviews.add("\"" + element.text() + "\"");
      }
    }

    // updates the string arraylist of authors
    for (Elements elements : authorElements) {
      for (Element element : elements) {
        authors.add(element.text());
      }
    }

    // updates the string arraylist of reviews
    for (Elements elements : starElements) {
      for (Element element : elements) {
        // some elements of class "w_iUH7" are not star reviews, so an if statement is needed to check if the element is a star review
        if (element.text().length() == "5 out of 5 stars review".length())
          // specifically adds the first character to the arraylist, which is the star rating out of 5 as an integer
          stars.add(element.text().substring(0,1));
      }
    }
  }
  
  public void writeToCSV() throws IOException {

    // credit to: https://www.youtube.com/watch?v=dHZaqMmQNO4
    // updates the csv file with the reviews and authors
    File file = new File("Reviews.csv");

    // creates the printwriter, which will write to the csv file
    PrintWriter writer = new PrintWriter(file);

    // writes the csv header
    writer.write("reviewer name,review,number of stars\n");

    for (int i = 0; i < authors.size(); i++) {
      // writes each author + review row in the csv
      writer.write(authors.get(i) + "," + reviews.get(i) + "," + stars.get(i) + "\n");
    }

    // closes the csv file so it updates
    writer.close();
   }

  /** a method to print the size of each arraylist (mainly for testing) */
  public void printSizes() {
    System.out.println(stars.size());
    System.out.println(authors.size());
    System.out.println(reviews.size());
  }

  /** returns a string arraylist of reviews */
  public ArrayList<String> getReviews() {
    return reviews;
  }

  /** returns a string arraylist of authors */
  public ArrayList<String> getAuthors() {
    return authors;
  }

  /** returns a string arraylist of stars out of 5 */
  public ArrayList<String> getStars() {
    return stars;
  }
}
