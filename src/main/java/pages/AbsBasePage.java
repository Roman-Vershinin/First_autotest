package pages;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public abstract class AbsBasePage extends AbsCommon {
  private final String BASE_URL = "https://otus.ru/";
  public AbsBasePage(WebDriver driver) {
      super(driver);
  }

    public void open() {
        driver.get(BASE_URL);
    }
}
