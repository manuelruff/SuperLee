package HR.Domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Weekly {
    //start of the week, sunday of that week
    private LocalDate StartDate;
    private List<Shift> ShiftList;

    //constractor
    public Weekly(){
        ShiftList=new ArrayList<>();
        StartDate=LocalDate.now();
        //we want to start at next sunday so we add one until we are there
        DayOfWeek day=StartDate.getDayOfWeek();
        while(day!=DayOfWeek.SUNDAY){
            StartDate=StartDate.plusDays(1);
            day=StartDate.getDayOfWeek();
        }
    }
    //builder for the database
    public Weekly(String date){
        ShiftList=new ArrayList<>();
        StartDate=LocalDate.parse(date);
    }

    //add a shift to the list
    public void AddShift(Shift sh){

        this.ShiftList.add(sh);
    }
    //print the weekly
    public void PrintMe(){
        for (int i=0;i<this.ShiftList.size();i++){
            this.ShiftList.get(i).PrintMe();
            System.out.println();
        }
    }
    public Shift GetShift(int day){
        return this.ShiftList.get(day);
    }
    public LocalDate getStartDate() {
        return StartDate;
    }

    public List<Shift> getShiftList() {
        return ShiftList;
    }
}
