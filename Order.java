import java.text.DateFormat;
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
        if(counter < 10){
            m_orderId = "OID000" + counter;
        }
        else if (counter < 100) {
            m_orderId = "OID00" + counter;
        }
        else if (counter < 1000) {
            m_orderId = "OID0" + counter;
        }
        else{
            m_orderId = "OID" + counter;
        }
        counter++;

        m_orderDate = verifyDate();
    }

    public void saveOrder(){

    }

    public void printOrder(){

    }

    private String verifyDate(){
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

    private void enterProductIdWeight(){
        Scanner sc = new Scanner(System.in);
        Item it;
        String input,productId;
        int weight;

        do{
            System.out.println("Enter Product ID(Eg.WC001): ");
            productId = sc.nextLine();
            System.out.println("Enter Weight(Eg.10): ");
            weight = sc.nextInt();

            System.out.println("Do you want to enter more items(Y/N): ");
            input = sc.nextLine();

        } while (input == "Y");
    }

    private boolean isValidProductId(String id){
        try {
            m_ctl.getProduct(id);
        } catch (Exception ex){
            return false;
        }
        return true;
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
