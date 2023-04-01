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
    }

    //add a shift to the list
    public void AddShift(Shift sh){
        this.ShiftList.add(sh);
    }

    //print the weekly
    public void PrintMe(){
        for (int i=0;i<this.ShiftList.size();i++){
            this.ShiftList.get(i).PrintMe();
        }
    }

    public Shift GetShift(int day){
        return this.ShiftList.get(day);
    }

    }
