import java.io.IOException;
import java.io.Reader;

/**
 * A Scanner is responsible for reading an input stream, one character at a
 * time, and separating the input into tokens. A token is defined as: 1. A
 * 'word' which is defined as a non-empty sequence of characters that begins
 * with an alpha character and then consists of alpha characters, numbers, the
 * single quote character "'", or the hyphen character "-". 2. An
 * 'end-of-sentence' delimiter defined as any one of the characters ".", "?",
 * "!". 3. An end-of-file token which is returned when the scanner is asked for
 * a token and the input is at the end-of-file. 4. A phrase separator which
 * consists of one of the characters ",",":" or ";". 5. A digit. 6. Any other
 * character not defined above.
 * 
 * @author Mr. Page
 *
 */

public class Scanner
{
    private Reader in;
    private String currentChar;
    private boolean endOfFile;

    // define symbolic constants for each type of token
    public static enum TOKEN_TYPE
    {
        WORD, END_OF_SENTENCE, END_OF_FILE, END_OF_PHRASE, DIGIT, UNKNOWN
    };

    /**
     * Constructor for Scanner objects. The Reader object should be one of 1. A
     * StringReader 2. A BufferedReader wrapped around an InputStream 3. A
     * BufferedReader wrapped around a FileReader The instance field for the
     * Reader is initialized to the input parameter, and the endOfFile indicator
     * is set to false. The currentChar field is initialized by the getNextChar
     * method.
     * 
     * @param in
     *            is the reader object supplied by the program constructing this
     *            Scanner object.
     */
    public Scanner(Reader in)
    {
        this.in = in;
        endOfFile = false;
        getNextChar();
    }

    /**
     * The getNextChar method attempts to get the next character from the input
     * stream. It sets the endOfFile flag true if the end of file is reached on
     * the input stream. Otherwise, it reads the next character from the stream
     * and converts it to a Java String object. postcondition: The input stream
     * is advanced one character if it is not at end of file and the currentChar
     * instance field is set to the String representation of the character read
     * from the input stream. The flag endOfFile is set true if the input stream
     * is exhausted.
     */
    private void getNextChar()
    {
        try
        {
            int inp = in.read();
            if (inp == -1)
                endOfFile = true;
            else
                currentChar = "" + (char) inp;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Gets the next character in the document by checking to see if the input
     * string is equal to the currentCharacter. If it is, it advances the
     * currentCharacter by getting the next character.
     * 
     * @param str
     *            the string to be eaten (advances this character)
     */
    private void eat(String str)
    {
        if (str.equals(currentChar))
        {
            getNextChar();
        }
    }

    /**
     * Checks if the single character string is a letter by checking the compare
     * value to a. If the value is greater than or equal to 0 and less than or
     * equal to 25, the value should be a character.
     * 
     * @param str
     *            The string to be checked
     * @return true if the str is a letter, or false otherwise.
     */
    private boolean checkLetter(String str)
    {
        return (str.compareTo("a") >= 0 && str.compareTo("a") <= 25)
                || (str.compareTo("A") >= 0 && str.compareTo("Z") <= 25);
    }

    /**
     * Checks if the single character string is a digit by comparing to 0. If
     * the compare value is greater than or equal to 0 and less than or equal to
     * 9, the value is a digit.
     * 
     * @param str
     *            The string to be checked
     * @return true if the character is a digit, or false otherwise.
     */
    private boolean checkDigit(String str)
    {
        return (str.compareTo("0") >= 0 && str.compareTo("0") <= 9);
    }

    /**
     * Checks if the single character is a special character (a single quote or
     * a dash).
     * 
     * @param str
     *            The string to be checked
     * @return true if the character is a special character, or false otherwise.
     */
    private boolean checkSpecial(String str)
    {
        return (str.compareTo("\"") == 0 || str.compareTo("-") == 0);
    }

    /**
     * Checks if the single character is a phraseTerminator (a comma, colon or
     * semicolon).
     * 
     * @param str
     *            The string to be checked
     * @return true if the character is a phraseTerminator, or false otherwise.
     */
    private boolean checkPhraseTerminator(String str)
    {
        return (str.compareTo(",") == 0 || str.compareTo(":") == 0 || str
                .compareTo(";") == 0);
    }

    /**
     * Checks if the single character is a sentenceTerminator (a period,
     * question mark, or exclamation point).
     * 
     * @param str
     *            The string to be checked
     * @return true if the character is a sentenceTerminator, or false
     *         otherwise.
     */
    private boolean checkSentenceTerminator(String str)
    {
        return (str.compareTo(".") == 0 || str.compareTo("?") == 0 || str
                .compareTo("!") == 0);
    }

    /**
     * Checks if the single character is a whiteSpace.
     * 
     * @param str
     *            The string to be checked
     * @return true if the character is a whiteSpace, or false otherwise.
     */
    private boolean checkWhiteSpace(String str)
    {
        return (str.compareTo(" ") == 0);
    }

    /**
     * Checks if the file has a next token.
     * 
     * @return returns true if the file has a next token, or false otherwise.
     */
    public boolean hasNextToken()
    {
        return !endOfFile;
    }

    /**
     * Gets the next token of the file by checking if the currentCharacter is a
     * word, a digit, a phrase terminator, a sentence terminator, or a
     * whitespace. If the current character is a letter, it returns the full
     * word with the digits and special characters that follow. If it is a
     * digit, the method returns the digit. If it is a phrase or sentence
     * terminator, the method returns the terminator. The method disregards
     * whitespaces from the file. If the current character is none of these, it
     * returns a token of type unknown.
     * 
     * @return a token based on the above rules.
     */
    public Token nextToken()
    {
        if (hasNextToken())
        {
            while (checkWhiteSpace(currentChar))
            {
                if (!hasNextToken())
                {
                    return new Token(Scanner.TOKEN_TYPE.END_OF_FILE, "END");
                }

                eat(currentChar);
            }
            if (checkDigit(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.DIGIT, currentChar);
                eat(currentChar);
                return answer;
            }
            else if (checkPhraseTerminator(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.END_OF_PHRASE,
                        currentChar);
                eat(currentChar);
                return answer;
            }
            else if (checkSentenceTerminator(currentChar))
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.END_OF_SENTENCE,
                        currentChar);
                eat(currentChar);
                return answer;
            }
            else if (checkLetter(currentChar))
            {
                String word = new String();
                while (hasNextToken()
                        && (checkLetter(currentChar)
                                || checkSpecial(currentChar)
                                || checkDigit(currentChar)))
                {
                    word = (word + currentChar.toLowerCase());
                    eat(currentChar);
                }
                return new Token(Scanner.TOKEN_TYPE.WORD, word);
            }
            else
            {
                Token answer = new Token(Scanner.TOKEN_TYPE.UNKNOWN,
                        currentChar);
                eat(currentChar);
                return answer;
            }
        }
        else
            return new Token(Scanner.TOKEN_TYPE.END_OF_FILE, "END");
    }
}
