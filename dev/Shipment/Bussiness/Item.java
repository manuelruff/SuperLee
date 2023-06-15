package Shipment.Bussiness;

public class Item {
    private String name;
    private int quantity;
    private Training storageCondition;

    public Item(String name, int quantity, Training storageCondition) {
        this.name = name;
        this.quantity = quantity;
        this.storageCondition = storageCondition;
    }
    public void printItem()
    {
        System.out.println("\tItem name: " + name);
        System.out.println("\tQuantity: "+ quantity);
        System.out.println("\tStorage Condition: " + storageCondition.name() + "\n");
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Item name: ").append(name).append("\n");
        sb.append("Quantity: ").append(quantity).append("\n");
        sb.append("Storage Condition: ").append(storageCondition.name()).append("\n");
        return sb.toString();
    }
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Training getStorageCondition() {
        return storageCondition;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
