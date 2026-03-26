import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Type your expression:");
            String infix = scanner.nextLine();
            Converter converter=new Converter(infix);
            String postfixExpression=converter.toPostFix();
            ExpressionTree tree=new ExpressionTree(infix);
            Node root = tree.buildTree(postfixExpression);
            
            System.out.print("Prefix:" );
            tree.prefix(root);
            
            System.out.print("\nInfix:" );
            System.out.print("("); 
            tree.infix(root);
            System.out.print(")");
            
            System.out.print("\nPostfix:" );
            tree.postfix(root);
            System.out.println();
        }
    }
}

