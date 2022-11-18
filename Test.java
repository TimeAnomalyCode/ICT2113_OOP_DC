
import java.io.IOException;
//This is used to test if a class works
//It is to be removed after the project is completed
//Please remove any code after testing
public class Test {
    public static void main(String args[]) throws IOException{
        Catalogue ctl = new Catalogue();
        ctl.loadProducts();
        
        Order od = new Order(ctl);
        od.LoadOrder();
        Products p1 = new Products("WC","White Chocolate",5.0);
        Products p2 = new Products("DC","Dark Chocolate",5.6);
        ctl.addProducts(p1);
        ctl.addProducts(p2);

        od.createOrder();
        od.createOrder();
        od.ListOneOrder("S10001");
        od.ListOneOrder("S10002");
        od.ListOneOrder("S10003");
        
        ctl.saveProducts();
        od.saveOrder();
    }
}
