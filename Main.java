
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        int option;
        boolean inrange = false;

        while (true) {
            System.out.print("\n1.New Order\n"
                    + "2.View Order Maintenance\n"
                    + "3. Exit\n"
                    + "Enter your option : ");

            Scanner sc = new Scanner(System.in);
            option = enter_option(sc);
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
    // prompt user to input option and check if it's integer
    public static int enter_option(Scanner sc) {
        int option = 0;
        boolean inrange = false;

        try {
            option = sc.nextInt();
            inrange = validate_input(option);
            if (!inrange) {
                System.out.println("Invalid input please enter another option\n");
            }
        } catch (Exception e) {
            System.out.println("Invalid input please enter another option\n");
        }
        return option;
    }

    // check if option is inrange of 1-3
    public static boolean validate_input(int option) {
        boolean inrange = false;
        if (option > 0 && option < 4) {
            inrange = true;
        }
        return inrange;
    }

    // check option to take action
    public static void select_option(int option) {
        switch (option) {
            case 1 -> {
                System.out.println("New Order");
            }
            case 2 -> {
                System.out.println("View Order Maintenance");
            }
            case 3 -> {
                System.out.println("Terminating program...");
                exit();
            }
        }

    }

}
