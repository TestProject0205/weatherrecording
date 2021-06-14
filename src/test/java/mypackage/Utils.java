package mypackage;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Utils {
    public float convertCLToKel(String temp) {
        float kelBaseTemp = (float)273.15;
        return convertTempValue(temp) + kelBaseTemp;
    }

    public float convertTempValue(String str) {
        str = str.substring(0, str.length() - 1);
        return (Float.parseFloat(str));
    }

    public Location readLocationFile(String fileName) {
        try {
            //Parsing the contents of the JSON file
            Gson gson = new Gson();
            Reader fileReader = new FileReader(fileName);
            Location json = gson.fromJson(fileReader, Location.class);
            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}



