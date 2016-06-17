package io.raffi.JSON;

/**
 * This class extends the RuntimeException thrown-able and it simply halts program execution when a
 * syntax error occurred.  It also prints the error message along with the line and column number of
 * where the error occurred.
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
	 * This constructor takes in a error message and the line and column that the error occurred on.
	 * @param        String        message       The error message
	 * @param        int           line          The line number where the error occurred
	 * @param        int           column        The column number where the error occurred
	 */
	protected SyntacticError ( String message, int line, int column ) {
		// Print out the JSON exception with the default headers and colors
		super ( new String (
			"\033[31;49m" + "SyntacticError:" + "\033[39;49m" + " " + message + " on line " + line +
			" and column " + column + "."
		) );
	}

}