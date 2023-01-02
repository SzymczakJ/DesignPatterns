import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class VowelCounter implements SentenceReaderObserver {

    private String vovelsString = "aeiouyAEIOUY";
    int vovelsCount = 0;


    @Override
    public void notifyObserver(ArrayList<String> wordList) throws IOException {
        String fileName = "vovelCounter";
        try (
                var fileWriter = new FileWriter(fileName, true);
                var writer = new BufferedWriter(fileWriter);
        ) {
            vovelsCount = 0;
            wordList.forEach(word -> {
                vovelsCount += countVovels(word);
            });
            writer.write(Integer.toString(vovelsCount));
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }
    }

    private int countVovels(String word) {
        int vovelCount = 0;
        CharacterIterator iterator = new StringCharacterIterator(word);
        while (iterator.current() != CharacterIterator.DONE) {
            if (vovelsString.contains("" + iterator.current())) {
                vovelCount++;
            }
            iterator.next();
        }
        return vovelCount;
    }
}
