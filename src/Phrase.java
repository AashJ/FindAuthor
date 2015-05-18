import java.util.ArrayList;

/**
 * The phrase class is an object that consists of a group of tokens, each
 * containing a single word. The phrase class stores tokens in a data structure.
 * An ArrayList is used because it is O(1) for adding an element. Additionally,
 * the get and set for an arrayList is O(1).
 * 
 * @author Aashish Jain
 * 
 * @version February 25, 2015
 */
public class Phrase
{
    // the data structure that holds the collection of tokens
    /**
     * An ArrayList is used because it is O(1) for adding an element.
     */
    private ArrayList<Token> tokenCollection;

    /**
     * The constructor for the Phrase object initializes an ArrayList of tokens.
     */
    public Phrase()
    {
        tokenCollection = new ArrayList<Token>();
    }

    /**
     * Adds a token to the tokenCollection instance variable by using the
     * arrayList add method (O(1)).
     * 
     * @param token
     */
    public void addToken(Token token)
    {
        tokenCollection.add(token);
    }

    /**
     * Copies all of the elements from the instance variable (tokenCollection)
     * which holds all of the tokens into a new data structure of type
     * ArrayList.
     * 
     * @return a copy of the instance variable that holds the tokens.
     */
    public ArrayList copy()
    {
        ArrayList<Token> copy = new ArrayList();
        for (Token t : tokenCollection)
        {
            copy.add(t);
        }
        return copy;
    }

    /**
     * Overrides java.lang.Object.toString to print out all of the tokens in the
     * tokenCollection.
     */
    public String toString()
    {
        String word = new String();
        for (Token t : tokenCollection)
        {
            word = word + t.toString() + " ";
        }
        return word;
    }

}
