## Java JSON Parser
This package is for Java and is able to parse JSON data using a parser and a lexer.  All data types are encapsulated within the JSONData object, this includes the JSONArray and JSONObject types.

### Initializing the JSON parser
The parser has three different constructors.  One is for a string, the second is with the File object, and finally the third is for an InputStream object.  The last one is there in order to pass in a resource file in a package.
###### Using a JSON data string:
```java
import io.raffi.JSON.Parser;
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONObject;

Parser parser = new Parser ( "{\"test\":\"data\"}" );
JSONData result = parser.getResult ();
JSONObject root = result.getObject ();
```
###### Using a JSON File:
```java
import io.raffi.JSON.Parser;
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONObject;

Parser parser = new Parser ( new File ( "test.json" ) );
JSONData result = parser.getResult ();
JSONObject root = result.getObject ();
```
###### Using an Input Stream:
```java
import io.raffi.JSON.Parser;
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONObject;

Parser parser = new Parser ( MyClass.class.getClassLoader ().getResourceAsStream ( "test.json" ) );
JSONData result = parser.getResult ();
JSONObject root = result.getObject ();
```

### Using the JSONData class:
This class wraps all the possible data types that JSON can encode, whether it is a boolean, int, double, string, object, array, or null.  Below you can find all the different methods that are public  for this object.
###### Constructors:
The following is an example code snippet showing how to initialize all the different types of data that are valid in the JSON protocol:
```java
import io.raffi.JSONData;
import io.raffi.JSONArray;
import io.raffi.JSONObject;

// Initializing with simple data types
JSONData boolValue = new JSONData ( false );
JSONData intValue = new JSONData ( 3e-2 );
JSONData doubleValue = new JSONData ( 3.0E4 );
JSONData stringValue = new JSONData ( "test" );
JSONData nullValue = new JSONData ( JSONData.Type.NULL );
// Initializing with a JSONArray
JSONArray array = new JSONArray ();
array.add ( "Some data" );
JSONData arrayValue = new JSONData ( array );
// Initializing with a JSONObject
JSONObject object = new JSONObject ();
object.put ( "Some", "data" );
JSONData objectValue = new JSONData ( object );
```
###### Get Type Methods (Returns Boolean):
These methods return a boolean that specifies whether the actual data type saved within the JSONData object matches the one in the method name.  Below is a chart that descibes the behavior of the methods.  A '✓' symbol means it returns true, while the '✗' symbol means it returns false.

               | Boolean | Integer | Double | String | JSONArray | JSONObject | NULL
-------------- | :-----: | :-----: | :----: | :----: | :-------: | :--------: | :--: 
`isBool ()`    |    ✓    |    ✗    |   ✗    |   ✗    |     ✗     |     ✗      |  ✗
`isInt ()`     |    ✗    |    ✓    |   ✗    |   ✗    |     ✗     |     ✗      |  ✗
`isDouble ()`  |    ✗    |    ✗    |   ✓    |   ✗    |     ✗     |     ✗      |  ✗
`isString ()`  |    ✗    |    ✗    |   ✗    |   ✓    |     ✗     |     ✗      |  ✗
`isArray ()`   |    ✗    |    ✗    |   ✗    |   ✗    |     ✓     |     ✗      |  ✗
`isObject ()`  |    ✗    |    ✗    |   ✗    |   ✗    |     ✗     |     ✓      |  ✗
`isNull ()`    |    ✗    |    ✗    |   ✗    |   ✗    |     ✗     |     ✗      |  ✓


###### Get Data Methods (Returns Data):
These function return the data in whatever format you want.  If the actual data type doesn't match the requested data type, then the function tries to cast the the original data type into the requested on.  Below is a quick reference table showing which data types can be converted to which data types.

               | Boolean | Integer | Double | String | JSONArray | JSONObject | NULL
-------------- | :-----: | :-----: | :----: | :----: | :-------: | :--------: | :--: 
`getBool ()`   |    ✓    |    ✓    |   ✓    |   ✓    |     ✗     |     ✗      |  ✓
`getInt ()`    |    ✓    |    ✓    |   ✓    |   ✓    |     ✗     |     ✗      |  ✗
`getDouble ()` |    ✓    |    ✓    |   ✓    |   ✓    |     ✗     |     ✗      |  ✗
`getString ()` |    ✓    |    ✓    |   ✓    |   ✓    |     ✓     |     ✓      |  ✓
`getArray ()`  |    ✗    |    ✗    |   ✗    |   ✗    |     ✓     |     ✗      |  ✗
`getObject ()` |    ✗    |    ✗    |   ✗    |   ✗    |     ✗     |     ✓      |  ✗
###### Using the JSONData class:
This class' `toString ()` function is overwritten, so one can easily us this class in a print statment like this:
```java
import io.raffi.JSON.JSONData;

JSONData data = new JSONData ( 3 );
System.out.println ( data );
```

### Using the JSONArray class:
###### Add method (Overloaded Parameters):
These functions are overloaded and simply create a new JSONData object in the overloaded contructors.  For this reason, if you call `array.get ( 0 )`, given that array is an instance of JSONArray and 0 is a valid index, will return a JSONData object.
```java
import io.raffi.JSON.JSONArray

JSONArray array = new JSONArray ();
array.add ( true );
array.add ( 3 );
array.add ( 3.0 );
array.add ( "3" );
array.add ( new JSONArray () );
array.add ( new JSONObject () );
```
###### Get methods (Returns data):
These methods simply call the similar get method in the stored JSONData object. An example of how to use these methods can be seen in the code snippet below:
```java
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONArray;
import io.raffi.JSON.JSONObject;

Parser parser = new Parser ( "[true,2,\"test\",{},[]]" );
JSONArray array = parser.getResult ().getArray ();

System.out.println ( array.get ( 0 ) );          // Returns a JSONData object and prints "true"
System.out.println ( array.getBool ( 0 ) );      // Returns true and prints "true"
System.out.println ( array.getInt ( 1 ) );       // Returns 2 and prints "2"
System.out.println ( array.getDouble ( 1 ) );    // Returns 2.0 and prints "2.0"
System.out.println ( array.getString ( 2 ) );    // Returns "test" and prints "test"
System.out.println ( array.getArray ( 4 ) );     // Returns [] and prints "[]"
System.out.println ( array.getObject ( 3 ) );    // Returns {} and prints "{}"
```
###### Traversing in foreach array:
```java
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONArray;

JSONArray array = new JSONArray ();
array.add ( 1 );
array.add ( "2" );
array.add ( 3 );

for ( JSONData element : array ) {
	System.out.println ( element );
}
```



TODO:

	Fix the string literal function in parser to detect valid uni-code escape characters. (convert to symbol)
	constructor for JSONArray, automaticly add all data into it




