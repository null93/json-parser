package io.raffi.JSON;

/**
 * This class is very important, because it wraps the JSON data into it.  It can be a base type, or
 * an array or object.  This object can be used for the high level iterations as a base type.  This
 * class is able to return the original data type, or it can cast it for the caller if applicable.
 * @version         1.0.0
 * @author          Rafael Grigorian
 * @category        JSONData.java
 * @package         io.raffi.JSON
 * @license         GNU Public License <http://www.gnu.org/licenses/gpl-3.0.txt>
 * @note            Do whatever you want with this package, it is here to help.  Credit is optional.
 */
public class JSONData {

	/**
	 * This enumerated type specifies the type of data that is passed in the constructor.
	 * @var          enum          Type          The data type passed in constructor
	 */
	public enum Type { BOOLEAN, INTEGER, DOUBLE, STRING, OBJECT, ARRAY, NULL; }

	/**
	 * This data member contains the type of the saved data object.  It is saved so we can cast it
	 * later based on the true type.
	 * @var          Type          type          The type of the saved data
	 */
	public Type type;

	/**
	 * This data member is an object and all data that is passed in the constructor is saved and
	 * casted as an Object, essentially loosing it's type.
	 * @var          Object        value         The data to save internally
	 */
	private Object value = null;

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        Boolean       value         The value to save internally as an Object
	 */
	protected JSONData ( Boolean value ) {
		// Save the value, casted as an Object and save the data type
		this.value = value;
		this.type = Type.BOOLEAN;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        int           value         The value to save internally as an Object
	 */
	protected JSONData ( int value ) {
		// Save the value, casted as an Object and save the data type
		this.value = new Integer ( value );
		this.type = Type.INTEGER;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        double        value         The value to save internally as an Object
	 */
	protected JSONData ( double value ) {
		// Save the value, casted as an Object and save the data type
		this.value = new Double ( value );
		this.type = Type.DOUBLE;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        String        value         The value to save internally as an Object
	 */
	protected JSONData ( String value ) {
		// Save the value, casted as an Object and save the data type
		this.value = value;
		this.type = Type.STRING;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        JSONArray     value         The value to save internally as an Object
	 */
	protected JSONData ( JSONArray value ) {
		// Save the value, casted as an Object and save the data type
		this.value = value;
		this.type = Type.ARRAY;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        JSONObject    value         The value to save internally as an Object
	 */
	protected JSONData ( JSONObject value ) {
		// Save the value, casted as an Object and save the data type
		this.value = value;
		this.type = Type.OBJECT;
	}

	/**
	 * This is an overloaded constructor that has a different data type that is passed that is
	 * different from all the other constructors.  The data is casted and saved internally, and the
	 * type is saved for later casting.
	 * @param        Type          value         Save null as the value, and set type to NULL only
	 */
	protected JSONData ( Type value ) {
		// Check to see that the type that was passed was NULL
		if ( value == Type.NULL ) {
			// Save the value, casted as an Object and save the data type
			this.value = null;
			this.type = Type.NULL;
		}
		// Otherwise throw an exception, since the only type that can be passed is NULL
		else {
			// Throw a JSONException
			throw new JSONException ( "Only JSONData.Type.NULL can be passed to Type constructor" );
		}
	}

	/**
	 * This method returns an integer from the data type.  If the data type is able to be converted
	 * and casted, then we cast it into the appropriate data type.  If it doesn't then thrown a
	 * JSONException.
	 * @return       Integer                     Returned data value as Integer
	 */
	public Integer getInt () {
		// If the data type is an integer
		if ( this.isInt () ) {
			// Return the integer
			return ( int ) this.value;
		}
		// If the data type is a double
		else if ( this.isDouble () ) {
			// Cast and return as an integer (lossy)
			return ( int ) Math.round ( ( double ) this.value );
		}
		// If the data type is a string
		else if ( this.isString () ) {
			// Try to cast as integer
			try {
				// Return and cast string as integer
				return Integer.parseInt ( ( String ) this.value );
			}
			// Catch any exceptions and try to cast as double and then integer (lossy)
			catch ( NumberFormatException exception ) {
				// Try to parse as decimal
				try {
					// Cast as a double
					double value = Double.parseDouble ( ( String ) this.value );
					// Then return as an integer
					return ( int ) Math.round ( value );
				}
				// Catch the exception if it fails
				catch ( NumberFormatException e ) {
					// Throw a new error
					throw new JSONException ( "No numerical value found in string" );
				}
			}
		}
		// If the data type is a boolean
		else if ( this.isBool () ) {
			// Return 1 is true, and 0 if false
			return ( Boolean ) this.value == true ? 1 : 0;
		}
		// If the value is null
		else if ( this.isNull () ) {
			// Return null
			return null;
		}
		// If its an object or an array, then throw an exception
		else if ( this.isObject () || this.isArray () ) {
			throw new JSONException ( "Cannot cast JSONArray / JSONObject as integer" );
		}
		// By default throw an exception stating that the data could not be casted
		throw new JSONException ( "Could not get/cast integer from this data type" );
	}

	/**
	 * This method returns a double from the data type.  If the data type is able to be converted
	 * and casted, then we cast it into the appropriate data type.  If it doesn't then thrown a
	 * JSONException.
	 * @return       Double                      Returned data value as Double
	 */
	public Double getDouble () {
		// If the data type is an integer
		if ( this.isInt () ) {
			// Cast and return double
			return ( double ) ( ( int ) this.value );
		}
		// If its a double
		else if ( this.isDouble () ) {
			// Simply return the double value
			return ( double ) this.value;
		}
		// If it is a boolean
		else if ( this.isBool () ) {
			// Return 1.0 if not false, otherwise 0.0
			return ( Boolean ) this.value ? 1.0 : 0.0;
		}
		// If the data type is a string
		else if ( this.isString () ) {
			// Try to parse as decimal
			try {
				// Cast as a double and return
				return Double.parseDouble ( ( String ) this.value );
			}
			// Catch the exception if it fails
			catch ( NumberFormatException e ) {
				// Throw a new error
				throw new JSONException ( "No numerical value found in string" );
			}
		}
		// If the data type is null
		else if ( this.isNull () ) {
			// Return null
			return null;
		}
		// If its an object or an array, then throw a JSONException
		else if ( this.isObject () || this.isArray () ) {
			throw new JSONException ( "Cannot cast JSONArray / JSONObject as integer" );
		}
		// By default throw an exception stating that the data could not be casted
		throw new JSONException ( "Could not get/cast double from this data type" );
	}

	/**
	 * This method returns a boolean from the data type.  If the data type is able to be converted
	 * and casted, then we cast it into the appropriate data type.  If it doesn't then thrown a
	 * JSONException.
	 * @return       Boolean                     Returned data value as Boolean
	 */
	public Boolean getBool () {
		// If the data type is an integer
		if ( this.isInt () ) {
			// Cast and return
			return ( int ) this.value != 0 ? true : false;
		}
		// If the data type is a double
		else if ( this.isDouble () ) {
			// Cast and return as a boolean
			return ( double ) this.value != 0.0 ? true : false;
		}
		// If the data type is a boolean
		else if ( this.isBool () ) {
			// Simply return the boolean value
			return ( Boolean ) this.value;
		}
		// If the data type is a string
		else if ( this.isString () ) {
			// Then return false if it's empty, otherwise return true
			return ( ( String ) this.value ).isEmpty () ? false : true;
		}
		// If the data type is null
		else if ( this.isNull () ) {
			// Return false
			return false;
		}
		// If the data type is an object or an array, throw a JSONException
		else if ( this.isObject () || this.isArray () ) {
			throw new JSONException ( "Cannot cast JSONArray / JSONObject as integer" );
		}
		// By default throw an exception stating that the data could not be casted
		throw new JSONException ( "Could not get/cast boolean from this data type" );
	}

	/**
	 * This method returns an string from the data type.  If the data type is able to be converted
	 * and casted, then we cast it into the appropriate data type.  If it doesn't then thrown a
	 * JSONException.
	 * @return       String                      Returned data value as String
	 */
	public String getString () {
		// If the method is a string, we don't want to append the quotes around the string
		if ( this.isString () ) {
			// Return casted value as string
			return ( String ) this.value;
		}
		// Simply return the toString method
		return this.toString ();
	}

	/**
	 * This method returns a JSONArray if in fact the data type of the internally saved object is
	 * an instance of JSONArray.  Otherwise we will return null.
	 * @return       JSONArray                   The internally saved data casted as JSONArray
	 */
	public JSONArray getArray () {
		// Check to see if the type is an array
		if ( this.isArray () ) {
			// Cast and return the JSONArray object
			return ( JSONArray ) this.value;
		}
		// Otherwise we should return null
		else {
			return null;
		}
	}

	/**
	 * This overloaded function takes in an array index, and it tries to get the array instance if
	 * applicable, and passes the index to the object and returns a JSONData object.
	 * @param        int           index         The index to access in the array
	 * @return       JSONData  					 Object at index, null if not array
	 */
	public JSONData getArray ( int index ) {
		// Get the array from if applicable
		JSONArray array = this.getArray ();
		// Make sure it isn't null
		if ( array != null ) {
			// Return the passed call to the array object
			return array.get ( index );
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This method returns a JSONObject if in fact the data type of the internally saved object is
	 * an instance of JSONObject.  Otherwise we will return null.
	 * @return       JSONObject                  The internally saved data casted as JSONObject
	 */
	public JSONObject getObject () {
		// Check to see if type is an object
		if ( this.isObject () ) {
			// If it is then cast and return it
			return ( JSONObject ) this.value;
		}
		// Otherwise we should return null
		else {
			return null;
		}
	}

	/**
	 * This overloaded function takes in an object key index, and it tries to get the object
	 * instance if applicable, and passes the key to the object and returns a JSONData object.
	 * @param        String        key           The key to access the object
	 * @return       JSONData  					 Object at index, null if not object
	 */
	public JSONData getObject ( String key ) {
		// Get the object from if applicable
		JSONObject object = this.getObject ();
		// Make sure it isn't null
		if ( object != null ) {
			// Return the passed call to the object
			return object.get ( key );
		}
		// Otherwise return null
		return null;
	}

	/**
	 * This class method simply returns if the saved data type is of boolean type.
	 * @return       Boolean                     Is the data member a bool?
	 */
	public Boolean isBool () {
		// Test the type of the data member
		return this.type == Type.BOOLEAN;
	}

	/**
	 * This class method simply returns if the saved data type is of integer type.
	 * @return       Boolean                     Is the data member a integer?
	 */
	public Boolean isInt () {
		// Test the type of the data member
		return this.type == Type.INTEGER;
	}

	/**
	 * This class method simply returns if the saved data type is of double type.
	 * @return       Boolean                     Is the data member a double?
	 */
	public Boolean isDouble () {
		// Test the type of the data member
		return this.type == Type.DOUBLE;
	}

	/**
	 * This class method simply returns if the saved data type is of string type.
	 * @return       Boolean                     Is the data member a string?
	 */
	public Boolean isString () {
		// Test the type of the data member
		return this.type == Type.STRING;
	}

	/**
	 * This class method simply returns if the saved data type is of array type.
	 * @return       Boolean                     Is the data member an array?
	 */
	public Boolean isArray () {
		// Test the type of the data member
		return this.type == Type.ARRAY;
	}

	/**
	 * This class method simply returns if the saved data type is of object type.
	 * @return       Boolean                     Is the data member a object?
	 */
	public Boolean isObject () {
		// Test the type of the data member
		return this.type == Type.OBJECT;
	}

	/**
	 * This class method simply returns if the saved data type is of null type.
	 * @return       Boolean                     Is the data member null?
	 */
	public Boolean isNull () {
		// Test the type of the data member
		return this.type == Type.NULL;
	}

	/**
	 * This method turns the data type that is saved internally into a string representation.  If a
	 * JSONArray or JSONObject is saved internally, then we will return the representation,
	 * recursively.
	 * @return       String                      The string representation of the saved data
	 */
	public String toString () {
		// Switch between the different types the internally saved data can be
		switch ( this.type ) {
			case INTEGER:
				// Cast and return as a string
				return Integer.toString ( ( int ) this.value );
			case BOOLEAN:
				// Cast and return as a string
				return ( Boolean ) this.value ? "true" : "false";
			case DOUBLE:
				// Cast and return as a string
				return Double.toString ( ( double ) this.value );
			case STRING:
				// Cast and return as a string
				return "\"" + ( String ) this.value + "\"";
			case NULL:
				// Cast and return as a string
				return "null";
			case ARRAY:
				// Recursively return the string representation
				return ( ( JSONArray ) this.value ).toString ();
			case OBJECT:
				// Recursively return the string representation
				return ( ( JSONObject ) this.value ).toString ();
			default:
				// Otherwise return null, should never get here
				return null;
		}
	}

}