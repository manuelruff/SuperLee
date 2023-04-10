public class BySuperLee extends MethoodSupply{

    public BySuperLee(String x)
    {
        super(x);
    }

    @Override
    public void deliveryDays() {
        System.out.println("Transportation under SuperLee's responsibility");
    }

    @Override
    public void SetDeliveryDays() {
        System.out.println("Transportation under SuperLee's responsibility");
    }
}
