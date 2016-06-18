package io.raffi.JSON;

import java.util.Scanner;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This class is the Lexer class and is initialized with either a JSON string, JSON file, or JSON
 * input stream.  The input data stream is there in order to load resources inside packages.  It
 * then converts the data into a string, if needed, and then starts tokenizing the data stream on
 * the request of the nextToken method.  The Lexer exists to make parsing way easier, by tokenizing
 * characters into chunks and classifying them as a type.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        Lexer.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
public class Lexer {

	/**
	 * This data member defines which characters are considered whitespace.  It is wrapped in a
	 * string instead of an array so we can use the String.contains method.
	 * @var          String        whitespace    Which characters are considered whitespace
	 */
	private String whitespace = "\t \r\n";

	/**
	 * This data member defines which characters can be escaped in a string.  It is wrapped in a
	 * string instead of an array so we can use the String.contains method.
	 * @var          String        escaped       Which characters can be escaped in a string
	 */
	private String escaped = "\"/\\\bfnrtu";

	/**
	 * This data member saves the json data in a string, whether the Lexer is initialized with a
	 * string, file, or input stream.
	 * @var          String        data          JSON data as string, initialized in constructor
	 */
	private String data;

	/**
	 * This data member saves the current line number of where we are in while traversing the JSON
	 * data string character by character.
	 * @var          int           line          The line number position in JSON data string
	 */
	private int line;

	/**
	 * This data member saves the current column number of where we are in while traversing the JSON
	 * data string character by character.
	 * @var          int           column        The column number position in JSON data string
	 */
	private int column;

	/**
	 * This data member saves the current index of where we are in while traversing the JSON data
	 * string character by character.
	 * @var          int           index         The index position in JSON data string
	 */
	private int index;

	/**
	 * This data member simply saves the length of the JSON data string.  It is defined in the
	 * constructor.
	 * @var          int           length        The length of the JSON data string
	 */
	private int length;

	/**
	 * This constructor takes in a JSON data string and saves the data internally, saves the data
	 * length, and initialized the positional data members.
	 * @param        String        data          The JSON data string to tokenize
	 */
	protected Lexer ( String data ) {
		// Make sure the string is not null
		if ( data == null ) {
			// Throw an exception
			throw new JSONException ( "Uninitialized String was passed to constructor" );
		}
		// Save the passed in data
		this.data = data;
		// Initialize the index, length, line, and column number initially
		this.line = 1;
		this.column = 1;
		this.index = 0;
		this.length = data.length ();
	}

	/**
	 * This constructor takes in a JSON data file and saves the data internally, saves the data
	 * length, and initialized the positional data members.  If no file is found, then JSONException
	 * is thrown.
	 * @param        File          filepath      The JSON data file to tokenize
	 */
	protected Lexer ( File filepath ) {
		// Make sure the file instance is not null
		if ( filepath == null ) {
			// Throw an exception
			throw new JSONException ( "Invalid File object was passed to constructor" );
		}
		// Try to load the file contents, IOException might occur
		try {
			// Load in the file contents as a string
			this.data = String.join ( "\n", Files.readAllLines ( filepath.toPath () ) );
		}
		// Catch the IOException if file path is invalid
		catch ( IOException io ) {
			// Throw a lexer runtime exception
			throw new JSONException ( "Could not load JSON file" );
		}
		// Initialize the index, line, length, and column number initially
		this.line = 1;
		this.column = 1;
		this.index = 0;
		this.length = data.length ();
	}

	/**
	 * This constructor takes in a JSON data input stream and saves the data internally, saves the
	 * data length, and initialized the positional data members.  If the input stream is null, a
	 * JSONException is thrown.
	 * @param        InputStream   resource      The JSON data input stream to tokenize
	 */
	protected Lexer ( InputStream resource ) {
		// Check to see that the input stream is not null
		if ( resource == null ) {
			throw new JSONException ( "Passed input stream is null" );
		}
		// Initialize the scanner and scan the file till EOF
		Scanner scanner = new Scanner ( resource ).useDelimiter ( "\\A" );
		// Check to see if data is valid
		this.data = scanner.hasNext () ? scanner.next () : "";
		// Initialize the index, line, length, and column number initially
		this.line = 1;
		this.column = 1;
		this.index = 0;
		this.length = data.length ();
	}

