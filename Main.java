
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
     
        Validate checker = new Validate();
        int option;
        boolean inrange = false;

        while (true) {
            option = checker.main_enter_option();
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
                System.out.println("Terminating program...");
                exit();
            }
        }

    }

}
