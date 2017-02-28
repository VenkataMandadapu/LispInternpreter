
/* Token class contains methods to set a token and get a token. 
 * The Token string contains the name of the tokens (LITERAL, NUMERIC, ERROR, EOF, ( AND )).
 * Name is used in case it is a string token or error token and value in case it is a numeric token.*/

public class Token{
	String token;
	String name;
	int value;
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	public void setToken(String s){
		this.token = s;
	}
	
	public String getToken(){
		return this.token;
	}
	
	public void setString(String name){
		this.name = name;
	}
	
	public String getString(){
		return this.name;
	}
	
	public Boolean isAtom(){
		if (token.equals("LITERAL") || token.equals("NUMERIC")){
			return true;
		}
		else{
			return false;
		}
	}
}