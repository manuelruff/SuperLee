import java.util.ArrayList;
import java.util.List;

public class Order {
    private String destination;
    private String zone;
    private List<Item> itemList;

    public Order(String destination, String zone) {
        this.destination = destination;
        this.zone = zone;
        itemList = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public String getZone() {
        return zone;
    }
    public void addItemToOrder(Item item)
    {
        for(int i=0;i< itemList.size();i++)
        {
            if(item.getName() == itemList.get(i).getName())
            {
             itemList.get(i).setQuantity(item.getQuantity() + itemList.get(i).getQuantity());
            }
            itemList.add(item);
        }
    }
    public List<Item> getItemsForShipping()
    {
        List<Item> deliveryList = new ArrayList<>();
        if(itemList.isEmpty())
        {
            System.out.println("Item list is empty");
        }
        else {

            String storingCondition = itemList.get(0).getStorageCondition().name();
            deliveryList.add(itemList.get(0));
            itemList.remove(0);
            for(int i=0; i< itemList.size(); i++)
            {
                if(itemList.get(i).getStorageCondition().name() == storingCondition)
                {
                    deliveryList.add(itemList.get(i));
                    itemList.remove(i);
                }
            }
        }
        return deliveryList;
    }

}
