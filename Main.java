
import java.io.IOException;
import java.util.Scanner;

public class Main {
    
    public static Scanner sc = new Scanner(System.in);
    // Declare catalogue;
    public static Catalogue ctl = new Catalogue();
    //Order using Catalogue
    public static Order od = new Order(ctl);

    public static void main(String args[]) throws IOException{
        System.out.println("Starting program...");
        
        // Load data from Products.txt to ctl Products array
        ctl.loadProducts();
        od.LoadOrder();
                
        int option;
        String Main_options = "\nDelicious Chocolate\n"
                    +"1. New Order\n"
                    + "2. View Order Maintenance\n"
                    + "3. View Products Maintenance\n"
                    + "4. Exit\n"
                    + "Enter your option : ";
        
        while (true) {
                        
            option =enter_option(Main_options,4);
            select_option(option);
            
        }
    }

    /* Main Options
    ----------------------------------------------------------------------------
     */
   
    // Select main options option 1-4
    public static void select_option(int option) throws IOException{
        switch (option) {
            case 1 -> {
                System.out.println("New Order\n");
            }
            case 2 -> {
                System.out.println("View Order Maintenance\n");
            }
            
            case 3 -> {     
                view_products_maintenance();

            }

            case 4 -> {                         
                System.out.println("\n");
                // Save Products data
                ctl.saveProducts();
                //Save Orders data Here
                od.saveOrder();
                System.out.println("Terminating program...");
                exit();
            }
        }

    }
    
    // option 1
    public static void new_order() {
        // declare new_order object
    }

    // option 2
    public static void view_order_maintenance() {
        String order_options = "\nOrder options\n"
                + "1. List Specified Order\n"
                + "2. List All Orders\n"
                + "3. Back to Main menu\n"
                + "Enter your option : ";
        int option;
        while (true){
            option = enter_option(order_options,3);

            if (option==3)
                break;
            select_order_option(option);
        }
    }

    //option 2 sub menu
    public static void select_order_option(int option) {
        switch (option){
            case 1 -> {}
            case 2 -> {od.ListAllOrders();}
        }
    }
    
    // option 3
    public static void view_products_maintenance() {
        
        String products_options = "\nProducts options\n"
                + "1. Add Product\n"
                + "2. Delete Product\n"
                + "3. Modify Product\n"
                + "4. Display Products\n"
                + "5. Back to Main menu\n"
                + "Enter your option : ";
        int option;
        while (true){
            option = enter_option(products_options,5);
            
            if (option==5)               
                break;
            select_products_option(option);           
        }
    }
    
    // option 3 sub options    
    public static void select_products_option(int option) {
        switch (option) {
            case 1 -> {
                
                enter_Products_add_product();
            }
            case 2 -> {
                
                enter_Products_delete_product();
            }
            
            case 3 -> {                
                
                enter_Products_modify_Product();
                
            }

            case 4 -> {                
                ctl.ListProducts();
            }
            
        }

    }
    
    // option 4
    public static void exit() {
        System.exit(0);
    }

    /* other functions
    ----------------------------------------------------------------------------
     */

