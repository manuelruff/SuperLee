import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WantShift {
    private Days day;
    private CanWork morning_evening;
    private Map<Jobs,Integer> m_wants;
    private List<String>w_wants;

    public WantShift(Days day,CanWork m_e){
        m_wants=new HashMap<>();
        w_wants=new ArrayList<>();
        this.day=day;
        this.morning_evening=m_e;
    }
    public void add_m_wants(Jobs job, Integer num){
        this.m_wants.put(job,num);
    }
    public void add_w_wants(String ID){
        this.w_wants.add(ID);
    }
    public void remove_w_wants(String ID){
        this.w_wants.remove(ID);
    }

    public Map<Jobs, Integer> getM_wants() {
        return m_wants;
    }

    public Days getDay() {
        return day;
    }

    public CanWork getMorning_evening() {
        return morning_evening;
    }

    public List<String> getW_wants() {
        return w_wants;
    }

    public void ShowShift() {
        System.out.println("shift details: ");
        System.out.println(this.day + " " + this.morning_evening);
        for (Map.Entry<Jobs, Integer> entry : m_wants.entrySet()) {
            Jobs job = entry.getKey();
            Integer num_of_workers = entry.getValue();
            System.out.println(job + ": " + num_of_workers);
        }
    }

    public boolean JobInShift(Jobs j){
        for(Map.Entry<Jobs, Integer> job : m_wants.entrySet()){
            // im not sure if the tostring will work - check!!!!
            if(j.toString().equals(job.toString())){
                return true;
            }
        }
    return false;
    }
}
