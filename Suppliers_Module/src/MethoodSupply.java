public abstract class MethoodSupply {
    protected String kind;
    public MethoodSupply(String x) {
        this.kind=x;
    }
    public abstract void deliveryDays();
    public void methoodType()
    {
        System.out.println(kind);
    }

    public abstract void SetDeliveryDays();
}
