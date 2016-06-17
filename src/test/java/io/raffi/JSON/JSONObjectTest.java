package io.raffi.JSON;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JSONObjectTest {

	@Test
	public void constructorTest () {
		assertTrue ( new JSONObject () != null );
		assertTrue ( new JSONObject ().size () == 0 );
		assertTrue ( new JSONObject ().put ( "a", 1 ).size () == 1 );
		assertTrue ( new JSONObject ().put ( "a", 1 ).put ( "b", 2 ).size () == 2 );
		assertTrue ( new JSONObject ().put ( "a", 1 ).put ( "b", 2 ).getInt ( "a" ) == 1 );
		assertTrue ( new JSONObject ().put ( "a", 1 ).put ( "b", 2 ).getInt ( "b" ) == 2 );
		assertTrue ( new JSONObject ().put ( "a", 1 ).put ( "b", 2 ).getInt ( "not here" ) == null );
	}

	@Test
	public void putTest () {
		assertTrue ( new JSONObject ().put ( "a", true ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", false ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", 0 ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", 0.0 ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", "" ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", "_" ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", JSONData.Type.NULL ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", new JSONObject () ) instanceof JSONObject );
		assertTrue ( new JSONObject ().put ( "a", new JSONArray () ) instanceof JSONObject );
	}

	@Test
	public void getBoolTest () {
		JSONObject object = new JSONObject ().put ( "a", true ).put ( "b", false );
		assertTrue ( object.getBool ( "a" ) == true );
		assertTrue ( object.getBool ( "b" ) == false );
		assertTrue ( object.getBool ( "not here" ) == null );
	}

	@Test
	public void getIntTest () {
		JSONObject object = new JSONObject ().put ( "a", 1 ).put ( "b", 0 );
		assertTrue ( object.getInt ( "a" ) == 1 );
		assertTrue ( object.getInt ( "b" ) == 0 );
		assertTrue ( object.getInt ( "not here" ) == null );
	}

	@Test
	public void getDoubleTest () {
		JSONObject object = new JSONObject ().put ( "a", 1.1 ).put ( "b", 0.1 );
		assertTrue ( object.getDouble ( "a" ) == 1.1 );
		assertTrue ( object.getDouble ( "b" ) == 0.1 );
		assertTrue ( object.getDouble ( "not here" ) == null );
	}

	@Test
	public void getStringTest () {
		JSONObject object = new JSONObject ().put ( "a", "b" ).put ( "b", "a" );
		assertTrue ( object.getString ( "a" ).equals ("b") );
		assertTrue ( object.getString ( "b" ).equals ("a") );
		assertTrue ( object.getString ( "not here" ) == null );
	}

	@Test
	public void getObjectTest () {
		JSONObject object = new JSONObject ().put ( "a", new JSONObject () ).put ( "b", new JSONObject () );
		assertTrue ( object.getObject ( "a" ) instanceof JSONObject );
		assertTrue ( object.getObject ( "b" ) instanceof JSONObject );
		assertTrue ( object.getObject ( "not here" ) == null );
	}

	@Test
	public void getArrayTest () {
		JSONObject object = new JSONObject ().put ( "a", new JSONArray () ).put ( "b", new JSONArray () );
		assertTrue ( object.getArray ( "a" ) instanceof JSONArray );
		assertTrue ( object.getArray ( "b" ) instanceof JSONArray );
		assertTrue ( object.getArray ( "not here" ) == null );
	}

	@Test
	public void toStringTest () {
		JSONObject object = new JSONObject ();
		object.put ( "true", true ).put ( "false", false );
		object.put ( "null", JSONData.Type.NULL );
		object.put ( "int", 0 ).put ( "double", 0.1 );
		object.put ( "string", "test" );
		object.put ( "object", new JSONObject () );
		object.put ( "array", new JSONArray () );
		String expected = "{\"null\":null,\"string\":\"test\",\"array\":[],\"double\":0.1,\"true\":true,\"false\":false,\"int\":0,\"object\":{}}";
		assertTrue ( object.toString ().equals ( expected ) );
	}

}