import java.text.DateFormat;
import java.util.Locale;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Order {
    private String m_orderId;
    private String m_orderDate;
    private Item[] m_items;
    private static int counter = 1;
    private Catalogue m_ctl;

    Order(Catalogue ctl){
        m_items = new Item[100];
        m_ctl = ctl;
    }

    public void createOrder(){
        Item it;
        String productId;
        int weigth;

        m_orderId = generateValidOID();
        System.out.println("OID: " + m_orderId);
        m_orderDate = enterDate();

        //Please convert this to do while
        productId = enterProductId();
        weigth = enterWeight();
        it = new Item(productId, weigth);
        m_items[counter - 2] = it;
        System.out.println("Product Added to " + m_orderId);

        while (true){
            if(enterMoreItems()){
                productId = enterProductId();
                weigth = enterWeight();
                it = new Item(productId, weigth);
                m_items[counter - 2] = it;
                System.out.println("Product Added to " + m_orderId);
            }
            else {
                break;
            }
        }
    }

    public void saveOrder(){

    }

    public void printOrder(){

    }

    private String generateValidOID(){
        String orderId;

        if(counter < 10){
            orderId = "OID000" + counter;
        }
        else if (counter < 100) {
            orderId = "OID00" + counter;
        }
        else if (counter < 1000) {
            orderId = "OID0" + counter;
        }
        else{
            orderId = "OID" + counter;
        }
        counter++;

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
