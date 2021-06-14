package mypackage;


public class TemperatureComparator {
    public boolean compareTemperatureByCity(String city, float variance){
        SeleniumWeather sw = new SeleniumWeather();
        WeatherAPI wa = new WeatherAPI();
        // Fetching Actual Temperature of City from API
        float actualTemp = wa.getWeatherFromApi(city);
        // Fetching Site Temperature from Selenium
        float siteTemp = sw.getSiteTemperature(city);
        System.out.println(String.format("City:%s ActualTemperature:%f SiteTemperature:%f",city, actualTemp, siteTemp));

        if(actualTemp > 0 && siteTemp > 0) {
            if (Math.abs(siteTemp - actualTemp) > variance) {
                return false;
            }
            return true;
        }
        return false;
    }
}
