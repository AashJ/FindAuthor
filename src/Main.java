import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * The main class tests the nextToken() method and the parseDocument() method in
 * the document class. Does so by passing a string into each test. To verify if
 * the scanner.nextToken() method is working, the class prints all of the tokens
 * in the class. To verify if the parseDocument() method is working, the class
 * prints all of the tokens in each sentence, for each sentence. Finally, this
 * class tests the findAuthor class by finding the author of three mystery
 * texts. The correct author of these mystery texts is commented above the
 * method which does so.
 * 
 * @author Aashish Jain
 * 
 * @version May 12, 2015
 * 
 */
public class Main
{
    private static int numberOfTokens = 25;

    /**
     * The main method tests all of the other methods in this project.
     * 
     * @param args
     *            arguments in the main method
     * @throws IOException
     *             if the files are not found
     */
    public static void main(String[] args) throws IOException
    {
        // String to test the document
        StringReader documentTest = new StringReader(
                "This is a document, collection of sentences."
                        + " Does this throw out the correct things?"
                        + "Testing for the separation of sentences! This works.");
        // Scanner to test the document
        Scanner dScanner = new Scanner(documentTest);
        // String to test the scanner
        StringReader scannerTest = new StringReader(
                "   This is a string )(&)(*@!.   ");
        // Scanner Test
        System.out.println("Scanner Test:");
        Scanner tScanner = new Scanner(scannerTest);
        for (int i = 0; i < numberOfTokens; i++)
        {
            System.out.println(tScanner.nextToken().toString());
        }
        System.out.println("\n Document Test 1: \n");
        // Document Test
        Document d = new Document(dScanner);
        d.parseDocument();
        for (int i = 0; i < d.getSentenceCollection().size(); i++)
        {
            System.out.println(d.getSentenceCollection().get(i));
        }

        /**
         * Document statistics 1 test The expected values:
         * 
         * Average Word Length: 5.045 Type Token Ratio: 0.7727 Hapax Legomana
         * Ratio: 0.5909 Average Words Per Sentence: 5.500 Sentece Complexity:
         * 1.25
         */
        DocumentStatistics alpha = new DocumentStatistics(d);
        System.out.println("\nAverage Word Length : "
                + alpha.getAverageWordLength());
        System.out.println("Type Token Ratio : " + alpha.getTypeTokenRatio());
        System.out
                .println("Hapax Legomana Ratio : " + alpha.getHapaxLegomana());
        System.out.println("Average words per sentence : "
                + alpha.getAverageWordsPerSentence());
        System.out.println("Sentence Complexity : "
                + alpha.getSentenceComplexity());

        /**
         * This dialogue from Shakespeare's "Hamlet" is all one sentence, and
         * should therefore be returned in one line.
         */
        StringReader test = new StringReader(
                "To be, or not to be, that is the question— "
                        + "Whether 'tis Nobler in the mind to suffer"
                        + " The Slings and Arrows of outrageous Fortune,"
                        + "Or to take Arms against a Sea of troubles,"
                        + " And by opposing, end them?");
        Scanner dScannerTwo = new Scanner(test);
        Document dTwo = new Document(dScannerTwo);
        System.out.println("\n Document Test 2: \n");
        dTwo.parseDocument();
        for (int k = 0; k < dTwo.getSentenceCollection().size(); k++)
        {
            System.out.println(dTwo.getSentenceCollection().get(k));
        }

        /**
         * Document statistics 2 test The expected values:
         * 
         * Average Word Length : 3.923 Type Token Ratio : 0.7692 Hapax Legomana
         * Ratio : 0.6154 Average words per sentence : 39.00 Sentence
         * Complexity: 6.000
         */
        DocumentStatistics beta = new DocumentStatistics(dTwo);
        System.out.println("\nAverage Word Length : "
                + beta.getAverageWordLength());
        System.out.println("Type Token Ratio : " + beta.getTypeTokenRatio());
        System.out.println("Hapax Legomana Ratio : " + beta.getHapaxLegomana());
        System.out.println("Average words per sentence : "
                + beta.getAverageWordsPerSentence());
        System.out.println("Sentence Complexity : "
                + beta.getSentenceComplexity());

        ArrayList<String> statFiles = new ArrayList<String>();
        statFiles.add("src/agatha.christie.stats");
        statFiles.add("src/alexandre.dumas.stats");
        statFiles.add("src/brothers.grim.stats");
        statFiles.add("src/charles.dickens.stats");
        statFiles.add("src/douglas.adams.stats");
        statFiles.add("src/emily.bronte.stats");
        statFiles.add("src/fyodor.dostoevsky.stats");
        statFiles.add("src/james.joyce.stats");
        statFiles.add("src/jane.austen.stats");
        statFiles.add("src/lewis.caroll.stats");
        statFiles.add("src/mark.twain.stats");
        statFiles.add("src/sir.arthur.conan.doyle.stats");
        statFiles.add("src/william.shakespeare.stats");
        FindAuthor f1 = new FindAuthor("src/mystery1.txt", statFiles);
        FindAuthor f2 = new FindAuthor("src/mystery2.txt", statFiles);
        FindAuthor f3 = new FindAuthor("src/mystery3.txt", statFiles);
        FindAuthor f4 = new FindAuthor("src/mystery4.txt", statFiles);
        FindAuthor f5 = new FindAuthor("src/mystery5.txt", statFiles);
        /**
         * The Author of Mystery 1 should be: Jane Austen. The Author of Mystery
         * 2 should be: Lewis Carroll. The Author of Mystery 3 should be:
         * Charles Dickens.
         */
        System.out.println("The Author of MysteryText 1 is " + f1.findAuthor());
        System.out.println("The Author of MysteryText 2 is " + f2.findAuthor());
        System.out.println("The Author of MysteryText 3 is " + f3.findAuthor());
        System.out.println("The Author of MysteryText 4 is " + f4.findAuthor());
        System.out.println("The Author of MysteryText 5 is " + f5.findAuthor());
    }
}
