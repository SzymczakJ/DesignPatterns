import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SentenceReader sentenceReader = new SentenceReader();
        sentenceReader.addObserver(new WordCounter());
        sentenceReader.addObserver(new ConsonantCounter());
        sentenceReader.addObserver(new VowelCounter());
        try {
            sentenceReader.readFile("text");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}