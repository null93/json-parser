import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONArray;
import io.raffi.JSON.JSONObject;
import io.raffi.JSON.Parser;

public class Example {

    public static void main ( String [] args ) {
        Parser parser = new Parser ("{" +
            "\"key_1\":    true," +
            "\"key_2\":    2," +
            "\"key_3\":    \"test\"," +
            "\"key_4\":    {}," +
            "\"key_5\":    []" +
        "}");

        JSONObject array = parser.getResult ().getObject ();

        System.out.println ( array.get ( "key_1" ) );          // Returns a JSONData object and prints 'true'
        System.out.println ( array.getBool ( "key_1" ) );      // Returns true and prints 'true'
        System.out.println ( array.getInt ( "key_2" ) );       // Returns 2 and prints '2'
        System.out.println ( array.getDouble ( "key_2" ) );    // Returns 2.0 and prints '2.0'
        System.out.println ( array.getString ( "key_3" ) );    // Returns 'test' and prints '"test"'
        System.out.println ( array.getArray ( "key_5" ) );     // Returns [] and prints '[]'
        System.out.println ( array.getObject ( "key_4" ) );    // Returns {} and prints '{}'
    }

}