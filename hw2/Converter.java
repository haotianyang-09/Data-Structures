
import java.util.*;

public class Converter{
    private String infixExpression;

    public Converter(String infixExpression){
        this.infixExpression=infixExpression;
     }

     public int precedence(String s) {
		if ((s.equals("+")) || (s.equals("-"))) {
			return 1;
		}

		else if ((s.equals("*")) || (s.equals("/")))  {
			return 2;
		}

		else if(s.equals("^")){
			return 3;
		}
        else{
            return 0;//(has priority of 0
        }
    }
    
    public boolean isOperand(String s){
        if(s.matches("\\d+")){//We do not consider negative numbers
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isOperator(String s){
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String toPostFix(){
        String postFix="";
        ArrayStack<String> outputStack = new ArrayStack<>();
        char[] expression = infixExpression.toCharArray();
        List<String> tokens = ParserHelper.parse(expression);

        
        for(int i = 0; i < tokens.size(); i++){
            String c = tokens.get(i); 
    
            if(isOperand(c)==true){
                postFix+=c+" ";
            }

            else {
                if(isOperator(c)==true){
                while (outputStack.isEmpty()==false && precedence(outputStack.top()) >= precedence(c)) {
                    postFix+=outputStack.pop();
                    postFix+=" ";
                }
                outputStack.push(c);
            }

            else if (c.equals("(")) {
                outputStack.push(c);
            } 

            else{//it is )
                while (!outputStack.isEmpty() && !outputStack.top().equals("(")) {
                    postFix+=outputStack.pop();
                    postFix+=" ";//Pop everything until we encounter the first (
                }
                outputStack.pop(); //Discard the (
            }
        }
    }

        while (outputStack.isEmpty()==false) {
            postFix+=outputStack.pop();
            postFix+=" ";
        }

        return postFix.trim();
     }
     
} 