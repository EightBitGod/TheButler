package manager;

import entities.DriverWrapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DriverDataManager {
    Integer count = 0;
    static List<DriverWrapper> driverWrappers = new ArrayList<>();

    public String dirname = "./driverdata/copies/data", maincopy = "./driverdata/main/data00", copiesdir = "./driverdata/copies/";
    public void initalise(){
        WebDriverManager.chromedriver().setup();
        deleteCopies();
    }


    public void copyFolder(File src, File dest) throws IOException{
        FileUtils.copyDirectory(src, dest);
    }

    @SneakyThrows
    private synchronized void deleteCopies() {
        FileUtils.deleteDirectory(new File(copiesdir));
    }

    @SneakyThrows
    public synchronized void createCopy(String dir) {
        copyFolder(new File(maincopy), new File(dir));
    }

    public synchronized DriverWrapper getDriverWindow(){
        count++;
        String dir =dirname + count;
        if(!new File(maincopy).exists()){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--user-data-dir="+maincopy);
            WebDriver webDriver = new ChromeDriver(options);
            webDriver.get("https://www.google.com");
            webDriver.quit();
        }
        createCopy(dir);
        ChromeOptions m_Options = new ChromeOptions();
        m_Options.addArguments("--user-data-dir="+dir);
        DriverWrapper driverWrapper = DriverWrapper.builder().dir(dir).webDriver(new ChromeDriver(m_Options)).build();
        driverWrappers.add(driverWrapper);
        return driverWrapper;
    }

    @SneakyThrows
    public synchronized void updateDriverData(DriverWrapper driverWrapper){
        FileUtils.deleteDirectory(new File(maincopy));
        new File(driverWrapper.getDir()).renameTo(new File(maincopy));
    }

    public synchronized void shutdown(){
        for(DriverWrapper driverWrapper : driverWrappers){
            driverWrapper.getWebDriver().close();
        }
    }

}
