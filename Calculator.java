import java.util.Scanner;
 
public class Calculator {
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
 char operation;
 double num1,num2,result;
do{
        System.out.print("Enter the first number: ");
        num1 = sc.nextDouble();
        System.out.print("Enter the second number: ");
        num2 = sc.nextDouble();
 
   System.out.print("Enter the type of operation you want to perform (+, -, *, /, %): ");
    
         operation = sc.next().charAt(0);
       
 
   
    
        switch (operation) {
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '%':
                result = num1 % num2;
                break;
            case '/':
                if(num2!=0){
                result =num1/ num2;
                }
                else{
                    System.out.println("cannot divide by zero");
                    continue;
                }
                break;
            default:
                System.out.println("Invalid operation");
                continue;
        }
      System.out.println("num1 is="+num1);
      System.out.println("num2 is="+num2);
      System.out.println("operation is="+operation);
       System.out.println("Result  is="+result);
      
      System.out.println(" do you want  another operation(y/n):");
    } 
      while(sc.next().charAt(0)=='y');
   sc.close();
}

}