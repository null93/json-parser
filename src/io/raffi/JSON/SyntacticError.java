package io.raffi.JSON;

/**
 * This class extends the RuntimeException thrown-able and it simply halts program execution when a
 * syntax error occurred.  It also prints the error message along with the line and column number of
 * where the error occurred.  If the program is executed on Mac OS, we will print the exception
 * header in color.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        SyntacticError.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
@SuppressWarnings ( "serial" )
public class SyntacticError extends RuntimeException {

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
	 * This data member defines the header that we will print with every syntactic error thrown.
	 * @var          String        HEADER        The highlight color escaped in a string
	 */
	private String HEADER = "SyntacticError:";

	/**
	 * This constructor takes in a error message and the line and column that the error occurred on.
	 * @param        String        message       The error message
	 * @param        int           line          The line number where the error occurred
	 * @param        int           column        The column number where the error occurred
	 */
	protected SyntacticError ( String message, int line, int column ) {
		// Check to see which OS we are on, if we are in Mac OS, we will colorize header
		if ( System.getProperty ("os.name").toLowerCase ().contains ("mac") ) {
			// Print out the syntactic error with the default headers and colors
			System.out.printf (
				"%s %s, on line %i and column %i.", COLOR + HEADER + RESET, message, line, column
			);
		}
		// Print out a colorless header
		else {
			// Print out the syntactic error with the default headers
			System.out.printf ( "%s %s, on line %i and column %i.", HEADER, message, line, column );
		}
	}

}