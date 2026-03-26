public class PostfixCalculator{

    private String postfixExpression;

    public PostfixCalculator(String postfixExpression){
        this.postfixExpression=postfixExpression;
    }

    public static boolean isOperator(String o){
        if(o.equals("+")||o.equals("-")||o.equals("*")||o.equals("/")||o.equals("^")){
            return true;
        }
        else{
            return false;
        }
    }

    public static double Calculation(double num1, double num2, String s){
        if(s.equals("+")){
            return num1+num2;
        }
        else if(s.equals("-")){
            return num1-num2;
        }
        else if(s.equals("*")){
            return num1*num2;
        }
        else if(s.equals("/")){
            return num1/num2;
        }
        else{
            return Math.pow(num1, num2);
        }
    }

    public static double evaluate(String postfixExpression){
        String[] symbols = postfixExpression.split("\\s+");
        ArrayStack<Double> postflixStack = new ArrayStack<>();
        for(int i=0;i<symbols.length;i++){
            if(isOperator(symbols[i])==true){
                double num2=postflixStack.pop();
                double num1=postflixStack.pop();
                postflixStack.push(Calculation(num1, num2, symbols[i]));
            }
            else{
                postflixStack.push(Double.parseDouble(symbols[i]));
            }
        }
        return postflixStack.pop();
        
    }
}