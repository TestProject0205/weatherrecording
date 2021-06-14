package mypackage;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WeatherAPI{
    public float getWeatherFromApi(String city) {
        RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=6806ac7220de95cf34bcb704f5a42865";
        Response response = null;
        try {
            response = RestAssured.given()
                    .when()
                    .get(RestAssured.baseURI);
            //Creation of JsonPath object
            if(response.getStatusCode()==200 ) {
                JsonPath jpath = response.jsonPath();
                float temp = Float.parseFloat(jpath.getString("main.temp"));
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}


