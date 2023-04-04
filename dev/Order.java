import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private String destination;
    private Zone zone;
    private List<Item> itemList;

    public Order(String destination, Zone zone) {
        this.destination = destination;
        this.zone = zone;
        itemList = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public Zone getZone() {
        return zone;
    }
    /*
    this function adds an item to the order
     */
    public void addItemToOrder(Item item)
    {
        for(int i=0;i< itemList.size();i++)
        {
            if(Objects.equals(item.getName(), itemList.get(i).getName()))
            {
             itemList.get(i).setQuantity(item.getQuantity() + itemList.get(i).getQuantity());
            }
            itemList.add(item);
        }
    }
    /*
    this function gets a storage condition as a parameter and returns a list of items
    from the order with the same storage condition and removes those items from the order
     */
    public List<Item> getItemsForShipping(Training ability)
    {
        List<Item> deliveryList = new ArrayList<>();
        if(itemList.isEmpty())
        {
            System.out.println("Item list is empty");
        }
        else {
            for(int i=0; i< itemList.size(); i++)
            {
                if(itemList.get(i).getStorageCondition() == ability)
                    deliveryList.add(itemList.remove(i));
            }
        }
        return deliveryList;
    }
    /*
        This function returns the first item storage condition
     */
    public Training firstItemType()
    {
        return itemList.get(0).getStorageCondition();
    }

}

