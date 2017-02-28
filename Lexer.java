import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;

/*Lexer class contains getNextToken function.
 * getNextToken function will keep reading character one by one until it encounters space, return, carriage return , open parenthesis or close parenthesis.
 * When it encounter parenthesis it will unread the parenthesis then form token with rest of the input and will reread the parenthesis in the next run.
 * If an invalid token occur it will return ERROR token and EOF token for end of file token. All the token are of the format in Token class which is present in Token.java file.
 * The Lexer is called from the main present in Compiler.java. */ 

public class Lexer{
	PushbackReader pr = new PushbackReader(new InputStreamReader(System.in));
	
	Token current = new Token();
	
	public void Init() throws IOException{
		this.current = getNextToken();
	}
	
	public Token getCurrent(){
		return this.current;
	}
	
	public void moveToNext() throws IOException {
		this.current = getNextToken();
	}
	
	public Token getNextToken() throws IOException{
		String s = "";
		String v = "";
		String invalid = "";
		
		
		while(true){
			int value = pr.read();
			
			if(value == -1){
				if(!invalid.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(invalid);
					t1.setToken("ERROR");
					return t1;
				}
				else if(!s.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(s);
					t1.setToken("LITERAL");
					return t1;
				}
				else if(!v.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setValue(Integer.parseInt(v));
					t1.setToken("NUMERIC");
					return t1;
				}
				else{
					Token t1 = new Token();
					t1.setToken("EOF");
					return t1;
				}
			}
			
			char val = (char)value;
			
			if(48 <= value && value <= 57){
				if(!invalid.isEmpty()){
					invalid = invalid + val;
				}
				if (!s.isEmpty()){
					s = s + val;
				}
				
				else{
					v = v + val;
				}
			}
			
			if(65 <= value && value <= 90){
				if(!invalid.isEmpty()){
					invalid = invalid + val;
				}
				else if(!v.isEmpty()){
					invalid = v + val;
					v = "";
				}
				else {
					s = s + val;
				}
			}
		
			if(val == '('){
				if(!invalid.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(invalid);
					t1.setToken("ERROR");
					return t1;
				}
				else if(!s.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(s);
					t1.setToken("LITERAL");
					return t1;
				}
				else if(!v.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setValue(Integer.parseInt(v));
					t1.setToken("NUMERIC");
					return t1;
				}
				else{
					Token t1 = new Token();
					t1.setToken("(");
					return t1;
				}
			}
			else if(val == ' ' || val == '\n' || val == '\r'){
				if(!invalid.isEmpty()){
					Token t1 = new Token();
					t1.setString(invalid);
					t1.setToken("ERROR");
					return t1;
				}
				else if (!s.isEmpty()){
					Token t1 = new Token();
					t1.setString(s);
					t1.setToken("LITERAL");
					return t1;
				}
				else if(!v.isEmpty()){
					Token t1 = new Token();
					t1.setValue(Integer.parseInt(v));
					t1.setToken("NUMERIC");
					return t1;
				}
			}
			else if(val == ')'){
				if(!invalid.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(invalid);
					t1.setToken("ERROR");
					return t1;
				}
				else if(!s.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setString(s);
					t1.setToken("LITERAL");
					return t1;
				}
				else if(!v.isEmpty()){
					pr.unread(value);
					Token t1 = new Token();
					t1.setValue(Integer.parseInt(v));
					t1.setToken("NUMERIC");
					return t1;
				}
				else{
					Token t1 = new Token();
					t1.setToken(")");
					return t1;
				}
			}
		}
	}
}