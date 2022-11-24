public class Products {
    private String m_productid;
    private String m_name;
    private double m_price;

    Products(String productid, String name, double price) {
        // productid (Eg. WC, DC)
        m_productid = productid;
        m_name = name;

        m_price = price;
    }

    public void setProductId(String productid) {
        m_productid = productid;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setPrice(double price) {
        m_price = price;
    }

    public String getProductId() {
        return m_productid;
    }

    public String getName() {
        return m_name;
    }

    public double getPrice() {
        return m_price;
    }
}
