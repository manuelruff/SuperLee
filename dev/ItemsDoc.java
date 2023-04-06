import java.util.ArrayList;
import java.util.List;

public class ItemsDoc {
    private String ID;
    private String siteName;
    private List<Item> itemList;

    public ItemsDoc(String ID, String siteName) {
        this.ID = ID;
        this.siteName = siteName;
        itemList = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public String getSiteName() {
        return siteName;
    }
    public void addItemToDoc(Item item)
    {
        itemList.add(item);
    }

    public void addListOfItems(List<Item> items){
        if (items.isEmpty())
            return;
        for (Item item : items){
            this.addItemToDoc(item);
        }
    }
    public void deleteItem(Item removeItem){
        itemList.remove(removeItem);
    }
    public boolean isEmpty(){
        return itemList.isEmpty();
    }

}
