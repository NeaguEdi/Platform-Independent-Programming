package testu11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Utility class for file operations.
 */
public class FileUtils {

    /**
     * Extracts code from Java files in the specified directory.
     * 
     * @param pathOriginal the path to the directory
     * @return a list of strings representing words in the Java files
     * @throws IOException if an I/O error occurs
     */
    public static List<String> obtinereCod(String pathOriginal) throws IOException {
        List<String> wordsList = new ArrayList<>();

        Files.walk(Paths.get(pathOriginal))
                .filter(new Predicate<Path>() {
                    @Override
                    public boolean test(Path file) {
                        return Files.isRegularFile(file);
                    }
                })
                .filter(new Predicate<Path>() {
                    @Override
                    public boolean test(Path file) {
                        return file.toString().endsWith(".java");
                    }
                })
                .forEach(new Consumer<Path>() {
                    public void accept(Path file) {
                        try {
                            /**Read content of the file*/ 
                            byte[] cod = Files.readAllBytes(file);
                            String fileContent = new String(cod);

                            /**Split the string into words using whitespace as delimiter*/ 
                            String[] wordsArray = fileContent.split("\\s+");

                            /**Add words to the list*/
                            for (String word : wordsArray) {
                                wordsList.add(word);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        return wordsList;
    }
}
