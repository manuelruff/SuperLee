package Domain;

import java.time.LocalDateTime;
public class Cancellations {
    private double Amount;
    private String NameOfProduct;
    private String IDOfCanceller;
    private String NameOfCanceller;
    private LocalDateTime Time;

    public Cancellations(double Amount, String NameOfProduct,String IDOfCanceller, String NameOfCanceller){
        this.Amount=Amount;
        this.NameOfProduct=NameOfProduct;
        this.IDOfCanceller=IDOfCanceller;
        this.NameOfCanceller=NameOfCanceller;
        this.Time=LocalDateTime.now();
    }

    // the function prints the information about the cancel
    public void printMe(){
        System.out.println("time: "+ this.Time +
                "\n name of product: "+this.NameOfProduct +" amount: "+this.Amount +
                "\n cancelled by: "+this.NameOfCanceller +" with ID: "+this.IDOfCanceller  );
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
