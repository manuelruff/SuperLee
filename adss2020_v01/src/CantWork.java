public class CantWork {
    private double start;
    private double end;

    public CantWork(double s,double e){
        this.start=s;
        this.end=e;
    }

    public double getStart(){
        return start;
    }
    public double getEnd(){
        return end;
    }
    public void printMe(){
        System.out.println("start: "+start+ " end: "+end);
    }
}
