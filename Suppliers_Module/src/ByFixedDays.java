import java.util.Scanner;

public class ByFixedDays extends MethoodSupply{

    private int[] supplydays = new int[7];
    Scanner input = new Scanner(System.in);

    public ByFixedDays(String x, int[] arr)
    {
        super(x);
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==0)
                supplydays[i]=0;
            else
                supplydays[i]=1;
        }
    }
    @Override
    public void deliveryDays() {
        String[] dayNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for (int i = 0; i < supplydays.length; i++) {
            if(supplydays[i]==1)
            {
                System.out.println(dayNames[i]);
            }
        }
    }

    @Override
    public void SetDeliveryDays() {
        int choice=9;
        for(int i=0; i<7; i++)
        {
            this.supplydays[i]=0;
        }
        while(choice!=0)
        {
            System.out.println("Choose new supply days");
            System.out.println("1.Sunday");
            System.out.println("2.Monday");
            System.out.println("3.Tuesday");
            System.out.println("4.Wednesday");
            System.out.println("5.Thursday");
            System.out.println("6.Friday");
            System.out.println("7.Saturday");
            choice=input.nextInt();

            if(choice>0 && choice<8)
            {
                this.supplydays[choice]=1;
            }
            else
                System.out.println("You insert invalid input");
        }

    }
}
