package io.raffi.JSON;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JSONDataTest {

	private Parser parser;
	private JSONArray array;
	private String json = "[true,null,0,0.6,\"test\",{},[]]";

	public JSONDataTest () {
		parser = new Parser ( json );
		array = parser.getResult ().getArray ();
	}

	@Test
	public void constructorBooleanTest () {
		JSONData data = new JSONData ( true );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () == true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () != true );
	}

	@Test
	public void constructorNullTest () {
		JSONData data = new JSONData ( JSONData.Type.NULL );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () == true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () != true );
		try {
			JSONData nil = new JSONData ( JSONData.Type.BOOLEAN );
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
	}

	@Test
	public void constructorIntTest () {
		JSONData data = new JSONData ( 0 );
		assertTrue ( data.isInt () == true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () != true );
	}

	@Test
	public void constructorDoubleTest () {
		JSONData data = new JSONData ( 0.0 );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () == true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () != true );
	}

	@Test
	public void constructorStringTest () {
		JSONData data = new JSONData ( "" );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () == true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () != true );
	}

	@Test
	public void constructorObjectTest () {
		JSONData data = new JSONData ( new JSONObject () );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () == true );
		assertTrue ( data.isArray () != true );
	}

	@Test
	public void constructorArrayTest () {
		JSONData data = new JSONData ( new JSONArray () );
		assertTrue ( data.isInt () != true );
		assertTrue ( data.isDouble () != true );
		assertTrue ( data.isBool () != true );
		assertTrue ( data.isString () != true );
		assertTrue ( data.isNull () != true );
		assertTrue ( data.isObject () != true );
		assertTrue ( data.isArray () == true );
	}

	@Test
	public void isBooleanTest () {
		assertTrue ( array.get ( 0 ).isInt () != true );
		assertTrue ( array.get ( 0 ).isDouble () != true );
		assertTrue ( array.get ( 0 ).isBool () == true );
		assertTrue ( array.get ( 0 ).isString () != true );
		assertTrue ( array.get ( 0 ).isNull () != true );
		assertTrue ( array.get ( 0 ).isObject () != true );
		assertTrue ( array.get ( 0 ).isArray () != true );
	}

	@Test
	public void isNullTest () {
		assertTrue ( array.get ( 1 ).isInt () != true );
		assertTrue ( array.get ( 1 ).isDouble () != true );
		assertTrue ( array.get ( 1 ).isBool () != true );
		assertTrue ( array.get ( 1 ).isString () != true );
		assertTrue ( array.get ( 1 ).isNull () == true );
		assertTrue ( array.get ( 1 ).isObject () != true );
		assertTrue ( array.get ( 1 ).isArray () != true );
	}

	@Test
	public void isIntTest () {
		assertTrue ( array.get ( 2 ).isInt () == true );
		assertTrue ( array.get ( 2 ).isDouble () != true );
		assertTrue ( array.get ( 2 ).isBool () != true );
		assertTrue ( array.get ( 2 ).isString () != true );
		assertTrue ( array.get ( 2 ).isNull () != true );
		assertTrue ( array.get ( 2 ).isObject () != true );
		assertTrue ( array.get ( 2 ).isArray () != true );
	}

	@Test
	public void isDoubleTest () {
		assertTrue ( array.get ( 3 ).isInt () != true );
		assertTrue ( array.get ( 3 ).isDouble () == true );
		assertTrue ( array.get ( 3 ).isBool () != true );
		assertTrue ( array.get ( 3 ).isString () != true );
		assertTrue ( array.get ( 3 ).isNull () != true );
		assertTrue ( array.get ( 3 ).isObject () != true );
		assertTrue ( array.get ( 3 ).isArray () != true );
	}

	@Test
	public void isStringTest () {
		assertTrue ( array.get ( 4 ).isInt () != true );
		assertTrue ( array.get ( 4 ).isDouble () != true );
		assertTrue ( array.get ( 4 ).isBool () != true );
		assertTrue ( array.get ( 4 ).isString () == true );
		assertTrue ( array.get ( 4 ).isNull () != true );
		assertTrue ( array.get ( 4 ).isObject () != true );
		assertTrue ( array.get ( 4 ).isArray () != true );
	}

	@Test
	public void isObjectTest () {
		assertTrue ( array.get ( 5 ).isInt () != true );
		assertTrue ( array.get ( 5 ).isDouble () != true );
		assertTrue ( array.get ( 5 ).isBool () != true );
		assertTrue ( array.get ( 5 ).isString () != true );
		assertTrue ( array.get ( 5 ).isNull () != true );
		assertTrue ( array.get ( 5 ).isObject () == true );
		assertTrue ( array.get ( 5 ).isArray () != true );
	}

	@Test
	public void isArrayTest () {
		assertTrue ( array.get ( 6 ).isInt () != true );
		assertTrue ( array.get ( 6 ).isDouble () != true );
		assertTrue ( array.get ( 6 ).isBool () != true );
		assertTrue ( array.get ( 6 ).isString () != true );
		assertTrue ( array.get ( 6 ).isNull () != true );
		assertTrue ( array.get ( 6 ).isObject () != true );
		assertTrue ( array.get ( 6 ).isArray () == true );
	}

	@Test
	public void getBoolTest () {
		assertTrue ( new JSONData ( false ).getBool () == false );
		assertTrue ( new JSONData ( true ).getBool () == true );
		assertTrue ( new JSONData ( 0 ).getBool () == false );
		assertTrue ( new JSONData ( 1 ).getBool () == true );
		assertTrue ( new JSONData ( 0.0 ).getBool () == false );
		assertTrue ( new JSONData ( 1.0 ).getBool () == true );
		assertTrue ( new JSONData ( 1.3 ).getBool () == true );
		assertTrue ( new JSONData ( 1.5 ).getBool () == true );
		assertTrue ( new JSONData ( 1.7 ).getBool () == true );
		assertTrue ( new JSONData ( "1" ).getBool () == true );
		assertTrue ( new JSONData ( "1.8" ).getBool () == true );
		assertTrue ( new JSONData ( "" ).getBool () == false );
		assertTrue ( new JSONData ( "_" ).getBool () == true );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getBool () == false );
		
		try {
			new JSONData ( new JSONObject () ).getBool ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( new JSONArray () ).getBool ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
	}

	@Test
	public void getIntTest () {
		assertTrue ( new JSONData ( false ).getInt () == 0 );
		assertTrue ( new JSONData ( true ).getInt () == 1 );
		assertTrue ( new JSONData ( 0 ).getInt () == 0 );
		assertTrue ( new JSONData ( 1 ).getInt () == 1 );
		assertTrue ( new JSONData ( 0.0 ).getInt () == 0 );
		assertTrue ( new JSONData ( 1.1 ).getInt () == 1 );
		assertTrue ( new JSONData ( 1.5 ).getInt () == 2 );
		assertTrue ( new JSONData ( 1.7 ).getInt () == 2 );
		assertTrue ( new JSONData ( "1.8" ).getInt () == 2 );
		assertTrue ( new JSONData ( "1" ).getInt () == 1 );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getInt () == null );
		try {
			new JSONData ( "" ).getInt ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( "_" ).getInt ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( new JSONObject () ).getInt ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( new JSONArray () ).getInt ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
	}

	@Test
	public void getDoubleTest () {
		assertTrue ( new JSONData ( false ).getDouble () == 0.0 );
		assertTrue ( new JSONData ( true ).getDouble () == 1.0 );
		assertTrue ( new JSONData ( 0 ).getDouble () == 0.0 );
		assertTrue ( new JSONData ( 1 ).getDouble () == 1.0 );
		assertTrue ( new JSONData ( 0.0 ).getDouble () == 0.0 );
		assertTrue ( new JSONData ( 1.1 ).getDouble () == 1.1 );
		assertTrue ( new JSONData ( 1.5 ).getDouble () == 1.5 );
		assertTrue ( new JSONData ( 1.7 ).getDouble () == 1.7 );
		assertTrue ( new JSONData ( "1.8" ).getDouble () == 1.8 );
		assertTrue ( new JSONData ( "1" ).getDouble () == 1.0 );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getDouble () == null );
		try {
			new JSONData ( "" ).getDouble ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( "_" ).getDouble ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( new JSONObject () ).getDouble ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
		try {
			new JSONData ( new JSONArray () ).getDouble ();
			fail ("JSONException not caught!");
		}
		catch ( JSONException e ) {}
	}

	@Test
	public void getStringTest () {
		assertTrue ( new JSONData ( false ).getString ().equals ("false") );
		assertTrue ( new JSONData ( true ).getString ().equals ("true") );
		assertTrue ( new JSONData ( 0 ).getString ().equals ("0") );
		assertTrue ( new JSONData ( 1 ).getString ().equals ("1") );
		assertTrue ( new JSONData ( 0.0 ).getString ().equals ("0.0") );
		assertTrue ( new JSONData ( 1.1 ).getString ().equals ("1.1") );
		assertTrue ( new JSONData ( 1.5 ).getString ().equals ("1.5") );
		assertTrue ( new JSONData ( 1.7 ).getString ().equals ("1.7") );
		assertTrue ( new JSONData ( "1.8" ).getString ().equals ("1.8") );
		assertTrue ( new JSONData ( "1" ).getString ().equals ("1") );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getString ().equals ("null") );

		assertTrue ( new JSONData ( "" ).getString ().equals ("") );
		assertTrue ( new JSONData ( "_" ).getString ().equals ("_") );
		assertTrue ( new JSONData ( new JSONObject () ).getString ().equals ("{}") );
		assertTrue ( new JSONData ( new JSONObject ().put ( "a", 1 ) ).getString ().equals ("{\"a\":1}") );
		assertTrue ( new JSONData ( new JSONArray () ).getString ().equals ("[]") );
		assertTrue ( new JSONData ( new JSONArray ().add ( 1 ) ).getString ().equals ("[1]") );
	}

	@Test
	public void getObjectTest () {
		assertTrue ( new JSONData ( false ).getObject () == null );
		assertTrue ( new JSONData ( true ).getObject () == null );
		assertTrue ( new JSONData ( 0 ).getObject () == null );
		assertTrue ( new JSONData ( 1 ).getObject () == null );
		assertTrue ( new JSONData ( 0.0 ).getObject () == null );
		assertTrue ( new JSONData ( 1.0 ).getObject () == null );
		assertTrue ( new JSONData ( 1.1 ).getObject () == null );
		assertTrue ( new JSONData ( 1.5 ).getObject () == null );
		assertTrue ( new JSONData ( 1.7 ).getObject () == null );
		assertTrue ( new JSONData ( "1.8" ).getObject () == null );
		assertTrue ( new JSONData ( "1" ).getObject () == null );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getObject () == null );
		assertTrue ( new JSONData ( new JSONObject () ).getObject () != null );
		assertTrue ( new JSONData ( new JSONArray () ).getObject () == null );
	}

	@Test
	public void getArrayTest () {
		assertTrue ( new JSONData ( false ).getArray () == null );
		assertTrue ( new JSONData ( true ).getArray () == null );
		assertTrue ( new JSONData ( 0 ).getArray () == null );
		assertTrue ( new JSONData ( 1 ).getArray () == null );
		assertTrue ( new JSONData ( 0.0 ).getArray () == null );
		assertTrue ( new JSONData ( 1.0 ).getArray () == null );
		assertTrue ( new JSONData ( 1.1 ).getArray () == null );
		assertTrue ( new JSONData ( 1.5 ).getArray () == null );
		assertTrue ( new JSONData ( 1.7 ).getArray () == null );
		assertTrue ( new JSONData ( "1.8" ).getArray () == null );
		assertTrue ( new JSONData ( "1" ).getArray () == null );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).getArray () == null );
		assertTrue ( new JSONData ( new JSONObject () ).getArray () == null );
		assertTrue ( new JSONData ( new JSONArray () ).getArray () != null );
	}

	@Test
	public void toStringTest () {
		assertTrue ( new JSONData ( false ).toString ().equals ("false") );
		assertTrue ( new JSONData ( true ).toString ().equals ("true") );
		assertTrue ( new JSONData ( 0 ).toString ().equals ("0") );
		assertTrue ( new JSONData ( 1 ).toString ().equals ("1") );
		assertTrue ( new JSONData ( 0.0 ).toString ().equals ("0.0") );
		assertTrue ( new JSONData ( 1.0 ).toString ().equals ("1.0") );
		assertTrue ( new JSONData ( 1.1 ).toString ().equals ("1.1") );
		assertTrue ( new JSONData ( 1.5 ).toString ().equals ("1.5") );
		assertTrue ( new JSONData ( 1.7 ).toString ().equals ("1.7") );
		assertTrue ( new JSONData ( "1.8" ).toString ().equals ("\"1.8\"") );
		assertTrue ( new JSONData ( "1" ).toString ().equals ("\"1\"") );
		assertTrue ( new JSONData ( JSONData.Type.NULL ).toString ().equals ("null") );
		assertTrue ( new JSONData ( new JSONObject () ).toString ().equals ("{}") );
		assertTrue ( new JSONData ( new JSONObject ().put ( "a", 1 ) ).toString ().equals ("{\"a\":1}") );
		assertTrue ( new JSONData ( new JSONArray () ).toString ().equals ("[]") );
		assertTrue ( new JSONData ( new JSONArray ().add ( 1 ) ).toString ().equals ("[1]") );
		assertTrue ( this.array.toString ().equals ( this.json ) );
	}

}