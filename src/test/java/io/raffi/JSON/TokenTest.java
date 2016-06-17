package io.raffi.JSON;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TokenTest {

	@Test
	public void constructorTest () {
		assertTrue ( new Token ( Token.Type.TRUE, "", 1, 1 ).type == Token.Type.TRUE );
		assertTrue ( new Token ( Token.Type.FALSE, "", 1, 1 ).type == Token.Type.FALSE );
		assertTrue ( new Token ( Token.Type.NULL, "", 1, 1 ).type == Token.Type.NULL );
		assertTrue ( new Token ( Token.Type.STRING, "", 1, 1 ).type == Token.Type.STRING );
		assertTrue ( new Token ( Token.Type.NUMBER, "", 1, 1 ).type == Token.Type.NUMBER );
		assertTrue ( new Token ( Token.Type.LEFT_CURLY, "", 1, 1 ).type == Token.Type.LEFT_CURLY );
		assertTrue ( new Token ( Token.Type.RIGHT_CURLY, "", 1, 1 ).type == Token.Type.RIGHT_CURLY );
		assertTrue ( new Token ( Token.Type.LEFT_SQUARE, "", 1, 1 ).type == Token.Type.LEFT_SQUARE );
		assertTrue ( new Token ( Token.Type.RIGHT_SQUARE, "", 1, 1 ).type == Token.Type.RIGHT_SQUARE );
		assertTrue ( new Token ( Token.Type.COLON, "", 1, 1 ).type == Token.Type.COLON );
		assertTrue ( new Token ( Token.Type.COMMA, "", 1, 1 ).type == Token.Type.COMMA );
		assertTrue ( new Token ( Token.Type.UNKNOWN, "", 1, 1 ).type == Token.Type.UNKNOWN );
		assertTrue ( new Token ( Token.Type.EOT, "", 1, 1 ).type == Token.Type.EOT );
		assertTrue ( new Token ( null, "", 1, 1 ).type == Token.Type.UNKNOWN );
		assertTrue ( new Token ( null, "a", 1, 1 ).value.equals ("a") );
		assertTrue ( new Token ( null, "a", 1, 1 ).line == 1 );
		assertTrue ( new Token ( null, "a", 1, 1 ).column == 1 );
	}

}