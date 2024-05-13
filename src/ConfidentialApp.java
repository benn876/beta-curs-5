import java.util.Arrays;

public class ConfidentialApp {
    public static void main(String[] args) {
        int charCount = countChar("testatre", 't');
        System.out.println(charCount);

        String redacted = redact("word");
        System.out.println(redacted);

        String[] words = {"Ana", "are", "mere"};
        boolean isContained = containsWord(words, "are");
        System.out.println(isContained);

        String[] splitArray = splitString("Ana are mere Merele sunt multe");
        Arrays.stream(splitArray).forEach(System.out::println);

        String result = confidential("Ana are mere multe mere bune", new String[]{"mere", "Ana"});
        System.out.println(result);
    }

    private static String confidential(String input, String[] sensitiveWords) {
        String[] inputArray = splitString(input);
        for (int index = 0; index < inputArray.length; index++) {
            if (containsWord(sensitiveWords, inputArray[index])) {
                inputArray[index] = redact(inputArray[index]);
            }
        }

        return String.join(" ", inputArray);
    }

    private static String[] splitString(String request) {
        return request.split(" ");
    }

    private static boolean containsWord(String[] request, String toBeFound) {
        for (String word : request) {
            if (word.equals(toBeFound)) {
                return true;
            }
        }
        return false;
    }

    private static String redact(String request) {
        return request.replaceAll(".", "*");
    }

    private static int countChar(String input, char foundChar) {
        int count = 0;
        for (int index = 0; index < input.length(); index++) {
            if (input.charAt(index) == foundChar) {
                count++;
            }
        }
        return count;
    }
}