package io.raffi.JSON;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.io.File;

/**
 * This class is the Parser class and it initialized the Lexer using whatever constructor we choose,
 * then it parsed the data and traversed through the below defined CFG in order to parse and
 * validate the json data.  It is also used to generate the translation data structure that is
 * defined within this package.  The abstractly defined JSONData class keeps all the types of data
 * types that could present itself in a JSON data structure.  The specification that was used to
 * parse the JSON data can be found here: https://tools.ietf.org/html/rfc7159
 *
 * BASE             =>      true | false | null | STRING | NUMBER | OBJECT | ARRAY
 * OBJECT           =>      { OBJECT_PAIR }
 * OBJECT_PAIR      =>      STRING : BASE SEPERATE_OBJECT | ;
 * SEPERATE_OBJECT  =>      , OBJECT_PAIR | ;
 * ARRAY            =>      [ ARRAY_RECURSE ]
 * ARRAY_RECURSE    =>      BASE | BASE , ARRAY_RECURSE | ;
 *
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        Parser.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
public class Parser {

	/**
	 * This internal data member saves the reference to the Lexer class that is instantiated in the
	 * constructor.
	 * @var          Lexer         lexer         This is the Lexer class instance
	 */
	private Lexer lexer;

	/**
	 * This internal data member holds the current token that was returned by the Lexer.  It is
	 * initially saved in the constructor by calling the nextToken method within the constructor.
	 * The parser holds the token while parsing, and we can request a new one once we are done with
	 * the current one.
	 * @var          Token         current       This is the current Token, held by the parser.
	 */
	private Token current;

	/**
	 * This internal data member holds the result of a successful parsing.  It is of type JSONData,
	 * because the root of the json data format can be anything, an array, object, or base data type
	 * is also valid.
	 * @var          JSONData      result        The resulting java data structure that was parsed
	 */
	private JSONData result;

	/**
	 * This constructor is initialized with passing the file object.  It initialized the Lexer,
	 * initialized the current token using the Lexer, and then saves the result by launching the
	 * starting CFG derivative.  It finally checks to see that EOT is found after everything is
	 * parsed.
	 * @var          File          filepath      The file object from where to read from
	 */
	public Parser ( File filepath ) {
		// Initialize the lexer and save it internally
		this.lexer = new Lexer ( filepath );
		// Get the first token and save it to be the current one
		this.current = this.lexer.nextToken ();
		// Launch parsing by initializing the start of the CFG and save result internally
		this.result = this.base ();
		// Make sure the current token after parsing is EOT
		this.match ( Token.Type.EOT );
	}


	/**
	 * This constructor is initialized with passing a data string to it.  It initialized the Lexer,
	 * initialized the current token using the Lexer, and then saves the result by launching the
	 * starting CFG derivative.  It finally checks to see that EOT is found after everything is
	 * parsed.
	 * @var          File          filepath      The file object from where to read from
	 */
	public Parser ( String data ) {
		// Initialize the lexer and save it internally
		this.lexer = new Lexer ( data );
		// Get the first token and save it to be the current one
		this.current = this.lexer.nextToken ();
		// Launch parsing by initializing the start of the CFG and save result internally
		this.result = this.base ();
		// Make sure the current token after parsing is EOT
		this.match ( Token.Type.EOT );
	}


	/**
	 * This constructor is initialized with passing an input stream.  It initialized the Lexer,
	 * initialized the current token using the Lexer, and then saves the result by launching the
	 * starting CFG derivative.  It finally checks to see that EOT is found after everything is
	 * parsed.
	 * @var          File          filepath      The file object from where to read from
	 */
	public Parser ( InputStream resource ) {
		// Initialize the lexer and save it internally
		this.lexer = new Lexer ( resource );
		// Get the first token and save it to be the current one
		this.current = this.lexer.nextToken ();
		// Launch parsing by initializing the start of the CFG and save result internally
		this.result = this.base ();
		// Make sure the current token after parsing is EOT
		this.match ( Token.Type.EOT );
	}

	/**
	 * This function takes in a token type that we are expecting to be the current, and if it
	 * matches the current token type, then it replaces it with the next token by calling the
	 * nextToken method from the Lexer class.  If it doesn't match, then a syntactic error is
	 * thrown.
	 * @param        Token.Type    expecting     The token type we are expecting current to be
	 * @return       void
	 */
	private void match ( Token.Type expecting ) {
		// Check to see that the expected type matches the current token type
		if ( this.current.type == expecting ) {
			// Get the next token and save it as the current token
			this.current = this.lexer.nextToken ();
		}
		// Otherwise there should be a syntactic error that is thrown
		else {
			// Throw a new syntactic error
			throw new SyntacticError (
				"Expecting " + expecting.toString () + ", got '" + this.current.value + "'",
				this.current.line,
				this.current.column
			);
		}
	}

	/**
	 * This function is a simple getter function that returns the result of the parser.  This result
	 * is initialized in the constructor.
	 * @return           JSONData                The parsed JSON object that is saved internally
	 */
	public JSONData getResult () {
		// Return the parsed result in the form of a JSONData object
		return this.result;
	}

	/**
	 * This method is the start of the CFG found in the header, and defines the below derivative. It
	 * returns a JSONData instance that it tries to parse using the grammar rules.
	 *
	 * BASE          =>            true | false | null | STRING | NUMBER | OBJECT | ARRAY
	 *
	 * @return       JSONData                    The parsed data encapsulated in the JSONData object
	 */
	private JSONData base () {
		// Declare the JSONData variable
		JSONData data;
		// Switch between the possible types of tokens that are acceptable
		switch ( this.current.type ) {
			case TRUE:
				// Match the token that we matched in this case
				this.match ( this.current.type );
				// Return the JSONData
				return new JSONData ( true );
			case FALSE:
				// Match the token that we matched in this case
				this.match ( this.current.type );
				// Return the JSONData
				return new JSONData ( false );
			case NULL:
				// Match the token that we matched in this case
				this.match ( this.current.type );
				// Return the JSONData
				return new JSONData ( JSONData.Type.NULL );
			case STRING:
				// Initialize the JSONData instance so we can return it after we match it
				data = new JSONData ( this.current.value );
				// Match the token that we matched in this case
				this.match ( this.current.type );
				// Return the JSONData
				return data;
			case NUMBER:
				// Check to see if its an double
				if ( this.current.value.contains ( "." ) ) {
					// Initialize the JSONData instance
					data = new JSONData ( Double.parseDouble ( this.current.value ) );
					// Match the number token
					this.match ( this.current.type );
					// Return the JSONData object initialized with double
					return data;
				}
				// Check to see if there is scientific notation in the number
				if ( this.current.value.contains ( "e" ) || this.current.value.contains ( "E" ) ) {
					// Initialize the JSONData instance
					data = new JSONData ( new BigDecimal ( this.current.value ).intValue () );
				}
				// Otherwise there is no scientific notation
				else {
					// Try to initialize as integer
					try {
						// Initialize to as an integer JSONData instance
						data = new JSONData ( Integer.parseInt ( this.current.value ) );
					}
					// Catch to see if we fail, number might be too big
					catch ( NumberFormatException exception ) {
						// Throw an error stating that number is too big for an integer
						throw new JSONException (
							"Problem casting '" + this.current.value + "' as integer, too big?"
						);
					}
				}
				// Match the number token
				this.match ( this.current.type );
				// Otherwise return the JSONData object initialized with integer
				return data;
			case LEFT_CURLY:
				// If its an object then call the object derivative, and return the JSONData object
				return new JSONData ( this.object () );
			case LEFT_SQUARE:
				// If its an array then call the array derivative, and return the JSONData array
				return new JSONData ( this.array () );
			default:
				// If anything else is encountered, throw a syntactic error
				throw new SyntacticError (
					"Unknown token found '" + this.current.value + "'",
					this.current.line,
					this.current.column
				);
		}
	}

	/**
	 * This method is part of the CFG found in the header, and defines the below derivative. It
	 * returns a JSONObject instance that it tries to parse using the grammar rules.
	 *
	 * OBJECT        =>            { OBJECT_PAIR }
	 *
	 * @return       JSONObject                  Parsed data encapsulated in the JSONObject object
	 */
	private JSONObject object () {
		// Initialize the JSONObject that we will return
		JSONObject data = new JSONObject ();
		// Match the left curly
		this.match ( Token.Type.LEFT_CURLY );
		// Parse the object pair by passing the initialized object
		this.objectPair ( data );
		// Match the right curly
		this.match ( Token.Type.RIGHT_CURLY );
		// Return the JSONObject object
		return data;
	}

	/**
	 * This method is part of the CFG found in the header, and defines the below derivative. It
	 * takes in a JSONObject instance and appends any key value pairs to it, if any.
	 *
	 * OBJECT_PAIR   =>            STRING : BASE SEPERATE_OBJECT | ;
	 *
	 * @param        JSONObject                  JSONObject object to append key value pair to
	 * @return       void
	 */
	private void objectPair ( JSONObject object ) {
		// If the current token is a string, then there is a key value pair in the object
		if ( this.current.type == Token.Type.STRING ) {
			// Save the key value
			String key = this.current.value;
			// Match the key and the colon
			this.match ( Token.Type.STRING );
			this.match ( Token.Type.COLON );
			// Save the value
			JSONData value = this.base ();
			// Put the key value pair into the passed object
			object.put ( key, value );
			// See if we have more key value pairs to match
			this.seperateObject ( object );
		}
	}

	/**
	 * This method is part of the CFG found in the header, and defines the below derivative. It
	 * takes in a JSONObject instance and appends any key value pairs to it, if any.
	 *
	 * SEPERATE_OBJECT             =>            , OBJECT_PAIR | ;
	 *
	 * @param        JSONObject                  JSONObject object to append key value pair to
	 * @return       void
	 */
	private void seperateObject ( JSONObject object ) {
		// If there is a comma, then there are more key value pairs
		if ( this.current.type == Token.Type.COMMA ) {
			// Match the comma
			this.match ( Token.Type.COMMA );
			// Get the second pair, by passing in the object into the objectPair derivative
			this.objectPair ( object );
		}
	}

	/**
	 * This method is part of the CFG found in the header, and defines the below derivative. It
	 * returns a JSONArray instance that it tries to parse using the grammar rules.
	 *
	 * ARRAY         =>            [ ARRAY_RECURSE ]
	 *
	 * @return       JSONArray                   Parsed data encapsulated in the JSONArray object
	 */
	private JSONArray array () {
		// Initialize the JSONArray object
		JSONArray array = new JSONArray ();
		// Match the left square bracket
		this.match ( Token.Type.LEFT_SQUARE );
		// See if there are data members inside array
		this.arrayRecurse ( array );
		// Match the right square bracket
		this.match ( Token.Type.RIGHT_SQUARE );
		// Return the JSONArray object
		return array;
	}

	/**
	 * This method is part of the CFG found in the header, and defines the below derivative. It
	 * takes in a JSONArray instance and appends any JSONData objects to it.
	 *
	 * ARRAY_RECURSE               =>            BASE | BASE , ARRAY_RECURSE | ;
	 *
	 * @param        JSONArray                  JSONArray object to append JSONData object to
	 * @return       void
	 */
	private void arrayRecurse ( JSONArray array ) {
		// If one of these tokens are ahead of it, then we can match contents
		if ( this.current.type == Token.Type.NUMBER
			 || this.current.type == Token.Type.STRING
			 || this.current.type == Token.Type.NULL
			 || this.current.type == Token.Type.TRUE
			 || this.current.type == Token.Type.FALSE
			 || this.current.type == Token.Type.LEFT_SQUARE
			 || this.current.type == Token.Type.LEFT_CURLY ) {
			// Add the base type to the array
			array.add ( this.base () );
			// If there is a comma, there is more in the array
			if ( this.current.type == Token.Type.COMMA ) {
				// Match the comma and pass array reference to self, recursively
				this.match ( Token.Type.COMMA );
				// Make sure that a valid base value is next
				if ( this.current.type == Token.Type.NUMBER
					 || this.current.type == Token.Type.STRING
					 || this.current.type == Token.Type.NULL
					 || this.current.type == Token.Type.TRUE
					 || this.current.type == Token.Type.FALSE
					 || this.current.type == Token.Type.LEFT_SQUARE
					 || this.current.type == Token.Type.LEFT_CURLY ) {
					// Match that base value
					this.arrayRecurse ( array );
				}
				// Otherwise we need to throw an error
				else {
					// Throw a syntactic error
					throw new SyntacticError (
						"Expecting BASE_VALUE, got '" + this.current.value + "'",
						this.current.line,
						this.current.column
					);
				}
			}
		}
	}

}