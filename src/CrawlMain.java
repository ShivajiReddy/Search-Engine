//public class CrawlMain {
//    public static void main (String args[]) {
//        //String URL = args[1];
//        // System.out.println("Hello");
//         String URL = "https://www.ucr.edu/";
//        //String URL = "https://en.wikipedia.org/wiki/California";
//        Crawler c = new Crawler(URL);
//        c.crawlUrl();
//    }
//}

import java.util.ArrayList;

public class CrawlMain {
    public static void main (String args[]) throws InterruptedException {

      // uncomment this for running through command
      /*  String URL = args[1];
        MultiCrawler cmain = new MultiCrawler(URL);
        Integer no_of_threads = Integer.valueOf(args[2]);
        ArrayList<MultiCrawler> child_crawler = new ArrayList<>(no_of_threads);
        for(int thread_counter=0; thread_counter<no_of_threads;thread_counter++){
            child_crawler.add(new MultiCrawler());
        }
        for(int thread_counter=0; thread_counter<no_of_threads;thread_counter++){
            child_crawler.get(thread_counter).getThread().join();
        }
        */
        long startTime = System.currentTimeMillis();
        //String URL = "https://www.ucr.edu/";
        String URL = "https://en.wikipedia.org/wiki/California";
        MultiCrawler cmain = new MultiCrawler(URL);    // main thread


        MultiCrawler cmain1 = new MultiCrawler();
        MultiCrawler cmain2 = new MultiCrawler();
        MultiCrawler cmain3 = new MultiCrawler();
        MultiCrawler cmain4 = new MultiCrawler();
        MultiCrawler cmain5 = new MultiCrawler();
        MultiCrawler cmain6 = new MultiCrawler();
        MultiCrawler cmain7 = new MultiCrawler();
        MultiCrawler cmain8 = new MultiCrawler();
        System.out.println("Waiting");
        cmain.getThread().join();
        cmain1.getThread().join();
        cmain2.getThread().join();
        cmain3.getThread().join();
        cmain4.getThread().join();
        cmain5.getThread().join();
        cmain6.getThread().join();
        cmain7.getThread().join();
        cmain8.getThread().join();
        System.out.println("Completed");
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
