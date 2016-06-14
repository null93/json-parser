TODO:

	Fix the string literal function in parser to detect valid uni-code escape characters.


Parser parser = new Parser ( Main.class.getClassLoader ().getResourceAsStream ( "test.json" ) );
Parser parser = new Parser ( new File ( "test.json" ) );
Parser parser = new Parser ( "{\"test\":\"data\"}" );