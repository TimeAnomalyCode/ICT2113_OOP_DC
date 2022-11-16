
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws IOException{
        
        // Declare catalogue
        Catalogue ctl = new Catalogue();
        // Load data from Products.txt to ctl Products array
        ctl.loadProducts();
        
        Scanner sc = new Scanner(System.in);
        int option;
        
        while (true) {
                        
            option =enter_option(sc);
            select_option(option);
            
        }
    }

    /*Options
    ----------------------------------------------------------------------------
     */
    // option 1
    public static void new_order() {
        // declare new_order object
    }

    // option 2
    public static void view_order_maintenance() {
        // declare catalogue object
    }

    // option 3
    public static void exit() {
        System.exit(0);
    }

    /* other functions
    ----------------------------------------------------------------------------
     */

    // check option to take action
    public static void select_option(int option) {
        switch (option) {
            case 1 -> {
                System.out.println("New Order\n");
            }
            case 2 -> {
                System.out.println("View Order Maintenance\n");
            }
            
            case 3 -> {                
                System.out.println("View Products Maintenance\n");

            }

            case 4 -> {                
                System.out.println("Terminating program...");
                exit();
            }
        }

    }
    
    public static int enter_option(Scanner sc){
        int option;
        boolean inrange;
        
        while (true){
            System.out.print("Delicious Chocolate\n"
                        +"1.New Order\n"
                        + "2.View Order Maintenance\n"
                        + "3.View Products Maintenance\n"
                        + "4.Exit\n"
                        + "Enter your option : ");
            try {
                option = sc.nextInt();
                inrange = option_inrange(4,option);
                if (!inrange) {
                    System.out.println("Error option out of range\n");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Error invalid input please enter another option\n");
                sc.nextLine();
                continue;
            }
            return option;
        }     
    }
    
    public static boolean option_inrange(int num_options, int option){
        boolean inrange = option > 0 && option < (num_options+1);
        return inrange;
    }
    
    
}
