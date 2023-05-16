package com.demo.mypack.util;

import com.demo.mypack.Base;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Getter

public class Flights extends Base {

    @FindBy(xpath = "//*[@alt='Make My Trip']")
    private WebElement MakeMyTrip_lbl;
    //    WebDriver driver;
//    private static final Logger LOGGER = Logger.getLogger(Flights.class);
    @FindBy(id = "fromCity")
    private WebElement From_btn;
    //    @FindBy(xpath = "(//*[@id='fromCity']//parent::*/following::input)[1]")
//    private WebElement InputFromCity;
    @FindBy(id = "toCity")
    private WebElement To_btn;
    //    @FindBy(xpath = "(//*[@id='toCity']//parent::*/following::input)[1]")
//    private WebElement InputToCity;
//    @FindBy(xpath = "//*[contains(@class,'DayPicker-Day--today')]")
//    private WebElement DatePickerToday;
//
    @FindBy(xpath = "//a[text()='Search']")
    private WebElement Search_btn;
//    @FindBy(xpath = "//*[contains(@class,'suggestion--first')]")
//    private WebElement SelectFirst;

//    public Flights(WebDriver driver) {
//        LOGGER.info("In Flight class");
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//        LOGGER.info("Flight page class initialization");
//    }




//    public void setFrom(String from) throws InterruptedException {
//        LOGGER.info("setting from");
//        Thread.sleep(2000);
//        From_btn.click();
//        Thread.sleep(5000);
//        InputFromCity.sendKeys(from);
//        Thread.sleep(5000);
//        SelectFirst.click();
//    }
//
//    public void setTo(String to) throws InterruptedException {
//        Thread.sleep(2000);
//        To.click();
//        Thread.sleep(2000);
//        InputToCity.sendKeys(to);
//        Thread.sleep(2000);
//        SelectFirst.click();
//        Thread.sleep(3000);
//        DatePickerToday.click();
//    }
//
//    public void clickSearch() {
//        Search.click();
//    }


    @Override
    public ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(MakeMyTrip_lbl);
    }

}
