import java.util.ArrayList;

/**
 * The sentence class contains a sequence of phrase objects. The sentence class
 * contains a method that allows the user to add phrase objects to the data
 * structure that holds the phrases, a method that copies the existing data
 * structure into a new data structure, and a toString method that provides a
 * string representation of the object. The dataStructure of type ArrayList is
 * used because of its Big-O for add (O(1)). Additionally, the get and set for
 * an arrayList is O(1).
 * 
 * @author Aashish Jain
 * 
 * @version February 18, 2014
 *
 */
public class Sentence
{
    /**
     * an instance variable of type ArrayList that holds the collection of
     * phrases.
     */
    private ArrayList<Phrase> phraseCollection;

    /**
     * A constructor for the Sentence object. This constructor creates a new
     * ArrayList and assigns phraseCollection to this new ArrayList.
     */
    public Sentence()
    {
        phraseCollection = new ArrayList<Phrase>();
    }

    /**
     * Adds a phrase to the phraseCollection instance variable by using the
     * ArrayList.add() method.
     * 
     * @param p
     *            The phrase to be added to phraseCollection.
     */
    public void addPhrase(Phrase p)
    {
        phraseCollection.add(p);
    }

    /**
     * Creates a new data structure of type ArrayList and copies all of the
     * existing elements into it.
     * 
     * @return the new data structure of type ArrayList with all of the copied
     *         elements.
     */
    public ArrayList copy()
    {
        ArrayList<Phrase> copy = new ArrayList();
        for (Phrase p : phraseCollection)
        {
            ArrayList<Token> tokens = p.copy();
            Phrase a = new Phrase();
            for (Token t : tokens)
            {
                a.addToken(t);
            }
            copy.add(a);
        }
        return copy;
    }

    /**
     * Overrides java.lang.Object.toString to create a string representation of
     * the phraseCollection.
     */
    public String toString()
    {
        String answer = new String();
        for (int i = 0; i < phraseCollection.size(); i++)
        {
            answer = answer + phraseCollection.get(i).toString();
        }
        return answer;
    }
}
