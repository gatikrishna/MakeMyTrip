package com.demo.mypack.stepDefinition;

public class ExtentReportManager {
//    public ExtentSparkReporter spark;
//    public ExtentReports extent;
//    public ExtentTest logger ;
////            extent.createTest("To verify Google Title");
//
//    WebDriver driver = Base.getDriver();
//
//    @Before
//    public void startTest() {
//        extent = new ExtentReports();
//
//        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/STMExtentReport.html");
//        extent.attachReporter(spark);
//        extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
//        extent.setSystemInfo("Environment", "Production");
//        extent.setSystemInfo("User Name", "Rajkumar SM");
//        spark.config().setDocumentTitle("Title of the Report Comes here ");
//        // Name of the report
//        spark.config().setReportName("Name of the Report Comes here ");
//        // Dark Theme
//        spark.config().setTheme(Theme.STANDARD);
//
//
//    }
//
//    @AfterStep
//    public void setScreenshot(Scenario scenario) throws Exception {
//        String screenshotPath = getScreenShot(driver, scenario.getName());
//        if (scenario.isFailed()) {
////            scenario.attach(screenshotPath, "image/png", "image");
//            logger.log(Status.FAIL, MarkupHelper.createLabel(scenario.getName() + " - Test Case Failed", ExtentColor.RED));
//            logger.log(Status.FAIL, MarkupHelper.createLabel(" - Test Case Failed", ExtentColor.RED));
////To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
////We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
////String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
//
////To add it in the extent report
//            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
//        } else {
//            logger.log(Status.PASS, MarkupHelper.createLabel(scenario.getName() + " Test Case PASSED", ExtentColor.GREEN));
//            logger.pass("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
//        }
//
//    }
//
//
//    public String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        TakesScreenshot ts = (TakesScreenshot) driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//// after execution, you could see a folder "FailedTestsScreenshots" under src folder
//        String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
//        File finalDestination = new File(destination);
//        FileUtils.copyFile(source, finalDestination);
//        return destination;
//    }
}
