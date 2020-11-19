package nodes;

import entities.DriverWrapper;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;

import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static utils.Constants.WEB_DRIVER;

public class LinkedinSendConnectionRequestsNode extends Node{
    @SneakyThrows
    @Override
    protected Map<String, Object> processTask(Map<String, Object> nodeInput) {
        DriverWrapper driverWrapper = (DriverWrapper) nodeInput.get(WEB_DRIVER);
        for(int i = 0; i<5;i++){
            driverWrapper.getWebDriver().get("https://www.linkedin.com/mynetwork/");
            sleep(3000);
            JavascriptExecutor js = (JavascriptExecutor) driverWrapper.getWebDriver();
            js.executeScript("document.body.style.zoom = '0.25'");
            sleep(3000);
            for(WebElement element : driverWrapper.getWebDriver().findElements(By.tagName("Button")).stream()
                    .filter(webElement -> webElement.getAttribute("aria-label")!=null
                            && webElement.getAttribute("aria-label").contains(" to connect")
                            && webElement.getText().equals("Connect")).collect(Collectors.toList())){
                DriverUtils.click(element, driverWrapper);
                sleep(50);
            }
        }
        return nodeInput;
    }
}
