package Bussiness.ShipBussiness;

import Bussiness.Training;
import Bussiness.Zone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private String destination;
    private Zone zone;
    private List<Item> itemList;

    private String ID;
    private  static int count;
    public Order(String destination, Zone zone) {
        count++;
        this.ID = String.valueOf(count);
        this.destination = destination;
        this.zone = zone;
        itemList = new ArrayList<>();
    }

    public String getDestination() {
        return destination;
    }

    public void addListOfItems(List<Item> items){
        itemList = items;
    }

    public Zone getZone() {
        return zone;
    }
    /*
    this function adds an item to the order
     */
    public void addItemToOrder(Item item)
    {
        if (!itemList.isEmpty()) {
            for (int i = 0; i < itemList.size(); i++) {
                if (Objects.equals(item.getName(), itemList.get(i).getName())) {
                    itemList.get(i).setQuantity(item.getQuantity() + itemList.get(i).getQuantity());
                    return;
                }
            }
        }
        itemList.add(item);
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
            return null;
        }
        else {
            for(int i=itemList.size() - 1; i >= 0 ; i--)
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


    public boolean checkIfEmpty(){
        return itemList.isEmpty();
    }

    public void printOrder() {
        System.out.println("Order ID: " + ID);
        System.out.println("Destination: " + destination);
        if (itemList.isEmpty())
            System.out.println("No items currently in the order");
        else {
            System.out.println("Items: ");
            for (Item item : itemList) {
                item.printItem();
            }
        }
    }
}

