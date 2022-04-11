package edu.ucsb.cs156.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

// @ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "edu\\.ucsb\\.cs156\\.example\\.testconfig\\..*"))


@SpringBootApplication
public class ExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }

}
