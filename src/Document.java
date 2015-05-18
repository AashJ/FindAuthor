import java.util.ArrayList;

/**
 * A document is responsible for reading an input stream, one token at a time
 * and separating the input into sentences. A sentence is defined as a
 * collection of words that ends with an END_OF_SENTENCE type token (refer to
 * token class). An ArrayList data structure was used for sentence collection
 * because of its add function is O(1). Additionally, to get an element and to
 * set an element is O(1).
 * 
 * @author Aashish Jain
 * 
 * @version February 24, 2014
 *
 */
public class Document
{
    /**
     * The array list in which the sentences are stored.
     */
    private ArrayList<Sentence> sentenceCollection;
    /**
     * The scanner which parses tokens.
     */
    private Scanner in;
    /**
     * A reference to the current token.
     */
    private Token currentToken;

    /**
     * A document constructor takes in a scanner and instantiates the sentence
     * collection instance variable, the scanner instance variable, and gets the
     * first token.
     * 
     * @param in
     *            The scanner to be put into the document class
     */
    public Document(Scanner in)
    {
        sentenceCollection = new ArrayList<Sentence>();
        this.in = in;
        getNextToken();
    }

    /**
     * Updates current token to the nextToken in the document. Does so by
     * calling the scanner.nextToken() method.
     */
    public void getNextToken()
    {
        currentToken = in.nextToken();
    }

    /**
     * Updates current token to the nextToken in the document. Only does so if
     * the input parameter is equal to the current token. Else, throws a
     * RuntimeException detailing the string that was passed in and the
     * currentToken.
     * 
     * @param t
     *            The token to check if equal to the currentToken.
     */
    public void eat(Token t)
    {
        if (currentToken.equals(t))
        {
            getNextToken();
        } 
        else
        {
            throw new RuntimeException(t.toString() + " does not match "
                    + currentToken.toString());
        }
    }

    /**
     * Creates a new phrase which consists of words until the end of a phrase
     * token.
     * 
     * @param t
     *            The token to start the phrase.
     * @return an object of the phrase class that holds all of the words in this
     *         phrase.
     */
    public Phrase parsePhrase(Token t)
    {
        // creates new phrase
        Phrase p = new Phrase();
        // Checks if token t is equal to an ending character
        while (!t.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE)
                && !t.getType().equals(Scanner.TOKEN_TYPE.END_OF_SENTENCE)
                && !t.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            // add the token to the phrase if it is a word, and then continue on
            if (t.getType().equals(Scanner.TOKEN_TYPE.WORD))
            {
                p.addToken(t);
            }
            eat(t);
            t = currentToken;
        }
        return p;

    }

    /**
     * Creates an object of type sentence to hold multiple phrases. The sentence
     * ends when the currentToken is of type end of sentence.
     * 
     * @return an object of type sentence that holds multiple phrases.
     */
    public Sentence parseSentence()
    {
        // Creates a new sentence
        Sentence s = new Sentence();
        // Checks for the end of a sentence
        while (!currentToken.getType().equals(
                Scanner.TOKEN_TYPE.END_OF_SENTENCE)
                && !currentToken.getType().equals(
                        Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            s.addPhrase(parsePhrase(currentToken));
            if (currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_PHRASE))
            {
                eat(currentToken);
            }
        }
        return s;
    }

    /**
     * Adds sentences to the sentenceCollection by calling parseSentece(), which
     * calls parsePhrase on the currentToken. Continues to add sentences to the
     * sentence collection until the end of the file is reached.
     */
    public void parseDocument()
    {
        // continues until the end of the file is reached
        while (!currentToken.getType().equals(Scanner.TOKEN_TYPE.END_OF_FILE))
        {
            sentenceCollection.add(parseSentence());
            eat(currentToken);
        }
    }

    /**
     * returns this object's sentence collection.
     * 
     * @return this object's sentenceCollection
     */
    public ArrayList<Sentence> getSentenceCollection()
    {
        return sentenceCollection;
    }
}
