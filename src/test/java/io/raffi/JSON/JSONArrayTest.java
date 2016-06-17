package io.raffi.JSON;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JSONArrayTest {

	@Test
	public void constructorTest ( ) {
		assertTrue ( new JSONArray () != null );
		assertTrue ( new JSONArray ().size () == 0 );
		assertTrue ( new JSONArray ().add ( 2 ).size () == 1 );
		assertTrue ( new JSONArray ().add ( 2 ).add ( 3 ).size () == 2 );
		try {
			new JSONArray ().get ( 0 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void addTest () {
		assertTrue ( new JSONArray ().add ( true ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( false ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( 0 ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( 0.0 ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( "" ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( "_" ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( new JSONObject () ) instanceof JSONArray );
		assertTrue ( new JSONArray ().add ( new JSONArray () ) instanceof JSONArray );
	}

	@Test
	public void getBoolTest () {
		JSONArray array = new JSONArray ().add ( true ).add ( false );
		assertTrue ( array.getBool ( 0 ) == true );
		assertTrue ( array.getBool ( 1 ) == false );
		try {
			array.getBool ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void getIntTest () {
		JSONArray array = new JSONArray ().add ( 1 ).add ( 0 );
		assertTrue ( array.getInt ( 0 ) == 1 );
		assertTrue ( array.getInt ( 1 ) == 0 );
		try {
			array.getInt ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void getDoubleTest () {
		JSONArray array = new JSONArray ().add ( 1.1 ).add ( 0.1 );
		assertTrue ( array.getDouble ( 0 ) == 1.1 );
		assertTrue ( array.getDouble ( 1 ) == 0.1 );
		try {
			array.getDouble ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void getStringTest () {
		JSONArray array = new JSONArray ().add ( 1.0 ).add ( "2" );
		assertTrue ( array.getString ( 0 ).equals ("1.0") );
		assertTrue ( array.getString ( 1 ).equals ("2") );
		try {
			array.getString ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void getObjectTest () {
		JSONArray array = new JSONArray ().add ( new JSONObject () ).add ( new JSONObject () );
		assertTrue ( array.getObject ( 0 ) instanceof JSONObject );
		assertTrue ( array.getObject ( 1 ) instanceof JSONObject );
		try {
			array.getObject ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void getArrayTest () {
		JSONArray array = new JSONArray ().add ( new JSONArray () ).add ( new JSONArray () );
		assertTrue ( array.getArray ( 0 ) instanceof JSONArray );
		assertTrue ( array.getArray ( 1 ) instanceof JSONArray );
		try {
			array.getArray ( 2 );
			fail ("IndexOutOfBoundsException not thrown!");
		}
		catch ( IndexOutOfBoundsException e ) {}
	}

	@Test
	public void toStringTest () {
		JSONArray array = new JSONArray ();
		array.add ( 1 ).add ( 2.0 ).add ( "test" ).add ( false ).add ( true );
		array.add ( new JSONArray () ).add ( new JSONObject () ).add ( JSONData.Type.NULL );
		assertTrue ( array.toString ().equals ( "[1,2.0,\"test\",false,true,[],{},null]" ) );
	}

}