import java.text.DateFormat;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Order {
    private String m_orderId;
    private String m_orderDate;
    private Item[] m_items;
    private static int counter = 1;

    Order(){
        m_items = new Item[100];
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
        //System.out.println(m_orderId);
        //System.out.println(m_orderDate);
    }

    public void saveOrder(){

    }

    public void printOrder(){

    }

    private String verifyDate(){
        Scanner sc = new Scanner(System.in);
        String date;

        do {
            System.out.print("Enter Date: ");
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
}

/*
Notes
Order od = new Order();

od.createOrder();
od.createOrder();
od.createOrder();
*/
