import java.io.IOException;

/* Compiler class contains main method which will call getNextToken method in Lexer class until it encounters an EOF token or ERROR token.
 * Based on the token encountered the main class will increment the count of that particular token. 
 * In case of LITERAL token it will store the token in a String List called name and for NUMERIC token it will sum the values of all NUMERIC tokens.*/

public class Compiler{
	
	public static void main(String args[]) throws IOException{
		Lexer lexer = new Lexer();
		Parser parser = new Parser();
		lexer.Init();
		
		parser.parseStart(lexer);	
	}
}