	/**
	 * This method takes in the desired literal to match ( false, true, null ) and it tries to match
	 * it character by character.  If an unknown literal is encountered, then an unknown token is
	 * passed.
	 * @var          Token.Type    type          The type of literal to try to tokenize
	 * @return       Token                       The token that was tokenized
	 */
	private Token matchLiteral ( Token.Type type ) {
		// Get the string name based on token type
		String name = type.toString ().toLowerCase ();
		// Check to see that we have enough room to read the rest of literal
		if ( this.index + name.length () - 1 < this.length ) {
			// Get the necessary lookahead string
			String lookahead = this.data.substring ( this.index, this.index + name.length () );
			// If the lookahead is the literal name then it is a valid token
			if ( lookahead.equals ( name ) ) {
				// Return the literal token
				this.column += name.length ();
				this.index += name.length ();
				// Return the literal token
				return new Token ( type, lookahead, this.line, this.column - name.length () );
			}
		}
		// Return an unknown token if all fails and increment the index
		String value = Character.toString ( this.data.charAt ( this.index ) );
		this.index++;
		return new Token ( Token.Type.UNKNOWN, value, this.line, this.column++ );
	}

	/**
	 * This function tries to tokenize a string literal given the start of the string.  If syntactic
	 * errors are occurred, then a syntactic exception is thrown.
	 * @param        char          current       The current character that is encountered initially
	 * @return       Token                       The string token that is tokenized
	 */
	private Token matchStringLiteral ( char current ) {
		// Save the current line and column
		int line = this.line;
		int column = this.column;
		// Initialize the string result and increment the index and column
		String value = "";
		this.index++;
		this.column++;
		// Loop until we reach the end of data
		while ( this.index < this.length ) {
			// Redefine the current token and append to the value
			current = this.data.charAt ( this.index );
			// Check to see if we finished the string
			if ( current == '"' ) {
				// Increment the index and return the string token
				this.index++;
				this.column++;
				return new Token ( Token.Type.STRING, value, line, column );
			}
			// Check to see if there is an escaped character
			if ( current == '\\' ) {
				// Check to see if there is an appropriate escape character next
				if ( this.index + 1 >= this.length || !this.escaped.contains ( this.data.charAt ( this.index + 1 ) + "" ) ) {
					// Throw a new error stating that an invalid escape has occurred
					throw new SyntacticError ( "Invalid escape character", this.line, this.column );
				}
				// Otherwise its a valid escape
				else {
					// Add the second part of the escape character to value and update values
					value += current;
					this.index++;
					this.column++;
					current = this.data.charAt ( this.index );
				}
			}
			// Add the character to the value string
			value += current;
			// Increment the index and column
			this.index++;
			this.column++;
		}
		// If we reach this point, then the string was never closed
		throw new SyntacticError ( "String literal is not closed", line, column );
	}

