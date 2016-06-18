package io.raffi.JSON;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ParserTest {

	@Test
	public void simpleTest () {
		assertTrue ( new Parser ( "true" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "false" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "null" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "0" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "-0" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "1.0" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "\"test\"" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "[]" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "{}" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( "\"\"" ).getResult ().getString () == "" );
	}

	@Test
	public void constructorTest () {
		InputStream stream = new ByteArrayInputStream ( "{}".getBytes ( StandardCharsets.UTF_8 ) );
		assertTrue ( new Parser ( "[0,1,2,3]" ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( new File ( "assets/json/test_1.json" ) ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( stream ).getResult () instanceof JSONData );
		try {
			new Parser ( new File ( "assets/json/does_not_exist.json" ) );
			fail ("JSONException was not thrown!");
		}
		catch ( JSONException e ) {}
		try {
			InputStream test = null;
			new Parser ( test );
			fail ("JSONException was not thrown!");
		}
		catch ( JSONException e ) {}
		try {
			File test = null;
			new Parser ( test );
			fail ("JSONException was not thrown!");
		}
		catch ( JSONException e ) {}
		try {
			String test = null;
			new Parser ( test );
			fail ("JSONException was not thrown!");
		}
		catch ( JSONException e ) {}
	}

	@Test
	public void dataTest () {
		assertTrue ( new Parser ( new File ( "assets/json/test_1.json" ) ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( new File ( "assets/json/test_2.json" ) ).getResult () instanceof JSONData );
		assertTrue ( new Parser ( new File ( "assets/json/test_3.json" ) ).getResult () instanceof JSONData );
		try {
			new Parser ( "1284712893418723648716234871623478213" );
			fail ("JSONException was not thrown!");
		}
		catch ( JSONException e ) {}
		try {
			new Parser ( "a{}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[3 4]" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[3,4,]" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[,]" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "," );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{\"a\" 1}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{\"\":1 \"\":2}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[\"a\":]" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{:a}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "tru" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "fals" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "nul" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{:a}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "{7}" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "+0" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[3,3 a]" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
		try {
			new Parser ( "[1,2];" );
			fail ("SyntacticError was not thrown!");
		}
		catch ( SyntacticError e ) {}
	}

}