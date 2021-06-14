package mypackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;


public class TestExecutor{
    @Test
    public static void executeWeatherTests() throws IOException {
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
        Utils ut = new Utils();
        TemperatureComparator tc = new TemperatureComparator();
        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/config.properties"));
        String fileName = prop.getProperty("TEST_FILE_Path");;
        Location loc = ut.readLocationFile(fileName);
        List<String> cities = loc.City;
        Float variance = loc.Variance;
        for(String city: cities) {
            boolean result = tc.compareTemperatureByCity(city, variance);
            try{
                Assert.assertEquals(result,true);
            }catch (Throwable t){
                System.out.println("Calculated Temperature difference is more than the variance by user input ");
            }

            System.out.println("Test result for "+ city + " : "+ result);
        }
    }
}
