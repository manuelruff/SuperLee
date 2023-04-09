import java.util.Scanner;
public class PrecentageDiscount{
    private Range amountRange;
    private int percentage;

    public PrecentageDiscount(Range amountRange, int percentage) {
        this.amountRange = amountRange;
        this.percentage = percentage;
    }

    public Range getAmountRange() {
        return amountRange;
    }

    public void setAmountRange(Range amountRange) {
        this.amountRange = amountRange;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public static PrecentageDiscount CreateDiscount()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a lower limit of the quantity :");
        int min=input.nextInt();
        System.out.println("Enter a upper limit of the quantity :");
        int max=input.nextInt();
        System.out.println("Enter the discount in percentage :");
        int percentage=input.nextInt();
        Range range=new Range(min,max);
        PrecentageDiscount discount=new PrecentageDiscount(range,percentage);
        return discount;
    }

    public void printDiscount()
    {
        System.out.println("Range: "+amountRange.getMin()+" - " +amountRange.getMax()+" the discount is "+this.percentage+"%");
    }
}
