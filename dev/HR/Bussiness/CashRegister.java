package HR.Bussiness;

import java.util.*;

public class CashRegister {
    private List<Cancellations> Log;
    public CashRegister(){
        Log=new ArrayList<>();
    }
    public void AddCancalation(Cancellations cancel){
        this.Log.add(cancel);
    }
    public void PrintCancellation(String name,int year,int month,int day){
        //count how many logs we found with this information
        int count=0;
        //run on all the cancellations and check what fits
        for(int i=0;i<Log.size();i++){
            if (this.Log.get(i).getYear()==year && this.Log.get(i).getMonth()==month  && this.Log.get(i).getDay()==day ){
                this.Log.get(i).printMe();
                count++;
            }
        }
        //if we don't find anything we will tell the user
        if (count==0){
            System.out.println("sorry we didnt found anything with the date you asked for");
        }
    }

    public List<Cancellations> getAllCancellations(){
        return this.Log;
    }
}
