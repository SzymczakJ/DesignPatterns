import java.io.*;
import java.util.ArrayList;

public class WordCounter implements SentenceReaderObserver {

    @Override
    public void notifyObserver(ArrayList<String> wordList) throws IOException {
        String fileName = "wordCounter";
        try (
                var fileWriter = new FileWriter(fileName, true);
                var writer = new BufferedWriter(fileWriter);
        ) {
            writer.write(Integer.toString(wordList.size()));
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }
    }
}
