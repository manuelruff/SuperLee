import java.util.Scanner;

public class BySupplyDays extends MethoodSupply {

    int days;
    Scanner input = new Scanner(System.in);

    public BySupplyDays(String x,int days)
    {
        super(x);
        this.days=days;
    }

    @Override
    public void deliveryDays() {
        System.out.println("The delivery will ship in "+days + "Days");
    }

    @Override
    public void SetDeliveryDays() {
        System.out.println("Enter how many days is required for you to supply");
        int x=input.nextInt();
        this.days=x;
    }


}
