import java.io.IOException;
import java.util.ArrayList;

public interface SentenceReaderObserver {
    public void notifyObserver(ArrayList<String> wordList) throws IOException;
}
