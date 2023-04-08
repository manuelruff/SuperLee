import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemsDoc {
    private String ID;
    private String siteName;
    private List<Item> itemList;

    private static int count;
    public ItemsDoc(String siteName) {
        count++;
        this.ID = String.valueOf(count);
        this.siteName = siteName;
        itemList = new ArrayList<>();

    }
    public void printItemsDoc() {
        System.out.println("Item Document details:");
        System.out.println("Document ID: " + ID);
        System.out.println("Destination: " + siteName);
        for(Item item : itemList)
            item.printItem();
    }


    public String getID() {
        return ID;
    }

    public String getSiteName() {
        return siteName;
    }
    public void addItemToDoc(Item newItem)
    {
        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                if (Objects.equals(newItem.getName(), itemList.get(i).getName())) {
                    itemList.get(i).setQuantity(newItem.getQuantity() + itemList.get(i).getQuantity());
                    return;
                }
            }
        }
        itemList.add(newItem);
    }
    public void addListOfItems(List<Item> items){
        if (items.isEmpty())
            return;
        for (Item item : items){
            this.addItemToDoc(item);
        }
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void deleteItem(Item removeItem){
        itemList.remove(removeItem);
    }
    public boolean isEmpty(){
        return itemList.isEmpty();
    }

    public static int getCount(){
        return count;
    }

}
