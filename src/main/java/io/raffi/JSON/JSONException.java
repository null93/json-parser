package io.raffi.JSON;

/**
 * This class extends the RuntimeException thrown-able and it simply halts program execution when a
 * an error occurs and we throw it while in the Lexer or the Parser.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        JSONException.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
@SuppressWarnings ( "serial" )
public class JSONException extends RuntimeException {

	/**
	 * This constructor takes a error message and prints it out, if its a mac, print color header.
	 * @param        String        message       The error message
	 */
	public JSONException ( String message ) {
		// Print out the JSON exception with the default headers and colors
		super ( new String ( "\033[31;49m" + "JSONException:" + "\033[39;49m" + " " + message ) );
	}

}