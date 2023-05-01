package Bussiness.HRBussiness;

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

    /**
     * get all the parameters to creat a cancellation from the DB
     *
     */
    public Cancellations(double Amount, String NameOfProduct,String IDOfCanceller, String NameOfCanceller,String year,String month,String day,String hour,String minute){
        this.Amount=Amount;
        this.NameOfProduct=NameOfProduct;
        this.IDOfCanceller=IDOfCanceller;
        this.NameOfCanceller=NameOfCanceller;
        this.Time=LocalDateTime.of(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day),Integer.parseInt(hour),Integer.parseInt(minute));
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

    public double getAmount() {return Amount;}

    public String getNameOfProduct() {
        return NameOfProduct;
    }

    public String getIDOfCanceller() {
        return IDOfCanceller;
    }

    public String getNameOfCanceller() {
        return NameOfCanceller;
    }

    public String getTime() {
        String hour=Integer.toString(Time.getHour());
        String minute=Integer.toString(Time.getMinute());
        if(hour.length()==1)
            hour="0"+hour;
        if(minute.length()==1)
            minute="0"+minute;
        return hour+":"+minute;
    }
    public String getDate() {
        String month=Integer.toString(Time.getMonthValue());
        String day=Integer.toString(Time.getDayOfMonth());
        if(month.length()==1)
            month="0"+month;
        if(day.length()==1)
            day="0"+day;
        return Time.getYear()+"-"+month+"-"+day;
    }
}
