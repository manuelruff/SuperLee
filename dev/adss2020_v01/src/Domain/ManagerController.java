package Domain;

import DataAccess.ManagerPasswordMapper;

//this will be singlton
public class ManagerController {
    private static ManagerController instance;
    private String ManagerPassword;
    private ManagerController(){
        ManagerPasswordMapper.getInstance();
        ManagerPassword= ManagerPasswordMapper.getManagerPassword();
    }
    public static ManagerController getInstance(){
        if(instance == null){
            instance = new ManagerController();
        }
        return instance;
    }
   public boolean checkPassword(String password){
        return password.equals(ManagerPassword);
   }
    public void setManagerPassword(String password){
        ManagerPassword=password;
        ManagerPasswordMapper.setManagerPassword(password);
    }
}
