
import java.util.Scanner;
import java.util.Arrays;
import java.io.*;

public class Catalogue {

    private Products[] m_products;

    public Catalogue() {
        m_products = new Products[100];
    }

    public void addProducts(Products p) {
        if (!Product_Exist(p.getProductId())) {
            for (int i = 0; i < 100; i++) {
                if (m_products[i] == null) {
                    m_products[i] = p;
                    System.out.println(m_products[i].getProductId() + " Added to Catalogue");
                    break;
                }
            }
        } else {
            System.out.println("Error cannot add product with the same ID");
        }
    }

    public void deleteProducts(String id) {
        if (Product_Exist(id)) {
            for (int i = 0; i < 100; i++) {
                if (m_products[i].getProductId().equals(id)) {
                    m_products[i] = null;
                    System.out.println(id + " Removed from Catalogue");
                    break;
                }
            }

        } else {
            System.out.println("Error Product ID \"" + id + "\" not found");
        }

    }

    public void modifyProducts(String id) {
        if (Product_Exist(id)) {
            Scanner sc = new Scanner(System.in);
            String newId, newName;
            double newPrice;

            for (int i = 0; i < 100; i++) {
                if (m_products[i].getProductId().equals(id)) {
                    System.out.println("Modifying Product " + id);
                    System.out.print("Enter New ID: ");
                    newId = sc.nextLine();
                    m_products[i].setProductId(newId);
                    System.out.print("Enter New Name: ");
                    newName = sc.nextLine();
                    m_products[i].setName(newName);
                    System.out.print("Enter New Price: ");
                    newPrice = sc.nextDouble();
                    m_products[i].setPrice(newPrice);
                    sc.nextLine();
                    break;
                }
            }
        } else {
            System.out.println("Error Product ID \"" + id + "\" not found");
        }
    }

    public boolean Product_Exist(String ProductId) {

        for (int i = 0; i < 100; i++) {
            if (m_products[i] != null) {
                if (m_products[i].getProductId().equals(ProductId)) {
                    return true;
                }
            }
        }
        return false;
    }

    //Save all products in catalogue Products array into Products.txt
    public void saveProducts() throws IOException {
        System.out.println("Saving Product file...");
        try {
            PrintWriter outputFile = new PrintWriter("Products.txt");
            for (int i = 0; i < 100; i++) {
                if (m_products[i] == null) {
                    break;
                }
                outputFile.println("ProductId:" + m_products[i].getProductId());
                outputFile.println("ProductName:" + m_products[i].getName());
                outputFile.println("ProductPrice:" + +m_products[i].getPrice());
                outputFile.print("\n");
            }
            outputFile.close();
            System.out.println("Products file Saved");
        } catch (Exception e) {
            System.out.print("Failed to save Products file");
        }
    }

    //Run loadProducts data from Products.txt 
    public void loadProducts() throws IOException {

        //Line of strings contain Id,Name,Price
        String productIdline;
        String productNameline;
        String productPriceline;

        //Use arrays because split method return arrays
        String[] productIdarr;
        String[] productNamearr;
        String[] productPricearr;

        //Final data
        String productId;
        String productName;
        double productPrice;
        File file = new File("Products.txt");

        try {
            Scanner inputFile = new Scanner(file);

            if (file.length() == 0) {
                System.out.println("Products.txt is empty");
            }

            for (int i = 0; i < 100 && inputFile.hasNext(); i++) {

                productIdline = inputFile.nextLine();
                productNameline = inputFile.nextLine();
                productPriceline = inputFile.nextLine();
                inputFile.nextLine();

                productIdarr = productIdline.split(":");
                productNamearr = productNameline.split(":");
                productPricearr = productPriceline.split(":");

                productId = productIdarr[1];
                productName = productNamearr[1];
                productPrice = Double.parseDouble(productPricearr[1]);

                Products product = new Products(productId, productName, productPrice);

                m_products[i] = product;
            }
            inputFile.close();
        } catch (Exception e) {
            if (!file.exists()) {
                System.out.println("Products.txt does not exist");
            } else {
                System.out.println("Failed to load Products file");
            }
        }

    }

    public Products getProduct(String id) {
        int index = 0;
        for (int i = 0; i < 100; i++) {
            if (m_products[i].getProductId().equals(id)) {
                index = i;
                break;
            }
        }
        return m_products[index];
    }

    //Display all Products in m_products array
    public void ListProducts() {
        for (int i = 0; i < 100; i++) {
            if (m_products[i] != null) {
                System.out.println("Product " + (i + 1));
                System.out.println("Product ID:" + m_products[i].getProductId());
                System.out.println("Name:" + m_products[i].getName());
                System.out.println("Price:" + m_products[i].getPrice());
                System.out.print("\n");
                continue;
            }
            break;
        }
    }

    public void displayProduct(String id) {
        for (int i = 0; i < 100; i++) {
            if (m_products[i].getProductId().equals(id)) {
                System.out.println("Product ID:" + m_products[i].getProductId());
                System.out.println("Name:" + m_products[i].getName());
                System.out.println("Price:" + m_products[i].getPrice());
                break;
            }
        }
    }
}

//Notes
/*
Catalogue ctl = new Catalogue();
ctl.loadProducts();
Products p1 = new Products("WC001","White Chocolate","White Chocolate",5.0);
Products p2 = new Products("DC001","Dark Chocolate","Dark Chocolate",5.6);

ctl.addProducts(p1);
ctl.saveProducts();
System.out.println(ctl.getProduct("WC001").getName());
 */
