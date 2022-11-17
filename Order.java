import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class Order {
    private String[] m_orderId;
    private String[] m_orderDate;
    private Item[][] m_items;
    private static int m_counter = 1;
    private Catalogue m_ctl;
    private int m_numOfItems = 0;

    Order(Catalogue ctl){
        m_items = new Item[100][100];
        m_orderId = new String[100];
        m_orderDate = new String[100];
        m_ctl = ctl;
    }

    public void createOrder(){
        Item it;
        String productId;
        int weight;

        m_orderId[m_counter - 1] = generateValidOID();
        System.out.println("OID: " + m_orderId[m_counter - 2]);
        m_orderDate[m_counter - 2] = enterDate();
        m_numOfItems = 0;

        do {
            productId = enterProductId();
            weight = enterWeight();
            it = new Item(productId, weight);
            m_items[m_counter - 2][m_numOfItems++] = it;
            System.out.println(productId + " Added to " + m_orderId[m_counter - 2]);
        } while (enterMoreItems());
    }

    public void saveOrder(){
        System.out.println("Saving Order data...");
        try {
            PrintWriter outputFile = new PrintWriter("Orders.txt");
            for(int i = 0; i < 100; i++){
                if(m_items.length == 0){
                    break;
                }
                outputFile.println("OrderId:" + m_orderId[i]);
                outputFile.println("OrderDate:" + m_orderDate[i]);

                for(int j = 0; j < 100; j++){
                    outputFile.println("Item:" + m_items[i][j]);
                }
            }
            outputFile.close();
            System.out.println("Order data Saved");
        } catch (Exception e) {
            System.out.print("Failed to save Orders data");
        }
    }

    public void LoadOrder(){

    }

    public void printOrder(String orderId){
        for(int i = 0; i < 100; i++){
            if(orderId.equals(m_orderId[i])){
                System.out.println("OID: " + m_orderId[i]);
                System.out.println("Date: " + m_orderDate[i]);
                for(int j = 0; j < m_numOfItems; j++){
                    System.out.println("Items: " + m_items[i][j].getItemId());
                }
            }
        }
    }

    private String generateValidOID(){
        int num = 10000 + m_counter;
        String orderId;

        orderId = "S" + num;
        m_counter++;

        return orderId;
    }

    private String enterDate(){
        Scanner sc = new Scanner(System.in);
        String date;

        do {
            System.out.print("Enter Date(Eg.12/12/2022): ");
            date = sc.nextLine();

            if(!isValidDate(date)){
                System.out.println("Invalid Date");
            }

        } while (!isValidDate(date));

        return date;
    }

    private boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try{
            dateFormat.parse(date.trim());
        } catch (ParseException pe){
            return false;
        }
        return true;
    }

    private String enterProductId(){
        Scanner sc = new Scanner(System.in);
        String productId;

        do{
            System.out.print("Enter Product ID(Eg.WC001): ");
            productId = sc.nextLine();

            if(!isValidProductId(productId)){
                System.out.println("Invalid ID");
            }

        } while(!isValidProductId(productId));

        return productId;
    }

    private boolean isValidProductId(String id){
        try {
            m_ctl.getProduct(id);
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    private int enterWeight(){
        Scanner sc = new Scanner(System.in);
        String inputWeight;
        int weight;

        do{
            System.out.print("Enter Weight(Eg.10): ");
            inputWeight = sc.nextLine();

            if(!isValidWeight(inputWeight)){
                System.out.println("Invalid Weight");
            }

        } while (!isValidWeight(inputWeight));

        weight = Integer.parseInt(inputWeight);
        return weight;
    }

    private boolean isValidWeight(String input){
        int weight;

        try {
            weight = Integer.parseInt(input);
            if(weight < 0){
                return false;
            }
            return true;

        } catch (Exception ex){
            return false;
        }
    }

    private boolean enterMoreItems(){
        Scanner sc = new Scanner(System.in);
        String input = "N";
        while (true){
            System.out.print("Do you want to enter more items(Y/N): ");
            input = sc.nextLine();

            if(isValidMoreItems(input) == 0) {
                System.out.println(isValidMoreItems(input));
                System.out.println("Invalid Answer");
                continue;
            }
            else {
                break;
            }
        }

        if (isValidMoreItems(input) == -1) {
            return false;
        }

        return true;
    }

    private int isValidMoreItems(String input){
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
}

/*
Notes
Order od = new Order();

od.createOrder();
od.createOrder();
od.createOrder();
*/

/*
Notes 2
Catalogue ctl = new Catalogue();
Order od = new Order(ctl);
Products p1 = new Products("WC001","White Chocolate","White Chocolate",5.0);
Products p2 = new Products("DC001","Dark Chocolate","Dark Chocolate",5.6);

ctl.addProducts(p1);
ctl.addProducts(p2);
*/
