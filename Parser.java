import java.io.IOException;

public class Parser{
	public String interpreterPrint(Node node){
		String s = "";
		if((node.left == null) && (node.right == null)){
			s = s + node.getValue();
		}
		else{
			s = s+ "(";
			while(!((node.left == null) && (node.right == null))){
				s = s + interpreterPrint(node.left);
				s = s + " ";
				node = node.right;
			}
			if(node.getValue().equals("NIL")){
				s = s.substring(0,s.length()-1)+ ")";
			}
			else{
				s = s + ". "+node.getValue()+")";
			}
		}
		
		return s;
	}
	public void prettyPrint(Node node){
		if((node.left == null) && (node.right == null)){
			System.out.print(node.getValue());
		}
		else {
			System.out.print("(");
			prettyPrint(node.left);
			System.out.print(" . ");
			prettyPrint(node.right);
			System.out.print(")");
		}
	}
	
	public void parseExpression(Lexer lexer, Node node) throws IOException{
		if(lexer.getCurrent().isAtom()){
			if((lexer.getCurrent().getToken()).equals("LITERAL")){
				node.setValue(lexer.getCurrent().getString());
				node.setToken("LITERAL");
			}
			else{
				node.setValue(Integer.toString((lexer.getCurrent().getValue())));
				node.setToken("NUMERIC");
			}
			lexer.moveToNext();
		} 
		else if((lexer.getCurrent().getToken()).equals("(")){
			lexer.moveToNext();
			while(!((lexer.getCurrent().getToken()).equals(")"))){
				node.left = new Node();
				node.right = new Node();
				parseExpression(lexer,node.left);
				node = node.right;
			}
			node.setValue("NIL");
			lexer.moveToNext();
		}
		else{
			if(lexer.getCurrent().getToken().equals("ERROR")){
				System.out.println("ERROR: Invalid Token "+ lexer.getCurrent().getString());
			}
			else if(lexer.getCurrent().getToken().equals(")")){
				System.out.println("ERROR: Token encountered Closing Paranthesis, Expected EOF or Open paranthesis.");
			} 
			else if(lexer.getCurrent().getToken().equals("EOF")){
				System.out.println("ERROR: Token encountered EOF. Expected Expression or Closing paranthesis.");
			}
			System.exit(1);
		}
	}
	
	public void parseStart(Lexer lexer) throws IOException{
		do{
			Node head = new Node();
			parseExpression(lexer,head);
			Interpreter interpreter = new Interpreter();
			//prettyPrint(head);
			Node result = interpreter.eval(head);
			System.out.println(interpreterPrint(result));
		}while(!((lexer.getCurrent().getToken()).equals("EOF")));
	}
}