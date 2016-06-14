package io.raffi.JSON;

/**
 * This class is essentially a data wrapper and stores different types of data that make up a token.
 * It is accessible only in the package, and contains the token type, the token value, and finally
 * the line and column number that the token can be found on.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        Token.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
public class Token {

	/**
	 * This enum class specifies the token type.  These tokens are either base data types like true,
	 * false, null, etc.  They might also be structural tokens, like colon, comma, and the different
	 * types of brackets.
	 * @var          enum          Type          The type of token, used in Lexer and Parser
	 */
	protected enum Type {
		TRUE,               // true
		FALSE,              // false
		NULL,               // null
		STRING,             // string literal
		NUMBER,             // numeric literal
		LEFT_CURLY,         // {
		RIGHT_CURLY,        // }
		LEFT_SQUARE,        // [
		RIGHT_SQUARE,       // ]
		COLON,              // :
		COMMA,              // ,
		UNKNOWN,            // ?
		EOT;                // \0
	}

	/**
	 * This internal data member saves the line number from the original source data where the token
	 * has started.  This will be displayed in any syntactic errors that are thrown.
	 * @var          int           line          The line number of where the token starts
	 */
	protected int line;

	/**
	 * This internal data member saves the column number from the original source data where the
	 * token has started.  This will be displayed in any syntactic errors that are thrown.
	 * @var          int           column        The column number of where the token starts
	 */
	protected int column;

	/**
	 * This internal data member holds a member of the enumerated class that denotes the type of
	 * token that we are referencing in the instance.
	 * @var          Type          type          Type of token that is referenced throughout class
	 */
	protected Type type;

	/**
	 * This internal data member holds the value of the token as a string.  It will be used in the
	 * Parser class in order to extract and cast this value to the appropriate one in Java.
	 * @var          String        value         The value of the token
	 */
	protected String value;

	/**
	 * This constructor simply takes in the type of token, value, and the line and column that the
	 * token appears in.  It then simply saves it internally.
	 * @param        Type          type          The type of token
	 * @param        String        value         The value of the token
	 * @param        int           line          The line number the token starts on
	 * @param        int           column        The column number the token starts on
	 */
	protected Token ( Type type, String value, int line, int column ) {
		this.type = type;
		this.value = value;
		this.line = line;
		this.column = column;
	}

}