package Domain;

import junit.framework.Test;

//this will be singleton
public class GeneralController {
    private static GeneralController instance;

    private GeneralController() {
    }

    public static GeneralController getInstance() {
        if (instance == null) {
            instance = new GeneralController();
        }
        return instance;
    }
}
