package com.uktamjon.sodikov.cucumber;

import com.uktamjon.sodikov.SecondaryMicroserviceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.ComponentScan;

@CucumberContextConfiguration
@SpringBootTest(classes = {SecondaryMicroserviceApplication.class, CucumberSpringConfiguration.Configuration.class})
public class CucumberSpringConfiguration {

    @TestConfiguration
    @ComponentScan("com.uktamjon.sodikov")
    public static  class Configuration {

    }

}