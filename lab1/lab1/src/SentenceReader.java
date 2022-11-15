import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SentenceReader {
    private ArrayList<SentenceReaderObserver> observers = new ArrayList<SentenceReaderObserver>();

    public void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        ArrayList<String> sentence = new ArrayList<>();
        while (fileReader.hasNext()) {
            String nextChar = fileReader.next();
            if (nextChar.contains(".")) {
                observers.forEach(observer -> {
                    try {
                        observer.notifyObserver(sentence);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                sentence.clear();
            }
            sentence.add(nextChar);
        }
    }

    public void addObserver(SentenceReaderObserver observer) {
        observers.add(observer);
    }
}
