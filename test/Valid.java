import io.raffi.JSON.JSONArray;
import io.raffi.JSON.JSONData;
import io.raffi.JSON.JSONObject;
import io.raffi.JSON.Parser;

import org.junit.runner.notification.Failure;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Valid {

	private String json = "[true,null,-0,0.6,\"test\",{}]";

	@Test
	public void testIsCommand () {
		// Parse data string and get JSONArray
		Parser parser = new Parser ( json );
		JSONArray array = parser.getResult ().getArray ();
		// Test to see when value is a boolean
		assertTrue ( "Should be false", array.get ( 0 ).isInt () != true );
		assertTrue ( "Should be false", array.get ( 0 ).isDouble () != true );
		assertTrue ( "Should be false", array.get ( 0 ).isBool () == true );
		assertTrue ( "Should be false", array.get ( 0 ).isString () != true );
		assertTrue ( "Should be false", array.get ( 0 ).isNull () != true );
		assertTrue ( "Should be false", array.get ( 0 ).isObject () != true );
		assertTrue ( "Should be false", array.get ( 0 ).isArray () != true );
	}

	public static void main ( String [] args ) {
      Result result = JUnitCore.runClasses(Valid.class);
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
	}

}