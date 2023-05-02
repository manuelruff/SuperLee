package HR.Bussiness;

import HR.DataAccess.SuperMapper;
import HR.DataAccess.WorkerMapper;

import java.util.Map;

//fast singleton
public class ServiceControler {
    private static ServiceControler instance;
    private Map<String, Super> Superim;
    //all the Workers in the company
    private Map<String, Worker> Workers;
    private Map<String, Driver> Drivers;
    private ServiceControler() {
        Superim = SuperMapper.getSuperMap();
        Workers= WorkerMapper.getWorkerMap();
        Drivers=WorkerMapper.getDriverMap();
    }
    public static ServiceControler getInstance() {
        if (instance == null) {
            instance = new ServiceControler();
        }
        return instance;
    }

}
