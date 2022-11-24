
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

                    newId = Main.enter_Product_Id();
                    if (Product_Exist(newId) && !newId.equals(m_products[i].getProductId())) {
                        System.out.println("Error Cannot change Product Id to an existing Product Id");
                        break;
                    } else {
                        m_products[i].setProductId(newId);
                        newName = Main.enter_Product_Name();
                        m_products[i].setName(newName);
                        newPrice = Main.enter_Product_Price();
                        m_products[i].setPrice(newPrice);
                        System.out.println("Product " + id + " Successfully Modified");
                    }

                    break;
                }
            }
        } else {
            System.out.println("Error Product ID \"" + id + "\" not found");
        }
    }

    private boolean Product_Exist(String ProductId) {

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
        System.out.println("Saving Product data...");
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
            System.out.println("Products data Saved");
        } catch (Exception e) {
            System.out.print("Failed to save Products data");
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
            System.out.println("Loading Products...");

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
            System.out.println("Products loaded\n");
            inputFile.close();
        } catch (Exception e) {
            if (!file.exists()) {
                System.out.println("Products.txt does not exist\n");
            } else {
                System.out.println("Failed to load Products file\n");
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
        String ProductId;
        String ProductName;
        Double ProductPrice;
        System.out.printf("%-10s%-20s%-20s%-10s\n", "No.", "Product ID", "Name", "Price ($)");
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < 100; i++) {

            if (m_products[i] != null) {

                ProductId = m_products[i].getProductId();
                ProductName = m_products[i].getName();
                ProductPrice = m_products[i].getPrice();
                System.out.printf("%-10d%-20s%-20s%-10.2f\n", (i + 1), ProductId, ProductName, ProductPrice);
                continue;
            }
            break;
        }
        System.out.println("-----------------------------------------------------------");
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
