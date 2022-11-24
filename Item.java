public class Item {
    private String m_itemid;
    private int m_weight;

    Item(String itemid, int weight) {
        //itemid(Eg. WC001, DC001)
        m_itemid = itemid;
        m_weight = weight;
    }

    public void setItemId(String itemid) {
        m_itemid = itemid;
    }

    public void setWeight(int weight) {
        m_weight = weight;
    }

    public String getItemId() {
        return m_itemid;
    }

    public int getWeight() {
        return m_weight;
    }
}
