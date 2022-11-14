import java.util.Scanner;
public class Catalogue {
    private Products[] m_products;

    public Catalogue() {
        m_products = new Products[100];
    }

    public void addProducts(Products p){
        for(int i = 0; i < 100; i++){
            if(m_products[i] == null){
                m_products[i] = p;
                System.out.println("Product Added to Catalogue");
                break;
            }
        }
    }

    public void deleteProducts(String id){
        for(int i = 0; i < 100; i++){
            if(m_products[i].getId().equals(id)){
                m_products[i] = null;
                break;
            }
        }
    }

    public void modifyProducts(String id){
        Scanner sc = new Scanner(System.in);
        String newId,newName,newCategory;
        double newPrice;

        for(int i = 0; i < 100; i++){
            if(m_products[i].getId().equals(id)){
                System.out.print("Enter ID: ");
                newId = sc.nextLine();
                System.out.print("Enter Name: ");
                newName = sc.nextLine();
                System.out.print("Enter Category: ");
                newCategory = sc.nextLine();
                System.out.print("Enter Price: ");
                newPrice = sc.nextDouble();
                sc.nextLine();
                break;
            }
        }
    }

    public void loadProducts(){

    }

    public Products getProduct(String id){
        int index = 0;
        for(int i = 0; i < 100; i++){
            if(m_products[i].getId().equals(id)){
                index = i;
                break;
            }
        }
        return m_products[index];
    }

    public void displayProduct(String id){
        for(int i = 0; i < 100; i++){
            if(m_products[i].getId().equals(id)){
                System.out.println("Product ID:" + m_products[i].getId());
                System.out.println("Name:" + m_products[i].getName());
                System.out.println("Category:" + m_products[i].getCategory());
                System.out.println("Price:" + m_products[i].getPrice());
                break;
            }
        }
    }
}

//Notes
/*
Catalogue ctl = new Catalogue();
Products p1 = new Products("WC001","White Chocolate","White Chocolate",5.0);
Products p2 = new Products("DC001","Dark Chocolate","Dark Chocolate",5.6);

ctl.addProducts(p1);
System.out.println(ctl.getProduct("WC001").getName());
*/
