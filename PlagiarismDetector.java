package testu11;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class for detecting plagiarism between two projects.
 */
public class PlagiarismDetector {

    /**
     * Processes and compares two lists of words to determine the percentage of
     * common code.
     * 
     * @param wordsList1 the list of words from the first project
     * @param wordsList2 the list of words from the second project
     * @return the percentage of common code
     */
	
	public static double procesare(List<String> wordsList1, List<String> wordsList2) {
	    if (wordsList1 == null || wordsList2 == null) {
	        throw new IllegalArgumentException("Input lists must not be null");
	    }

	    int totalWords = wordsList1.size() + wordsList2.size();
	    if (totalWords == 0) {
	        return 0;
	    }

	    /**Check if the two word lists are identical*/ 
	    if (wordsList1.equals(wordsList2)) {
	        return 100.0;
	    }

	    List<String> commonWords = new ArrayList<>(wordsList1);
	    commonWords.retainAll(wordsList2);
	    int countCommonWords = commonWords.size();

	    double result = ((double) countCommonWords / (double) totalWords) * 100;
	    result = Math.min(100.0, result); // Ensure the result is between 0 and 100
	    result = Math.max(0.0, result);
	    return Double.parseDouble(String.format("%.2f", result).replace(',', '.')); // Replace commas with dots
	}





    /**
     * Extracts the name from the given file path.
     * 
     * @param filePath the file path
     * @return the extracted name
     */
    public static String prelucrareNume(String filePath) {
        int lastIndex = filePath.lastIndexOf(File.separator);

        if (lastIndex == -1) {
            return filePath;
        }

        return filePath.substring(lastIndex + 1);
    }
}
