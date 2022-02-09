import java.io.*;

public class WriteToFile {

    public void fileWrite(String parsedData ,Integer counter){
        try {
            String fileName = "/Users/shivaji/Desktop/IRData/document_" + counter +".txt";
           // String fileName = "/Users/avinash/Documents/Retrieval/Data/"+ title + "/document_" + counter +".txt";
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
            // System.out.println("Writing to file "+ fileName);
            bufferedWriter.write(parsedData);
            bufferedWriter.close();
        }catch (Exception e) {
            System.out.println("Error Writing to a file");
        }
    }

}
