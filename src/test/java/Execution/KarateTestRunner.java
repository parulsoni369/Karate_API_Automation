package Execution;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.BeforeAll;

class KarateTestRunner {
    @BeforeAll
    public static void before() {System.setProperty("karate.env", "SIT");
    }

    @Karate.Test
    Karate testOpenWeather() {

        //Run all the feature files in sequence
        return Karate.run("classpath:OpenWeatherAPIFeatures").relativeTo(getClass());

    }
}
