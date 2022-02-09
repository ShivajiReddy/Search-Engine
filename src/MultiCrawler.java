import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// multi threaded crawler implementation
public class MultiCrawler implements Runnable {

    private String URL;
    // Queue of links
    static Queue<String> links;
    // visited link
    static HashMap<String , Integer> visitedLinks;
    static Integer counter;
    Thread mainThread;

    // main constructor for parent thread
    public MultiCrawler(String URL) {
        this.URL = URL;
        links = new LinkedList<>();
        visitedLinks = new HashMap<>();
        counter = 0;
        links.add(URL);
        mainThread = new Thread(this);
        mainThread.start();
    }

    // overloaded constructor for child threads
    public MultiCrawler() {
        mainThread = new Thread(this);
        mainThread.start();
    }

    @Override
    public void run() {
        System.out.println("Crawler Started");
        loopCrawl();
    }

    public void loopCrawl() {
        System.out.println("Thread " + mainThread.getName() + "started processig ------------------------------");
        System.out.println("loop crawl");
        try {
            while(!links.isEmpty() &&  counter<1000) {
              //  System.out.println("parsing the page now");
                // https://stackoverflow.com/questions/24475816/jsoup-404-error - handling error
                // ignore http errors and allow only those with 200 status code
                String peekLink = getPeekLink();   // synchronised method
                Connection connection = Jsoup.connect(peekLink).ignoreHttpErrors(true);
                Document doc =  connection.get();

                if(connection.response().statusCode() == 200) {   // need to check for pages with error codes
                 //   System.out.println("Peek Link: " + peekLink);
                    //  System.out.println(doc.outerHtml());
                    for(Element link : doc.getElementsByTag("a")) {
                        //  System.out.println("Link : " + link);
                        String innerLink = link.attr("abs:href");
                        String innerText = link.text();

                        // add to queue if link is not visited
                        //System.out.println("link now: " + innerLink);
                        if(!visitedLinks.containsKey(innerLink) && !innerLink.isEmpty()) {   // need to check some conditions
//                            if(!innerLink.contains("https") || !innerLink.contains("http")) {
//                                innerLink = hostLink.concat(innerLink);
//                                System.out.println("link now: " + innerLink);
//                            }
                            links.add(innerLink);
                            visitedLinks.put(innerLink , 1);    // need to stem this for checking duplicates
                        }else {
                          //  System.out.println("Visited link : " + innerLink + " skipping the link");
                        }
                    }
                    System.out.println("-------------------------------------");
                    incrementCounter();   // synchronised method

                    // get data and write to file
                    // Elements textData = doc.getElementsByTag("");
                    Elements textData = doc.select(".mw-content-ltr p, .mw-content-ltr li");
                    Elements title = doc.select("title");
                    // Element textData = doc.getElementById("webpage-content");
                    //  System.out.println("textData :"  + textData);
                    // object to write data to files
                    WriteToFile w = new WriteToFile();
                    w.fileWrite(String.valueOf(textData), counter);

                }else{
                    // skipping the link when response code is other than 200
                    System.out.println("Cannot parse link : Status code returned " + connection.response().statusCode());
                }
            }
        }catch (Exception e) {
            System.out.println("exception crawling: " + e);
        }
    }

    // returns peek link on queue
    public synchronized String getPeekLink() {
        String peekLink = links.peek();
        links.poll();
        return peekLink;
    }

    // increment counter synchronously
    public synchronized void incrementCounter() {
        counter = counter+1;
    }

    // return this thread for join
    public Thread getThread() {
        return mainThread;
    }
}
