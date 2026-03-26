public class ExpressionTree {
    private Node root;
	private String expression;

	public ExpressionTree (String expression)
	{
		root = null;
		this.expression=expression;
	}
	

	public Node buildTree(String postfixExpression) {
		ArrayStack<Node> stack = new ArrayStack<>();
	
		
		for (String token : postfixExpression.split(" ")) {
			if (isOperator(token)) {
				Node right = stack.pop();
			    Node left = stack.pop();
			    Node node = new Node(token, left, right);
			    stack.push(node);
			} 
			
			else {
				Node node = new Node(token);
			    stack.push(node);
			}
		}
		root = stack.pop();
		return root;
	}

	public boolean isOperator(String s){
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
            return true;
        }
        else{
            return false;
        }
    }
    

	private void leftChild(Node t, Object o)
	//create a left child for node t
	{
		t.leftChild = new Node(o);
	}
	
	private void rightChild(Node t, Object o)
	//create a right child for node t
	{
		t.rightChild = new Node(o);
	}

    public void prefix(Node t) {
        if (t != null) {
            System.out.print(t);
            prefix(t.leftChild);
            prefix(t.rightChild);
        }
    }

    public void infix(Node t)  
	{
		if(t.leftChild != null)  //assume all operators are binary operands
		{
			System.out.print("("); 
			infix(t.leftChild);
			System.out.print(")");
		}
		System.out.print(t);
		if(t.leftChild != null)  //assume all operators are binary operands
		{
			System.out.print("("); 
			infix(t.rightChild);
			System.out.print(")");
		}
	}


    public void postfix(Node t) {
        if (t != null) {
           postfix(t.leftChild);
            postfix(t.rightChild);
			System.out.print(t);
            // print t;
        }
    }
    //The code is directly copied from ExpressionTree.txt in class
	
}

