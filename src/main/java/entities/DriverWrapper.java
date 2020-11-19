package entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.WebDriver;

@Builder
@Setter
@Getter
public class DriverWrapper {
    WebDriver webDriver;
    String dir;
}
