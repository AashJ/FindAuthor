import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * The DocumentStatistics class takes in a document class and is able to
 * calculate the average word length, the type-token ratio, the hapax legomana
 * ratio, the average number of words per sentence, and the average number of
 * phrases per sentence.
 * 
 * The type-token ratio is the amount of different words in a document to the
 * total number of words in a document.
 * 
 * The hapax legomana ratio is the number of unique words in a document to the
 * words appearing only once in the document.
 * 
 * @author Aashish Jain
 *
 */
public class DocumentStatistics
{
    /**
     * The only state variable of the document statistics class, this is the
     * document for the statistics to be calculated for.
     */
    private Document d;

    /**
     * The constructor for the documentStatstics class takes in a document and
     * parses the document to add sentences to its sentenceCollection.
     * 
     * @param doc
     *            The document to find the statistics for
     */
    public DocumentStatistics(Document doc)
    {
        d = doc;
        d.parseDocument();
    }

    /**
     * This method returns the average word length from the document that was
     * passed through the documentStatistics class. To do so, this method adds
     * up the length of each word in the sentenceCollection arrayList that is
     * stored in the document, and then divides the returned number by the
     * amount of words.
     * 
     * @precondition No preconditions are associated with this method, but if
     *               the number of words in the document is 0, the program will
     *               return “NaN,” for not a number.
     * @postcondition No post conditions are associated with this method.
     * 
     * @return This method returns a double of average length of the words in
     *         the document.
     */
    public double getAverageWordLength()
    {
        int totalWords = 0;
        int totalWordLength = 0;
        for (Sentence s : d.getSentenceCollection())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    totalWordLength += t.getStr().length();
                }
            }
        }

        double answer = (double) totalWordLength / totalWords;
        return answer;
    }

    /**
     * This method returns the ratio of the number of unique words in the
     * document to the number of words in the document. To do so, this method
     * goes through every word in the document, adding it to a set of words if
     * it is unique. Thus, if the word is already contained in the set, it is
     * not added. To obtain the ratio, the number of elements in the set is
     * divided by the total number of words in the document.
     * 
     * @precondition No preconditions are associated with this method, but if
     *               the number of words in the document is 0, the program will
     *               return “NaN,” for not a number.
     * @postcondition No post conditions are associated with this method.
     * 
     * @return This method returns a double of the ratio of unique words in the
     *         document to total number of words in the document.
     */
    public double getTypeTokenRatio()
    {
        Set<String> uniqueWords = new HashSet<String>();
        int totalWords = 0;
        for (Sentence s : d.getSentenceCollection())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    String word = t.getStr();
                    word.toLowerCase();
                    uniqueWords.add(word);
                }
            }
        }
        int numberOfUniqueWords = uniqueWords.size();
        double answer = (double) numberOfUniqueWords / (double) totalWords;
        return answer;
    }

    /**
     * This method returns the ratio of words that have appeared once to the
     * total number of words in the document. To do so, this method creates two
     * sets. One set is a set of the words that have appeared once. The other
     * set is the set of words that have appeared more than once. If a word is
     * in neither of the sets, the word is added to the set of words that appear
     * once. If the word is already in the set of words that have appeared once,
     * then it is added to the set of words that have appeared more than once,
     * and taken out of the set of words that have appeared once. If a word is
     * already in the set of words that have appeared more than once then the
     * word is discarded. To get the number of words that have appeared once,
     * simply count the elements in the words that have appeared once set. To
     * get the ratio, divide the number of words that have appeared once with
     * the total number of words in the document.
     * 
     * @precondition No preconditions are associated with this method, but if
     *               the number of total words in the document is 0, the program
     *               will return “NaN,” for not a number.
     * @postcondition No post conditions are associated with this method.
     * 
     * @return This method returns the ratio of words that have appeared once to
     *         the total number of words in the document.
     */
    public double getHapaxLegomana()
    {
        Set<String> appearedOnce = new HashSet<String>();
        Set<String> appearedMoreThanOnce = new HashSet<String>();
        int totalWords = 0;
        for (Sentence s : d.getSentenceCollection())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
                for (Token t : tokens)
                {
                    String word = t.getStr().toLowerCase();
                    if (!appearedOnce.contains(word)
                            && !appearedMoreThanOnce.contains(word))
                    {
                        appearedOnce.add(word);
                    } else if (appearedOnce.contains(word))
                    {
                        appearedMoreThanOnce.add(word);
                        appearedOnce.remove(word);
                    }
                }
            }
        }
        int oneAppearance = appearedOnce.size();
        double answer = (double) oneAppearance / (double) totalWords;
        return answer;
    }

    /**
     * This method returns the average number of words per sentence. To do so,
     * this function adds up the number of words total in the document, and
     * divides it by the number of elements in the document’s
     * sentenceCollection.
     * 
     * @precondition No preconditions are associated with this method, but if
     *               the number of sentences in the document is 0, the program
     *               will return “NaN,” for not a number.
     * @postcondition No post conditions are associated with this method.
     * 
     * @return This method returns a double of the total number of words in the
     *         document divided by the number of sentences in the document.
     */
    public double getAverageWordsPerSentence()
    {
        int totalWords = 0;
        int numberOfSentences = d.getSentenceCollection().size();
        for (Sentence s : d.getSentenceCollection())
        {
            ArrayList<Phrase> phrases = s.copy();
            for (Phrase p : phrases)
            {
                ArrayList<Token> tokens = p.copy();
                totalWords += tokens.size();
            }
        }
        double answer = (double) totalWords / (double) numberOfSentences;
        return answer;
    }

    /**
     * This method returns the average number of phrases per sentence. To do so,
     * this method accesses the document’s sentenceCollection and checks the
     * amount of phrases in each element of the sentenceCollection. Then, adds
     * up the total number of phrases and divides it by the number of sentences.
     * 
     * @precondition No preconditions associated with this method, but if the
     *               number of sentences is 0, the program will return “NaN,”
     *               for not a number.
     * @postcondition No post conditions associated with this method.
     * 
     * @return This method returns a double of the number of phrases in the
     *         document divided by the number of sentences in the document.
     */
    public double getSentenceComplexity()
    {
        int totalPhrases = 0;
        int totalSentences = d.getSentenceCollection().size();
        for (Sentence s : d.getSentenceCollection())
        {
            ArrayList<Phrase> phrases = s.copy();
            totalPhrases += phrases.size();
        }
        double answer = (double) totalPhrases / (double) totalSentences;
        return answer;
    }

}
