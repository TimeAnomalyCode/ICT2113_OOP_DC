
import java.util.Scanner;

public class Validate {
    
     static Scanner sc = new Scanner(System.in);
    
    public static int main_enter_option() {
        int option;
        boolean inrange;
        
        while (true){
            try {
                System.out.print("Delicious Chocolate\n"
                    +"1.New Order\n"
                    + "2.View Order Maintenance\n"
                    + "3. Exit\n"
                    + "Enter your option : ");
                option = sc.nextInt();
                inrange = option > 0 && option < 4;
                if (!inrange) {
                    System.out.println("Invalid input please enter another option\n");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid input please enter another option\n");
                continue;
            }
            return option;
        }
    }
    
    
}
