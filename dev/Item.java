public class Item {
    private String name;
    private int quantity;
    private Training storageCondition;

    public Item(String name, int quantity, Training storageCondition) {
        this.name = name;
        this.quantity = quantity;
        this.storageCondition = storageCondition;
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
