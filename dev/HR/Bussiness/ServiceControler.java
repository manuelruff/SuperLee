package HR.Bussiness;
import HR.DataAccess.SuperMapper;
import HR.DataAccess.WorkerMapper;
import java.util.*;
import java.util.ArrayList;

//fast singleton
public class ServiceControler {
    private static ServiceControler instance;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private Map<String, Driver> Drivers;
    private WorkerMapper workerMapper;
    private SuperMapper superMapper;

    private ServiceControler() {
        workerMapper=WorkerMapper.getInstance();
        superMapper=SuperMapper.getInstance();
        Superim = superMapper.getSuperMap();
        Workers= workerMapper.getWorkerMap();
        Drivers=workerMapper.getDriverMap();
    }
    public static ServiceControler getInstance() {
        if (instance == null) {
            instance = new ServiceControler();
        }
        return instance;
    }

    public List<String> getDriver(char licence, int training,int day){
        //create list to put in values of drivers
        List<String> driverInfo=new ArrayList<>();
        //take the parameters and turn them to enums
        Training ability=Training.values()[training];
        Days day1=Days.values()[day];
        //ill load all the drivers that have those training and licence to the DB so we can choose a relevant one
        workerMapper.ReadAllDriversByInfo(licence,ability);
        for(Driver driver:Drivers.values()){
            if(driver.getLicense()==licence&&driver.getAbility()==ability){
                if(driver.IsFree(day1)){
                    driverInfo.add(driver.getID());
                    driverInfo.add(driver.getName());
                    //todo add more stuff to the driver we give back
                }
            }
        }
        //todo if i find a driver i need to assign a store keeper for that day
        //todo or make sure to tell the HR manager to do so
        //if i return it empty, no driver was found
        return driverInfo;
    }

}
