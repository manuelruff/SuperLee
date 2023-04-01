import java.time.LocalDateTime;
public class Cancellations {
    private int Amount;
    private String NameOfProduct;
    private String IDOfCancler;
    private String NameOfCancler;
    private LocalDateTime Time;

    public Cancellations(int Amount, String NameOfProduct,String IDOfCancler, String NameOfCancler){
        this.Amount=Amount;
        this.NameOfProduct=NameOfProduct;
        this.IDOfCancler=IDOfCancler;
        this.NameOfCancler=NameOfCancler;
        this.Time=LocalDateTime.now();
    }

    public void printMe(){
        System.out.println("time: "+ this.Time +
                "\n name of product: "+this.NameOfProduct +" amount: "+this.Amount +
                "\n cancelled by: "+this.NameOfCancler +" with ID: "+this.IDOfCancler  );
    }

    public int getYear(){
        return Time.getYear();
    }

    public int getMonth(){
        return Time.getMonthValue();
    }

    public int getDay(){
        return Time.getDayOfMonth();
    }
}
