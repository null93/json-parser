package io.raffi.JSON;

import java.util.Iterator;
import java.util.ArrayList;

/**
 * This class extends the ArrayList container class and it wraps it using the JSONData object.  The
 * add method is overloaded and it initialized a new JSONData object with the parameter data and
 * adds it into the ArrayList.  Calling the get method will simply return the JSONData object, while
 * all the other get methods will cast the JSONData object as the appropriate type.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        JSONArray.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
@SuppressWarnings ( "serial" )
public class JSONArray extends ArrayList <JSONData> {

	/**
	 * This constructor simply calls the super constructor since this class is a wrapper class
	 * around the ArrayList container.
	 */
	public JSONArray () {
		// Simply call the super constructor
		super ();
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        Boolean       data          The data to save into the array
	 * @return       void
	 */
	public void add ( Boolean data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        int           data          The data to save into the array
	 * @return       void
	 */
	public void add ( int data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        double        data          The data to save into the array
	 * @return       void
	 */
	public void add ( double data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        String        data          The data to save into the array
	 * @return       void
	 */
	public void add ( String data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        JSONData.Type data          The data to save into the array
	 * @return       void
	 */
	public void add ( JSONData.Type data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        JSONArray     data          The data to save into the array
	 * @return       void
	 */
	public void add ( JSONArray data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method simply adds a new instance of the JSONData class into the array, it used the fact
	 * that the JSONData class constructor is overloaded, and the data type is saved internally in
	 * that class.
	 * @param        JSONObject    data          The data to save into the array
	 * @return       void
	 */
	public void add ( JSONObject data ) {
		// Simply add the JSONData and pass parameter data to JSONData overloaded constructor
		super.add ( new JSONData ( data ) );
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       Integer                     The casted data type from the JSONData object
	 */
	public Integer getInt ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the integer value of the JSONData object
			return data.getInt ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       Double                      The casted data type from the JSONData object
	 */
	public Double getDouble ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the double value of the JSONData object
			return data.getDouble ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       Boolean                     The casted data type from the JSONData object
	 */
	public Boolean getBool ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the boolean value of the JSONData object
			return data.getBool ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       String                      The casted data type from the JSONData object
	 */
	public String getString ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the string value of the JSONData object
			return data.getString ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       JSONArray                   The casted data type from the JSONData object
	 */
	public JSONArray getArray ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the array from the JSONData object
			return data.getArray ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method gets the JSONData object from the array based on the index, and then casts it
	 * using the the JSONData object.
	 * @param        int           index         The index of the element in the ArrayList
	 * @return       JSONObject                  The casted data type from the JSONData object
	 */
	public JSONObject getObject ( int index ) {
		// Get the JSONData object from index
		JSONData data = super.get ( index );
		// Check to see that it is not null
		if ( data != null ) {
			// Return the object from the JSONData object
			return data.getObject ();
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This function, simply traversed the array list and calls the toString method recursively on
	 * the JSONData object, and concatenated it together and wraps the JSON array syntax around the
	 * data.
	 * @return       String                      The data returned as a JSON string
	 */
	public String toString () {
		// Initialize the index counter to zero
		int index = 0;
		// Initialize the resulting string to the beginning of the array syntax
		String result = "[";
		// Loop through all JSONData objects in the ArrayList
		for ( JSONData element : this ) {
			// Append the string value to the result string
			result += element.toString ();
			// Check to see that this isn't the last element in the set
			if ( index < super.size () - 1 ) {
				// If it isn't then append a comma to the result string
				result += ",";
			}
			// Increment the index
			index++;
		}
		// Return the result with the ending of the array syntax
		return result + "]";
	}

}