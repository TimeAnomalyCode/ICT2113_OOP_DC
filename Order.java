import java.io.File;
import java.io.IOException;
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
    private int m_numOfItemsCounter = 0;

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
            m_items[m_counter - 2][m_numOfItems] = it;
            System.out.println(productId + " Added to " + m_orderId[m_counter - 2]);
        } while (enterMoreItems());
    }


    public void saveOrder() throws IOException{
        System.out.println("Saving Order data...");
        try {
            PrintWriter outputFile = new PrintWriter("Orders.txt");
            m_numOfItemsCounter = 0;
            for(int i = 0; i < m_counter; i++){
                if(m_items[i][0] == null){
                    break;
                }
                m_numOfItems = ActualNumberOfItems(m_items);

                outputFile.println("OrderId:" + m_orderId[i]);
                outputFile.println("OrderDate:" + m_orderDate[i]);

                outputFile.print("ProductId:");
                for(int j = 0; j < m_numOfItems; j++){
                    outputFile.print(m_items[i][j].getItemId());
                    if(j != m_numOfItems - 1) {
                        outputFile.print(",");
                    }
                }

                outputFile.print("\nWeight:");
                for(int j = 0; j < m_numOfItems; j++){
                    outputFile.print(m_items[i][j].getWeight());
                    if(j != m_numOfItems - 1) {
                        outputFile.print(",");
                    }
                }
                outputFile.print("\n\n");
            }
            outputFile.close();
            System.out.println("Order data Saved");
        } catch (Exception e) {
            System.out.print("Failed to save Orders data");
        }
    }

    public void LoadOrder() throws IOException{
        String orderIdline;
        String orderDateline;
        String productIdline;
        String weightline;

        String[] orderIdarr;
        String[] orderDatearr;
        String[] productIdarr;
        String[] weightarr;

        String orderId;
        String orderDate;
        String[] productId = new String[100];
        int[] weight = new int[100];

        File file = new File("Orders.txt");

        try {
            Scanner inputFile = new Scanner(file);
            System.out.println("Loading Orders...");

            if(file.length() == 0){
                System.out.println("Orders.txt is empty");
            }

            for(int i = 0; i < 100 && inputFile.hasNext(); i++){
                orderIdline = inputFile.nextLine();
                orderDateline = inputFile.nextLine();
                productIdline = inputFile.nextLine();
                weightline = inputFile.nextLine();
                inputFile.nextLine();

                productIdarr = null;
                weightarr = null;

                orderIdarr = orderIdline.split(":");
                orderDatearr = orderDateline.split(":");
                productIdarr = productIdline.split("[:,]+");
                weightarr = weightline.split("[:,]+");

                orderId = orderIdarr[1];
                orderDate = orderDatearr[1];
                //Verify if productIdarr == weightarr, else error
                //Cannot add it to item class
                for(int j = 1; j < productIdarr.length; j++){
                    productId[j - 1] = productIdarr[j];
                }

                for(int k = 1; k < weightarr.length; k++){
                    weight[k - 1] = Integer.parseInt(weightarr[k]);
                }

                m_orderId[i] = orderId;
                m_orderDate[i] = orderDate;
                for(int l = 0; l < productIdarr.length - 1; l++){
                    Item it = new Item(productId[l], weight[l]);
                    m_items[i][l] = it;
                }

                m_counter++;
            }
            System.out.println("Orders loaded");
            inputFile.close();

        } catch (Exception e){
            if (!file.exists()) {
                System.out.println("Orders.txt does not exist");
            } else {
                System.out.println("Failed to load Orders file");
            }
        }
    }

    public void ListOneOrder(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter OrderId(Eg.S10001): ");
        String orderId = sc.nextLine();
        try {
            boolean foundOrderId = false;
            String[] tempArr = orderId.split("S");
            m_numOfItemsCounter = Integer.parseInt(tempArr[1]) - 10001;
            m_numOfItems = ActualNumberOfItems(m_items);
            for(int i = 0; i < m_counter - 1; i++){

                if(orderId.equals(m_orderId[i])){
                    foundOrderId = true;
                    TableOrder(i, m_numOfItems);
                    break;
                }
            }
            if(!foundOrderId){
                System.out.println("OrderID not Found");
            }
        } catch (Exception e){
            System.out.println("Invalid OrderID");
        }
    }

    public void ListAllOrders(){
        m_numOfItemsCounter = 0;
        for(int i = 0; i < m_counter - 1; i++){
            m_numOfItems = ActualNumberOfItems(m_items);
            TableOrder(i, m_numOfItems);
        }
    }

    private void TableOrder(int i, int numOfItems){
        double total = 0.0;
        String itemNo = "";
        String name = "";
        int weight = 0;
        double per100 = 0.0;
        double price = 0.0;

        System.out.printf("OID: %s\t\t\tDate: %s\n",m_orderId[i],m_orderDate[i]);
        System.out.println("ItemNo#\t\tName\t\t\t\tWeight\t\tPer 100g\tPrice ($)");
        System.out.println("-----------------------------------------------------------------");
        for(int j = 0; j < numOfItems; j++){
            itemNo = m_items[i][j].getItemId();
            name = m_ctl.getProduct(itemNo).getName();
            weight = m_items[i][j].getWeight();
            per100 = m_ctl.getProduct(m_items[i][j].getItemId()).getPrice();
            price = (weight/100.0) * per100;
            System.out.printf("%s001\t\t%s\t\t%d\t\t\t%.2f\t\t%.2f\n",
                    itemNo, name, weight, per100, price);
            total += price;
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("TOTAL\t\t\t\t\t\t\t\t\t\t\t\t\t%.2f\n\n",total);
    }

    private int ActualNumberOfItems(Item[][] itemarr){
        int numOfItems = 0;
        for(int i = m_numOfItemsCounter; i < itemarr.length; i++){
            for(int j = 0; j < itemarr[i].length; j++){
                if(itemarr[i][j] == null){
                    numOfItems = j;
                    m_numOfItemsCounter++;
                    break;
                }
            }
            break;
        }

        return numOfItems;
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
            System.out.print("Enter Product ID(Eg.WC): ");
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

        m_numOfItems++;
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
