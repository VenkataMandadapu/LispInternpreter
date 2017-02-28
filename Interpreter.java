public class Interpreter{
	public boolean isList(Node node){
		Node temp = node;
		while (temp.right != null){
			temp = temp.right;
		}
		if(temp.getValue().equals("NIL")){
			return true;
		}
		return false;
	}
	public int length(Node node){
		if (!isList(node)){
			System.out.println("ERROR: Given input is not list");
			System.exit(1);
		}
		if (node.getValue().equals("NIL")){
			return 0;
		}
		return 1 + length(node.right);
	}
	public Node atom(Node node){
		Node result = new Node();
		if((node.left == null) && (node.right == null)){
			result.setValue("T");
			return result;
		}
		result.setValue("NIL");
		return result;
	}
	
	public Node Null(Node node){
		Node result = new Node();
		if(! ((node.left == null) && (node.right == null))){
			result.setValue("NIL");
			return result;
		}
		else if (! node.getValue().equals("NIL")){
			result.setValue("NIL");
			return result;
		}
		result.setValue("T");
		return result;
	}
	
	public Node Int(Node node){
		Node result = new Node();
		if(!(node.left == null) && (node.right == null)){
			result.setValue("NIL");
			return result;
		}
		else if(! node.getToken().equals("NUMERIC")){
			result.setValue("NIL");
			return result;
		}
		result.setValue("T");
		return result;
	}
	
	public Node car(Node node){
		if((node.left == null) && (node.right == null)){
			System.out.println("Error: Car function is undefined for leaf nodes and atoms");
			System.exit(1);
		}
		return node.left;
	}
	
	public Node cdr(Node node){
		if((node.left == null) && (node.right == null)){
			System.out.println("Error: Cdr function is undefined for leaf nodes and atoms");
			System.exit(1);
		}
		return node.right;
	}
	
	public Node plus(Node node1, Node node2){
		if(Int(node1).getValue().equals("NIL") || Int(node2).getValue().equals("NIL")){
			System.out.println("ERROR: Plus is undefined as one of the operands is not integer");
			System.exit(1);
		}
		Node result = new Node();
		result.setValue(Integer.toString(Integer.parseInt(node1.getValue())+Integer.parseInt(node2.getValue())));
		result.setToken("NUMERIC");
		return result;
	}
	
	public Node minus(Node node1, Node node2){
		if(Int(node1).getValue().equals("NIL") || Int(node2).getValue().equals("NIL")){
			System.out.println("ERROR: Minus is undefined as one of the operands is not integer");
			System.exit(1);
		}
		Node result = new Node();
		result.setValue(Integer.toString(Integer.parseInt(node1.getValue())-Integer.parseInt(node2.getValue())));
		result.setToken("NUMERIC");
		return result;
	}
	
	public Node times(Node node1, Node node2){
		if(Int(node1).getValue().equals("NIL") || Int(node2).getValue().equals("NIL")){
			System.out.println("ERROR: Times is undefined as one of the operands is not integer");
			System.exit(1);
		}
		Node result = new Node();
		result.setValue(Integer.toString(Integer.parseInt(node1.getValue())*Integer.parseInt(node2.getValue())));
		result.setToken("NUMERIC");
		return result;
	}
	
	public Node less(Node node1, Node node2){
		if(Int(node1).getValue().equals("NIL") || Int(node2).getValue().equals("NIL")){
			System.out.println("ERROR: Less is undefined as one of the operands is not integer");
			System.exit(1);
		}
		Node result = new Node();
		if (Integer.parseInt(node1.getValue()) < Integer.parseInt(node2.getValue())){
			result.setValue("T");
		}
		else{
			result.setValue("NIL");
		}
		return result;
	}
	
	public Node greater(Node node1, Node node2){
		if(Int(node1).getValue().equals("NIL") || Int(node2).getValue().equals("NIL")){
			System.out.println("ERROR: Greater is undefined as one of the operands is not integer");
			System.exit(1);
		}
		Node result = new Node();
		if (Integer.parseInt(node1.getValue()) > Integer.parseInt(node2.getValue())){
			result.setValue("T");
		}
		else{
			result.setValue("NIL");
		}
		return result;
	}
	
	public Node eq(Node node1, Node node2){
		if(!((node1.left == null) && (node1.right == null)) || !((node2.left == null) && (node2.right == null))){
			System.out.println("ERROR: EQ is undefined as one of the inputs is not a leaf or an atom");
			System.exit(1);
		}
		Node result = new Node();
		if(node1.getValue().equals(node2.getValue())){
			result.setValue("T");
			return result;
		}
		result.setValue("NIL");
		return result;
	}
	
	public Node cons(Node node1, Node node2){
		Node result = new Node();
		result.left = node1;
		result.right = node2;
		
		return result;
	}
	
	public void checkCond(Node node){
		if(atom(car(node)).getValue().equals("T")){
			System.out.println("ERROR: COND undefined as some Si is not a list or length not equal to 2");
			System.exit(1);
		}
		else if(length(car(node))!= 2){
			System.out.println("ERROR: COND undefined as some Si is not a list or length not equal to 2");
			System.exit(1);
		}
		if(! node.right.getValue().equals("NIL")){
			checkCond(cdr(node));
		}
		else{
			System.out.println("ERROR: COND undefined as some Si is not a list or length not equal to 2");
			System.exit(1);
		}
		
	}
	
	public Node eval(Node node){
		if(atom(node).getValue().equals("T") && node.getValue().equals("T")){
			return node;
		}
		else if(Null(node).getValue().equals("T")){
			return node;
		}
		else if(Int(node).getValue().equals("T")){
			return node;
		}
		
		else if(atom(node).getValue().equals("T")){
			System.out.println("ERROR: Eval is not defined as node is leaf or atom and it is not Int, T or NULL");
			System.exit(1);
		}
		
		else if(car(node).getValue().equals("PLUS")){
			if(length(node) != 3){
				System.out.println("ERROR: Plus is not defined as the length of the tree is not 3");
				System.exit(1); 
			}
			else if(!eval(car(cdr(node))).getToken().equals("NUMERIC") || !eval(car(cdr(cdr(node)))).getToken().equals("NUMERIC")){
				System.out.println("ERROR: Plus is not defined as one of the operands is not numeric");
				System.exit(1);
			}
			return plus(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}
		else if(car(node).getValue().equals("MINUS")){
			if(length(node) != 3){
				System.out.println("ERROR: Minus is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			else if(!eval(car(cdr(node))).getToken().equals("NUMERIC") || !eval(car(cdr(cdr(node)))).getToken().equals("NUMERIC")){
				System.out.println("ERROR: MINUS is not defined as given operands are not numeric");
				System.exit(1);
			}
			return minus(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}
		else if(car(node).getValue().equals("TIMES")){
			if(length(node) != 3){
				System.out.println("ERROR: Times is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			else if(!eval(car(cdr(node))).getToken().equals("NUMERIC") || !eval(car(cdr(cdr(node)))).getToken().equals("NUMERIC")){
				System.out.println("ERROR: TIMES is not defined as given operands are not numeric");
				System.exit(1);
			}
			return times(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}
		else if(car(node).getValue().equals("LESS")){
			if(length(node) != 3){
				System.out.println("ERROR: LESS is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			else if(!eval(car(cdr(node))).getToken().equals("NUMERIC") || !eval(car(cdr(cdr(node)))).getToken().equals("NUMERIC")){
				System.out.println("ERROR: LESS is not defined as given operands are not numeric");
				System.exit(1);
			}
			return less(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}
		
		else if(car(node).getValue().equals("GREATER")){
			if(length(node) != 3){
				System.out.println("ERROR: Greater is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			else if(!eval(car(cdr(node))).getToken().equals("NUMERIC") || !eval(car(cdr(cdr(node)))).getToken().equals("NUMERIC")){
				System.out.println("ERROR: GREATER is not defined as given operands are not numeric");
				System.exit(1);
			}
			return greater(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}
		
		else if(car(node).getValue().equals("EQ")){
			if(length(node) != 3){
				System.out.println("ERROR: EQ is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			else if(!atom(eval(car(cdr(node)))).getValue().equals("T") || !atom(eval(car(cdr(cdr(node))))).getValue().equals("T")){
				System.out.println("ERROR: EQ is not defined as given operands are not atoms or leaves");
				System.exit(1);
			}
			return eq(eval(car(cdr(node))),eval(car(cdr(cdr(node)))));
		}

		else if(car(node).getValue().equals("ATOM")){
			if(length(node) != 2){
				System.out.println("ERROR: Atom is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			return(atom(eval(car(cdr(node)))));
		}
		else if(car(node).getValue().equals("INT")){
			if(length(node) != 2){
				System.out.println("ERROR: Int is not defined as the length of the tree is not 2");
				System.exit(1);
			}
			return(Int(eval(car(cdr(node)))));
		}
		else if(car(node).getValue().equals("NULL")){
			if(length(node) != 2){
				System.out.println("ERROR: NULL is not defined as the length of the tree is not 2");
				System.exit(1);
			}
			return(Null(eval(car(cdr(node)))));
		}
		
		else if(car(node).getValue().equals("CAR")){
			if(length(node) != 2){
				System.out.println("ERROR: Car is not defined as the length of the tree is not 2");
				System.exit(1);
			}
			return(car(eval(car(cdr(node)))));
		}
		
		else if(car(node).getValue().equals("CDR")){
			if(length(node) != 2){
				System.out.println("ERROR: Cdr is not defined as the length of the tree is not 2");
				System.exit(1);
			}
			return(cdr(eval(car(cdr(node)))));
		}
		
		else if(car(node).getValue().equals("CONS")){
			if(length(node) != 3){
				System.out.println("ERROR: Cons is not defined as the length of the tree is not 3");
				System.exit(1);
			}
			return(cons(eval(car(cdr(node))),eval(car(cdr(cdr(node))))));
		}
		else if(car(node).getValue().equals("QUOTE")){
			if(length(node) != 2){
				System.out.println("ERROR: Quote is not defined as the length of the tree is not 2");
				System.exit(1);
			}
			return(car(cdr(node)));
		}
		else if(car(node).getValue().equals("COND")){
			if(length(node) < 2){
				System.out.println("ERROR: COND is not defined as the length of the tree is less than 2");
				System.exit(1);
			}
			checkCond(cdr(node));
			Node temp = cdr(node);
			while(! temp.getValue().equals("NIL")){
				if(! eval(car(car(temp))).getValue().equals("NIL")){
					return eval(car(cdr(car(temp))));
				}
				temp = cdr(temp);
			}
			
			System.out.println("ERROR: None of the conditions evaluated to true");
			System.exit(1);
		}
		
		else{
			System.out.println("ERROR: Eval is not defined for the function "+car(node).getValue());
			System.exit(1);
		}
		return null;
	}
}