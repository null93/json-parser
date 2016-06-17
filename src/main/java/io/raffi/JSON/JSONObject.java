package io.raffi.JSON;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

/**
 * This class extends the HashMap container class and it is a wrapper around it using the JSONData
 * class.  If the get method is called an instance of the JSONData class is returned and we can work
 * with this class.  Otherwise we can use the other get methods declared in this class to get casted
 * values where ever applicable.  The put method can also work the same way, using the JSONData
 * class.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        JSONObject.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
@SuppressWarnings ( { "serial", "unchecked" } )
public class JSONObject extends HashMap <String, JSONData> {

	/**
	 * Since this class wraps the HashMap class, it will simply call the super constructor.
	 */
	public JSONObject () {
		// Simply call the super constructor
		super ();
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        int           value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, int value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        double        value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, double value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        Boolean       value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, Boolean value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        String        value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, String value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String          key           The key for the HashMap entry
	 * @param        JSONData.Type   value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, JSONData.Type value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        JSONArray     value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, JSONArray value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method takes in a string key and a value that is overloaded, and it initializes a new
	 * instance of the JSONData class.  It then adds that instance to the HashMap using the passed
	 * key.
	 * @param        String        key           The key for the HashMap entry
	 * @param        JSONObject    value         The value to initialize JSONData instance and add
	 * @return       JSONObject                  Return reference to self, to chain commands
	 */
	public JSONObject put ( String key, JSONObject value ) {
		// Initialize JSONData instance with passed data and add to HashMap using key string
		super.put ( key, new JSONData ( value ) );
		// Return reference to self, so we can chain commands
		return this;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       Integer                     The data extracted from JSONData class
	 */
	public Integer getInt ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getInt ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       Double                      The data extracted from JSONData class
	 */
	public Double getDouble ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getDouble ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       Boolean                     The data extracted from JSONData class
	 */
	public Boolean getBool ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getBool ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       String                      The data extracted from JSONData class
	 */
	public String getString ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getString ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       JSONArray                   The data extracted from JSONData class
	 */
	public JSONArray getArray ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getArray ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This method extracts the JSONData instance using the passed key string, and then it tries to
	 * extract the data using the JSONData class, given that the extracted entry from the HashMap is
	 * not null.  if it is, then null is returned.
	 * @param        String        key           The key to access the value in HashMap
	 * @return       JSONObject                  The data extracted from JSONData class
	 */
	public JSONObject getObject ( String key ) {
		// First try to get the JSONData instance using the key
		JSONData data = super.get ( key );
		// Check to see if the extracted data is null
		if ( data != null ) {
			// If it isn't extract data using the JSONData class
			return data.getObject ();
		}
		// Otherwise, return null
		return null;
	}

	/**
	 * This function, simply traversed the hash map and calls the toString method recursively on
	 * the JSONData object, and concatenated it together and wraps the JSON object syntax around the
	 * data.
	 * @return       String                      The data returned as a JSON string
	 */
	public String toString () {
		// Initialize the result string with the object begin syntax bracket
		String result = "{";
		// Initialize the iterator from the hash map
		Iterator iterator = this.entrySet ().iterator ();
		// Loop through until we reach the end using th iterator
		while ( iterator.hasNext () ) {
			// Initialize the pair using the iterator
			Map.Entry pair = ( Map.Entry ) iterator.next ();
			// Append the key value pair into a string
			result += "\"" + pair.getKey () + "\":" + pair.getValue ().toString ();
			// If the iterator is not the last one
			if ( iterator.hasNext () ) {
				// Append the separating comma into the result string
				result += ",";
			}
			// Remove the iterator to avoid exception
			iterator.remove ();
		}
		// Return the result string with the object end syntax bracket
		return result + "}";
	}

}