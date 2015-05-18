/**
 * A token class represents a token object. A token object could be any object
 * of a TOKEN_TYPE. A token object holds a string of the token and the type of
 * the token.
 * 
 * @author Aashish
 * @version February 16, 2015
 *
 */
public final class Token
{
    // the type of the token
    private Scanner.TOKEN_TYPE type;
    // the string of the token
    private String str;

    /**
     * A constructor for the token class. It takes in a type and a string to
     * create a token.
     * 
     * @param types
     *            The type of this token
     * @param string
     *            The string of this token
     */
    public Token(Scanner.TOKEN_TYPE types, String string)
    {
        str = string;
        type = types;
    }

    /**
     * returns the type of this token.
     * @return The type of this token object
     */
    public Scanner.TOKEN_TYPE getType()
    {
        return type;
    }

    /**
     * returns the string of this token.
     * @return The string of this token object
     */
    public String getStr()
    {
        return str;
    }

    /**
     * Overwrites the toString method to include the type of the token and then
     * the string.
     */
    public String toString()
    {
        return ("(" + getType() + ") " + str);
    }

    /**
     * Checks if two tokens are equal to eachother.
     * 
     * @param other
     *            The other token to be checked
     * @return true if the tokens are equal, or false otherwise.
     */
    public boolean equals(Token other)
    {
        return (getStr().equals(other.getStr()) && getType().equals(
                other.getType()));
    }

}
