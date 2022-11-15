public class Products {
    private String m_id;
    private String m_name;
    private String m_category;
    private double m_price;

    Products(String id,String name,double price){
        m_id = id;
        m_name = name;

        m_price = price;
    }

    public void setId(String id){m_id = id;}
    public void setName(String name){m_name = name;}
    public void setPrice(double price){m_price = price;}

    public String getId(){return m_id;}
    public String getName(){return m_name;}
    public double getPrice(){return m_price;}
}
