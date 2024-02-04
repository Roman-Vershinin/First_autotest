import components.Header;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AuthorizationPage;
import pages.MainPage;
import pages.ProfilePage;
import tools.WaitTools;

public class AddInfoToProfile_Test {
    protected WebDriver driver;
    protected WaitTools waitTools;
    private final Logger logger = LogManager.getLogger(AddInfoToProfile_Test.class);


    @BeforeEach
    public void driverSetup() {
    driver = new DriverFactory(driver).create();
    waitTools = new WaitTools(driver);
    }


    @AfterEach
    public void driverStop() {
        if (driver!= null) {
            driver.quit();
        }
    }
    @Test
    public void addInfoToProfile() {
        new MainPage(driver).open();
        new Header(driver).clickButtonEnter();
        new AuthorizationPage(driver).authorizationUser();
        new Header(driver).popUp();
        new ProfilePage(driver).clearFields();
        new ProfilePage(driver).inputFioInProfile();
        new ProfilePage(driver).inputCountryInProfile();
        new ProfilePage(driver).inputCityInProfile();
        new ProfilePage(driver).inputEnglishInProfile();
        new ProfilePage(driver).chooseReadyToRelocate();
        new ProfilePage(driver).chooseWorkingFormat();
        new ProfilePage(driver).chooseContacts();
        new ProfilePage(driver).chooseGender();
        new ProfilePage(driver).clickOnSave();

    }
    @Test
    public void checkProfile() {
        new MainPage(driver).open();
        new Header(driver).clickButtonEnter();
        new AuthorizationPage(driver).authorizationUser();
        new Header(driver).popUp();
        new ProfilePage(driver).assertions();
    }
}

