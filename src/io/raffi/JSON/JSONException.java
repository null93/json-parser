package io.raffi.JSON;

/**
 * This class extends the RuntimeException thrown-able and it simply halts program execution when a
 * an error occurs and we throw it while in the Lexer or the Parser. If the program is executed on
 * Mac OS, we will print the exception header in color.
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
	 * This data member defines the string to change the terminal output to a color one.
	 * @var          String        COLOR         The highlight color escaped in a string
	 */
	private String COLOR = "\033[31;49m";

	/**
	 * This data member defines the string to change the terminal output to the default one.
	 * @var          String        RESET         The regular color escaped in a string
	 */
	private String RESET = "\033[39;49m";

	/**
	 * This data member defines the header that we will print with every JSON exception thrown.
	 * @var          String        HEADER        The highlight color escaped in a string
	 */
	private String HEADER = "JSONException:";

	/**
	 * This constructor takes a error message and prints it out, if its a mac, print color header.
	 * @param        String        message       The error message
	 */
	public JSONException ( String message ) {
		// Check to see which OS we are on, if we are in Mac OS, we will colorize header
		if ( System.getProperty ("os.name").toLowerCase ().contains ("mac") ) {
			// Print out the JSON exception with the default headers and colors
			System.out.printf ( "%s %s", COLOR + HEADER + RESET, message );
		}
		// Print out a colorless header
		else {
			// Print out the JSON exception with the default headers
			System.out.printf ( "%s %s", HEADER, message );
		}
	}

}