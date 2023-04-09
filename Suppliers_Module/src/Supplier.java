import java.util.LinkedList;

public class Supplier {
    SupplierCard card;
    Agreement agreement;
    LinkedList<Order> order_history;


    public LinkedList<Order> getOrder_history() {
        return order_history;
    }

    public void addOrderToHistory(Order a)
    {
        order_history.add(a);
    }


    public Supplier(SupplierCard card, Agreement agreement, LinkedList<Order> order_history) {
        this.card = card;
        this.agreement = agreement;
        this.order_history = order_history;
    }

    public SupplierCard getCard() {
        return card;
    }

    public Agreement getAgreement() {
        return agreement;
    }

    public Supplier(SupplierCard card, Agreement agreement) {
        this.card = card;
        this.agreement = agreement;
        this.order_history = new LinkedList<>();
    }

    public void PrintOrders()
    {
        for(int i=0;i<this.order_history.size(); i++){
            this.order_history.get(i).PrintOrder();
        }
    }
}
