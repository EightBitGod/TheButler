package nodes;

import entities.DriverWrapper;
import manager.DriverDataManager;

import java.util.Map;

import static utils.Constants.WEB_DRIVER;

public class GetNewWebDriverNode extends Node {
    @Override
    protected Map<String, Object> processTask(Map<String, Object> nodeInput) {
        DriverWrapper driverWrapper = DriverDataManager.getDriverWindow();
        nodeInput.put(WEB_DRIVER, driverWrapper);
        return nodeInput;
    }
}