	/**
	 * This method tries to tokenize a numerical literal and it is returned.  If syntactic errors
	 * are occurred, then a syntactic exception is thrown.
	 * @param        char          current       The current character that is encountered initially
	 * @return       Token                       The number token that is tokenized
	 */
	private Token matchNumericLiteral ( char current ) {
		// Initialize the value that we tokenized
		String value = "";
		// Save the entry line and column
		int line = this.line;
		int column = this.column;
		// If there is a minus sign, then accept it since the number can be negative
		if ( current == '-' ) {
			// Append to value, increment index and column, and update the current character
			value += current;
			this.index++;
			this.column++;
			current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
		}
		// If its a zero, then we can only match it, no leading zeros
		if ( current == '0' ) {
			// Append to value, increment index and column, and update the current character
			value += current;
			this.index++;
			this.column++;
			current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
		}
		// If it isn't a digit, then match until a non numeral is encountered
		else if ( Character.isDigit ( current ) ) {
			// Loop through while the current char is numeric
			while ( Character.isDigit ( current ) ) {
				// Append to value, increment index and column, and update the current character
				value += current;
				this.index++;
				this.column++;
				current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
			}
		}
		// Otherwise print an exception, since it would be an invalid numeral
		else {
			// Throw a new syntactic error
			throw new SyntacticError ( "Invalid numeric literal", line, column );
		}
		// If there is a decimal point match it and the numbers that follow
		if ( current == '.' ) {
			// Append to value, increment index and column, and update the current character
			value += current;
			this.index++;
			this.column++;
			current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
			// If the next character is numeric all is well
			if ( Character.isDigit ( current ) ) {
				// Loop through until we match every number after the decimal point
				while ( Character.isDigit ( current ) ) {
					// Append to value, increment index and column, and update the current character
					value += current;
					this.index++;
					this.column++;
					current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
				}
			}
			// Otherwise print an exception error, since we need a number after the decimal point
			else {
				// Throw a new syntactic error
				throw new SyntacticError ( "Expecting digits after decimal point", line, column );
			}
		}
		// Check to see if there is a scientific format present
		if ( current == 'e' || current == 'E' ) {
			// Append to value, increment index and column, and update the current character
			value += current;
			this.index++;
			this.column++;
			current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
			// If there is a sign indication, match it
			if ( current == '+' || current == '-' ) {
				// Append to value, increment index and column, and update the current character
				value += current;
				this.index++;
				this.column++;
				current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
			}
			// If the next character is a numeral, all is well
			if ( Character.isDigit ( current ) ) {
				// Loop through until we match all numbers after scientific format indication
				while ( Character.isDigit ( current ) ) {
					// Append to value, increment index and column, and update the current character
					value += current;
					this.index++;
					this.column++;
					current = this.index < this.length ? this.data.charAt ( this.index ) : '\0';
				}
			}
			// Otherwise there is an error, since there needs to be at least one number after (e|E)
			else {
				// Throw a new syntactic error
				throw new SyntacticError ( "Expecting number after exponential", line, column );
			}
		}
		// Return the numerical token that we found
		return new Token ( Token.Type.NUMBER, value, line, column );
	}

	/**
	 * This method returns the next token that is encountered in the data stream that we got passed
	 * in the constructor.  If the data stream comes to the end, and a request to get a next token
	 * is occurred, then an End-Of-Token (EOT) is returned indefinitely.
	 * @return       Token                       The token that is tokenized next in the data stream
	 */
	protected Token nextToken () {
		// Initialize the value string
		String value = "";
		// Loop through until we reach EOT
		while ( this.index < this.length ) {
			// Get the current character
			char current = this.data.charAt ( this.index );
			// Append character to the value string if its not whitespace
			if ( !this.whitespace.contains ( current + "" ) ) {
				// Append character to value string
				value += current;
			}
			// See if the type is a string literal
			if ( current == '"' ) {
				// Try to match the string literal and return the token
				return this.matchStringLiteral ( current );
			}
			// Otherwise check to see if its a numerical literal
			else if ( current == '-' || Character.isDigit ( current ) ) {
				// Greedy match numeric literal and return the token
				return this.matchNumericLiteral ( current );
			}
			// Switch between the types of characters we find
			switch ( current ) {
				case 'n':
					// Try to match the NULL literal
					return this.matchLiteral ( Token.Type.NULL );
				case 'f':
					// Try to match the FALSE literal
					return this.matchLiteral ( Token.Type.FALSE );
				case 't':
					// Try to match the TRUE literal
					return this.matchLiteral ( Token.Type.TRUE );
				case ':':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.COLON, value, this.line, this.column++ );
				case ',':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.COMMA, value, this.line, this.column++ );
				case '{':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.LEFT_CURLY, value, this.line, this.column++ );
				case '}':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.RIGHT_CURLY, value, this.line, this.column++ );
				case '[':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.LEFT_SQUARE, value, this.line, this.column++ );
				case ']':
					// Increment the index and the column and return the token
					this.index++;
					return new Token ( Token.Type.RIGHT_SQUARE, value, this.line, this.column++ );
				case '\n':
					// Increment the line, column, and index and beak
					this.index++;
					this.line++;
					this.column = 1;
					break;
				case '\t':
				case ' ':
					// On whitespace, increment the index and the column, and break
					this.column++;
					this.index++;
					break;
				default:
					// Increment column and index
					this.column++;
					this.index++;
					// Return the unknown token type
					return new Token ( Token.Type.UNKNOWN, value, this.line, this.column - 1 );
			}
		}
		// After we went through everything, we want to keep returning EOT token
		return new Token ( Token.Type.EOT, "EOT", this.line, this.column );
	}

}