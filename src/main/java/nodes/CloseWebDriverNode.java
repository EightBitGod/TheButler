package nodes;

import entities.DriverWrapper;
import manager.DriverDataManager;

import java.util.Map;

import static utils.Constants.UPDATE_MAIN_WEB_DRIVER_DATA;
import static utils.Constants.WEB_DRIVER;

public class CloseWebDriverNode extends Node{

    @Override
    protected Map<String, Object> processTask(Map<String, Object> nodeInput) {
        DriverWrapper driverWrapper = (DriverWrapper) nodeInput.get(WEB_DRIVER);
        driverWrapper.getWebDriver().quit();
        System.out.println("checking update reqd");
        if(nodeInput.containsKey(UPDATE_MAIN_WEB_DRIVER_DATA) && (boolean) nodeInput.get(UPDATE_MAIN_WEB_DRIVER_DATA)){
            System.out.println("updating");
            DriverDataManager.updateDriverData(driverWrapper);
        }
        return nodeInput;
    }
}
