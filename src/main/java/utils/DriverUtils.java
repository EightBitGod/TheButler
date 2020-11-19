package utils;

import entities.DriverWrapper;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

@UtilityClass
public class DriverUtils {
    public void click(WebElement webElement, DriverWrapper driverWrapper){
        try{
            webElement.click();
        }
        catch (Exception e){
            JavascriptExecutor js = (JavascriptExecutor) driverWrapper.getWebDriver();
            try{
                js.executeScript("arguments[0].click();", webElement);
            }
            catch (Exception e1){
                try{
                    new Actions(driverWrapper.getWebDriver()).moveToElement(webElement ).click(webElement ).perform();
                }
                catch (Exception e2){
                    System.out.println("could not click");
                }
            }
        }
    }
}
