import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * The FindAuthor's class takes in the filePath of the document you want to find
 * the author for and statFiles to compare it to. It contains helper methods to
 * read the files and pass it into the document. The objective of this class is
 * to find the author of a given text.
 * 
 * @author Aashish Jain
 * @version March 19, 2015
 *
 */
public class FindAuthor
{
    /**
     * The document file that is checked to match any of the statistics of the
     * stat files
     */
    private String docFile;
    /**
     * The list of stat files that are compared with the document.
     */
    private ArrayList<String> statFiles;

    private double[] weights;

    /**
     * Constructor for the FindAuthor class. The constructor takes in a string
     * of the file to be analyzed and a list of existing statistics files.
     * 
     * @param dFile
     *            The file path of the document file.
     * @param sFiles
     *            An ArrayList of the file paths of the stat files.
     */
    public FindAuthor(String dFile, ArrayList<String> sFiles)
    {
        docFile = dFile;
        statFiles = sFiles;
        weights = new double[5];
        weights[0] = 11;
        weights[1] = 33;
        weights[2] = 50;
        weights[3] = 0.4;
        weights[4] = 4;
    }

    /**
     * Creates and parses the document of the instance variable of docFile.
     * 
     * @return the created and parsed document
     * 
     * @throws IOException
     *             if the file in the instanceField is not found.
     */
    private Document getDocumentFromFile() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(docFile));
        String currentString;
        String docString = "";
        while ((currentString = br.readLine()) != null)
        {
            docString += currentString + " ";
        }
        StringReader documentString = new StringReader(docString);
        Scanner scanner = new Scanner(documentString);
        Document d = new Document(scanner);
        d.parseDocument();
        return d;
    }

    /**
     * Creates an arrayList of all of the statistics of the document file that
     * was given.
     * 
     * @return an arrayList of all the statistics of the given document file.
     * 
     * @throws IOException
     *             if the file given is not found.
     */
    private ArrayList<Double> getDocumentStatistics() throws IOException
    {
        Document d = getDocumentFromFile();
        DocumentStatistics ds = new DocumentStatistics(d);
        ArrayList<Double> answer = new ArrayList<Double>();
        double averageWordLength = ds.getAverageWordLength();
        answer.add(averageWordLength);
        double TTR = ds.getTypeTokenRatio();
        answer.add(TTR);
        double HLR = ds.getHapaxLegomana();
        answer.add(HLR);
        double averageSentenceLength = ds.getAverageWordsPerSentence();
        answer.add(averageSentenceLength);
        double averageSentenceComplexity = ds.getSentenceComplexity();
        answer.add(averageSentenceComplexity);
        return answer;
    }

    /**
     * Gets the statistics in the correct order of the given statFile.
     * 
     * @param statFile
     *            The statfile to compile the statistics for.
     * @return An ArrayList of the statistics in the statFile.
     * @throws IOException
     *             if the file is not found.
     */
    private ArrayList<Double> getStatsOfStatFile(String statFile)
            throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(statFile));
        br.readLine();
        ArrayList<Double> answer = new ArrayList<Double>();
        for (int i = 0; i < 5; i++)
        {
            String stat = br.readLine();
            double val = Double.parseDouble(stat);
            answer.add(val);
        }
        return answer;
    }

    /**
     * Prints out the string of the author of the text passed into the
     * FindAuthor object by comparing the statistics in that file to all of the
     * statistics in the stat files. The author whos stats are closest is
     * returned.
     * 
     * @return A String of the found author.
     * 
     * @throws IOException
     *             if the file is not found.
     */
    public String findAuthor() throws IOException
    {
        ArrayList<Double> docStats = getDocumentStatistics();
        ArrayList<Double> answers = new ArrayList<Double>();
        for (int i = 0; i < statFiles.size(); i++)
        {
            ArrayList<Double> statF = getStatsOfStatFile(statFiles.get(i));
            double total = 0;
            for (int k = 0; k < statF.size(); k++)
            {
                total += (Math.abs(docStats.get(k) - statF.get(k)))
                        * weights[k];
            }
            answers.add(total);
        }
        int indexOfSmallest = 0;
        for (int index = 0; index < answers.size(); index++)
        {
            if (answers.get(index) < answers.get(indexOfSmallest))
            {
                indexOfSmallest = index;
            }
        }
        BufferedReader br = new BufferedReader(new FileReader(
                statFiles.get(indexOfSmallest)));
        return br.readLine();
    }
}
