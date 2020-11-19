package nodes;

import entities.DriverWrapper;
import lombok.SneakyThrows;
import manager.DriverDataManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Map;
import java.util.Scanner;

import static java.lang.Thread.sleep;
import static utils.Constants.OUTPUT;
import static utils.Constants.UPDATE_MAIN_WEB_DRIVER_DATA;
import static utils.Constants.WEB_DRIVER;

public class ValidateLinkedInLoginNode extends Node{
    @SneakyThrows
    @Override
    protected Map<String, Object> processTask(Map<String, Object> nodeInput) {
        DriverWrapper driverWrapper = (DriverWrapper) nodeInput.get(WEB_DRIVER);
        try{
            driverWrapper.getWebDriver().get("https://www.linkedin.com/login");
            sleep(4000);
            if(!driverWrapper.getWebDriver().getCurrentUrl().contains("feed")){
                driverWrapper.getWebDriver().findElement(By.id("username")).sendKeys("kshitizmiglani@outlook.com");
                System.out.println("enter linkedin password");
                driverWrapper.getWebDriver().findElement(By.id("password")).sendKeys(new Scanner(System.in).nextLine()+ Keys.ENTER);
                nodeInput.put(UPDATE_MAIN_WEB_DRIVER_DATA, true);
            }
            nodeInput.put(OUTPUT,"SUCCESS");
        }
        catch (Exception e){
            System.out.println("exception in  validatinglinkedinlogin");
            e.printStackTrace();
            nodeInput.put(OUTPUT,e);
            new Scanner(System.in).next();
            driverWrapper.getWebDriver().close();
        }
        return nodeInput;

    }
}
