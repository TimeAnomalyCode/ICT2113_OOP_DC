//This is used to test if a class works
//It is to be removed after the project is completed
//Please remove any code after testing
public class Test {
    public static void main(String args[]){
        Catalogue ctl = new Catalogue();
        Order od = new Order(ctl);
        Products p1 = new Products("WC","White Chocolate",5.0);
        Products p2 = new Products("DC","Dark Chocolate",5.6);

        ctl.addProducts(p1);
        ctl.addProducts(p2);
        od.createOrder();

        od.printOrder("S10001");
    }
}
