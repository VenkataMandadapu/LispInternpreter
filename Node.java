public class Node{
	String value = "";
	String token = "";
	Node left = null;
	Node right = null;
	
	public void setValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public void setToken(String value){
		this.token = value;
	}
	
	public String getToken(){
		return this.token;
	}
}