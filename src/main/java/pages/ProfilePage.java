package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends AbsBasePage{
    private Logger logger = LogManager.getLogger(ProfilePage.class);

    public ProfilePage(WebDriver driver) {
        super(driver);
    }
    public void clearFields() {
        String[] xPaths = {
                "//input[@name='fname']",
                "//input[@name='lname']",
                "//input[@name='fname_latin']",
                "//input[@name='lname_latin']",
                "//input[@name='blog_name']",
                "//input[@name='date_of_birth']"
        };
        for (String xpath : xPaths) {
            driver.findElement(By.xpath(xpath)).clear();
        }
        driver.findElement(By.xpath("//div[@data-num='0']/div/div/button[@type='button']")).click();
        driver.findElement(By.xpath("//div[@data-num='1']/div/div/button[@type='button']")).click();
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//button[@data-value='' and @data-empty='Не указано' and @title='Не выбрано']")).click();
        driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom-select']" +
                "[data-selected-option-class='lk-cv-block__select-option_selected']")).click();
        driver.findElement(By.xpath("//div[@class='lk-cv-block__select-options js-custom-select-options-container']" +
                "/div/button[@title='Не выбрано']")).click();
        logger.info("Поля очищены");
    }
    public void inputFioInProfile() {
        String[] fieldNames = {"fname", "lname", "fname_latin", "lname_latin", "blog_name", "date_of_birth"};
        String[] fieldValues = {"Роман", "Вершинин", "Roman", "Vershinin", "Роман", "22.03.1994"};

        for (int i = 0; i < fieldNames.length; i++) {
            driver.findElement(By.xpath("//input[@name='" + fieldNames[i] + "']")).sendKeys(fieldValues[i]);
        }
        logger.info("Введены ФИО и дата рождения");

    }

    public void inputCountryInProfile() {
        driver.findElement(By.cssSelector(".js-lk-cv-dependent-master.js-lk-cv-custom-select")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Россия')]")).click();
        logger.info("Страна выбрана");


    }
    public void inputCityInProfile() {
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//input[@data-title='Город']")));
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Москва')]")).click();
        logger.info("Город выбран");
    }
    public void inputEnglishInProfile() {
        driver.findElement(By.cssSelector("div[class='select lk-cv-block__input lk-cv-block__input_full js-lk-cv-custom" +
                "-select'][data-selected-option-class='lk-cv-block__select-option_selected']")).click();
        driver.findElement(By.cssSelector("button.lk-cv-block__select-option.js-custom-select-option[data-value=\"1\"]" +
                "[title=\"Начальный уровень (Beginner)\"]")).click();
        logger.info("Выбран уровень английского");
    }
    public void chooseReadyToRelocate() {
        driver.findElement(By.xpath("//span[@class=\"radio__label\" and text()=\"Нет\"]")).click();
        logger.info("Не готов к переезду");
    }
    public void chooseWorkingFormat() {
        if (!driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected()) {
            driver.findElement(By.cssSelector("input[title='Удаленно']")).click();
        }
        if (driver.findElement(By.cssSelector("input[title='Полный день']")).isSelected()) {
            driver.findElement(By.cssSelector("input[title='Полный день']")).click();
        }
        if (driver.findElement(By.cssSelector("input[title='Гибкий график']")).isSelected()) {
            driver.findElement(By.cssSelector("input[title='Гибкий график']")).click();
        }
        logger.info("Выбран формат работы");
    }
    public void chooseContacts() {
        driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add")).click();
        waitTools.waitForCondition(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"placeholder\" and" +
                " text()=\"Способ связи\"]")));
        driver.findElement(By.xpath("//span[@class=\"placeholder\" and text()=\"Способ связи\"]")).click();

        driver.findElement(By.xpath("//div[@class=\"lk-cv-block__select-options lk-cv-block__select-options_left" +
                " js-custom-select-options-container\"]/div/button[@data-value=\"skype\"]\n")).click();
        driver.findElement(By.xpath("//*[@id=\"id_contact-2-value\"]")).sendKeys("Treyser452");
        logger.info("Добавлен 1 контакт");
        driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add" +
                ".js-lk-cv-custom-select-add")).click();
        driver.findElement(By.xpath("//span[@class='placeholder']")).click();
        driver.findElement(By.xpath("//div[@class='lk-cv-block__select-options lk-cv-block__select-options_left" +
                " js-custom-select-options-container']//button[last()]")).click();
        driver.findElement(By.xpath("//*[@id=\"id_contact-3-value\"]")).sendKeys("Treyser452");

        logger.info("Добавлен 2 контакт");
    }
    public void chooseGender() {
        driver.findElement(By.id("id_gender")).click();
        driver.findElement(By.cssSelector("option[value='m']")).click();
        logger.info("Добавлен пол");
    }
    public void clickOnSave() {
        driver.findElement(By.xpath("//*[contains(text(), 'Сохранить и продолжить')]")).click();
        WebElement textSave = driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/span/span"));
        String actualText = textSave.getText().trim();
        Assertions.assertEquals("Данные успешно сохранены", actualText);
        logger.info("Сохранились");
    }
    public void assertions() {
        Assertions.assertEquals("Роман", driver.findElement(By.id("id_fname")).getAttribute("value"));
        Assertions.assertEquals("Вершинин", driver.findElement(By.id("id_lname")).getAttribute("value"));
        Assertions.assertEquals("Roman", driver.findElement(By.id("id_fname_latin")).getAttribute("value"));
        Assertions.assertEquals("Vershinin", driver.findElement(By.id("id_lname_latin")).getAttribute("value"));
        Assertions.assertEquals("Роман", driver.findElement(By.id("id_blog_name")).getAttribute("value"));
        Assertions.assertEquals("22.03.1994", driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)")).getAttribute("value"));
        Assertions.assertEquals("Россия", driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)")).getText());
        Assertions.assertEquals("Москва", driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)")).getText());
        Assertions.assertEquals("Начальный уровень (Beginner)", driver.findElement(By.cssSelector("div[class='select lk-cv-block__input" +
                " lk-cv-block__input_full js-lk-cv-custom-select'][data-selected-option-class='lk-cv-block__select-option_selected']")).getText());
        Assertions.assertEquals(true, driver.findElement(By.xpath("//input[@id='id_ready_to_relocate_0']")).isSelected());
        Assertions.assertEquals(true, driver.findElement(By.cssSelector("input[title='Удаленно']")).isSelected());
        Assertions.assertEquals("Treyser452", driver.findElement(By.id("id_contact-0-value")).getAttribute("value"));
        Assertions.assertEquals("Treyser452", driver.findElement(By.id("id_contact-1-value")).getAttribute("value"));
        Assertions.assertEquals("m", driver.findElement(By.id("id_gender")).getAttribute("value"));
    }
}