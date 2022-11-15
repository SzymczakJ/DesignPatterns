import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;

public class ConsonantCounter implements SentenceReaderObserver {

    private String consonantString = "bcdfgjklmnpqstvxzhrwyBCDFGJKLMNPQSTVXZHRWY";
    int consonantCount = 0;


    @Override
    public void notifyObserver(ArrayList<String> wordList) throws IOException {
        String fileName = " consonantCounter";
        try (
                var fileWriter = new FileWriter(fileName, true);
                var writer = new BufferedWriter(fileWriter);
        ) {
            consonantCount = 0;
            wordList.forEach(word -> {
                consonantCount += countVovels(word);
            });
            writer.write(Integer.toString(consonantCount));
            writer.newLine();

        } catch (IOException e) {
            System.err.println("Nie udało się zapisać pliku " + fileName);
        }
    }

    private int countVovels(String word) {
        int consonantCount = 0;
        CharacterIterator iterator = new StringCharacterIterator(word);
        while (iterator.current() != CharacterIterator.DONE) {
            if (consonantString.contains("" + iterator.current())) {
                consonantCount++;
            }
            iterator.next();
        }
        return consonantCount;
    }
}
