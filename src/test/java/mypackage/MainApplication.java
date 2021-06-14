package mypackage;

import java.io.IOException;

public class MainApplication {
    public static void main(String args[]) throws IOException {

        TestExecutor testExecutor = new TestExecutor();
        testExecutor.executeWeatherTests();
    }
}