    // Display options available, promp user to input, validate return option
    public static int enter_option(String options, int num_options){
        
        int option;
        boolean inrange;
        
        while (true){
            try {
                    System.out.print(options);
                    option = sc.nextInt();
                    sc.nextLine();
                    inrange = option > 0 && option < (num_options+1);
                    if (!inrange) {
                        System.out.println("Error option out of range");
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Error invalid input please enter another option");
                    sc.nextLine();
                }

        }
        return option;
    }
    
    public static void enter_Products_add_product(){
        
        String product_Id, product_Name;       
        double product_Price;

        
        while (true){
            System.out.print("\nAdding Product\n");
            // Product Id input
            product_Id = enter_Product_Id();
            product_Name = enter_Product_Name();
            product_Price = enter_Product_Price();

            Products product = new Products(product_Id,product_Name,product_Price);
            ctl.addProducts(product);
            add_More_Product();               
            break;
        }
    }
        
    public static void enter_Products_delete_product(){
        String product_Id;

        while (true){
            System.out.print("\nAdding Product\n");
            product_Id = enter_Product_Id();
            ctl.deleteProducts(product_Id);
            delete_More_Product();
            break;

        }
    }
    
    public static void enter_Products_modify_Product(){
        String product_Id;
        
        while (true){
            System.out.print("\nSelect which product to modify using product Id\n");
            product_Id = enter_Product_Id();
            
            
            
            ctl.modifyProducts(product_Id);
            // Product Id input     
            modify_More_Product();
            break;
        }
        
    }
       
    public static void add_More_Product(){
        int more_product;
        
        while (true){
            System.out.print("Do you want to add Product again(Y/N): ");
            
            more_product = isValidMoreProducts(sc.nextLine());
            
            if (more_product == 1){
                enter_Products_add_product();
            }
                       
            else if (more_product == 0){
                System.out.println("Error Invalid option please enter another option");
                continue;
            }
            break;
        }
    }
    
    public static void delete_More_Product(){
        int more_product;
        
        while (true){
            System.out.print("Do you want to remove Product again(Y/N): ");
            
            more_product = isValidMoreProducts(sc.nextLine());
            
            if (more_product == 1){
                enter_Products_delete_product();
            }
                       
            else if (more_product == 0){
                System.out.println("Error Invalid option please enter another option");
                continue;
            }
            break;
        }
    }
    
    public static void modify_More_Product(){
        int more_product;
        
        while (true){
            System.out.print("Do you want to modify Product again(Y/N): ");
            
            more_product = isValidMoreProducts(sc.nextLine());
            
            if (more_product == 1){
                enter_Products_modify_Product();
            }
                       
            else if (more_product == 0){
                System.out.println("Error Invalid option please enter another option");
                continue;
            }
            break;
        }
    }
       
    public static int isValidMoreProducts(String input){
            input.toUpperCase();

            if(input.equals("Y") || input.equals("YES")){
                return 1;
            }
            else if(input.equals("N") || input.equals("NO")){
                return -1;
            }
            else {
                return 0;
            }
        }
    
    public static String enter_Product_Id(){
        
        String product_Id;
        
        while (true){
            try{
                System.out.print("Enter Product Id :");
                product_Id = sc.nextLine();
                if (product_Id.isBlank()){
                    System.out.println("Error Product Id must contain value");
                    System.out.println("Please re-enter Product_Id");
                    continue;
                }
                
                else if (product_Id.length() != 2){
                    System.out.println("Error Product Id must contain 2 uppercase Alphabet");
                    System.out.println("Please re-enter Product_Id");
                    continue;
                }
                
                else if (!(product_Id.charAt(0) >= 'A' && product_Id.charAt(0) <= 'Z' && product_Id.charAt(1) >= 'A' && product_Id.charAt(1) <= 'Z')){
                    System.out.println("Error Product Id can only contain uppercase Alphabets");
                    System.out.println("Please re-enter Product Id");
                    continue;
                }
                
            } catch (Exception e) {
                 System.out.println("Error invalid input please enter again");
                 continue;
            }
            break;
        }
        return product_Id;
    }
    
    public static String enter_Product_Name(){
        String product_Name;
                
        while (true){
            try{
                System.out.print("Enter Product Name :");
                product_Name = sc.nextLine();
                if (product_Name.isBlank()){
                    System.out.println("Error Product Name must contain value");
                    System.out.println("Please re-enter Product Name");
                    continue;
                }
                                                
            } catch (Exception e) {
                 System.out.println("Error invalid input please enter again");
                 continue;
            }
            break;
        }
        return product_Name;
    }
    
    public static double enter_Product_Price(){
        double product_Price;
                
        while (true){
            try{
                System.out.print("Enter Product Price :");
                product_Price = sc.nextDouble();
                sc.nextLine();
                if (product_Price <= 0){
                    System.out.println("Error Product Price value must be bigger than 0");
                    System.out.println("Please re-enter Product Price");
                    continue;
                }
                                                
            } catch (Exception e) {
                 System.out.println("Error invalid input please enter again");
                 continue;
            }
            break;
        }
        return product_Price;
    }
}
