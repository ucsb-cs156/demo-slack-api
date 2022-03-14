package edu.ucsb.cs156.example.cypress;


import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.validation.constraints.NotNull;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.containers.output.ToStringConsumer;
import org.testcontainers.containers.output.WaitingConsumer;

import edu.ucsb.cs156.example.services.CurrentUserService;
import edu.ucsb.cs156.example.services.SystemInfoServiceImpl;
import edu.ucsb.cs156.example.testconfig.MockCurrentUserServiceImpl;
import edu.ucsb.cs156.example.testconfig.TestConfig;
import lombok.extern.slf4j.Slf4j;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("cypress")
@Import(TestConfig.class)
public class CypressIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    CurrentUserService currentUserService;

    @Test
    void runCypressTests() throws InterruptedException {

        // Ensures that the container will be able to access the Spring Boot application
        // that
        // is started via @SpringBootTest
        Testcontainers.exposeHostPorts(port);

        try (CypressContainer container = createCypressContainer()) {

            container.start();

            WaitingConsumer waitingConsumer = new WaitingConsumer();
            ToStringConsumer toStringConsumer = new ToStringConsumer();
            
            Consumer<OutputFrame> composedConsumer = toStringConsumer.andThen(waitingConsumer);
            container.followOutput(composedConsumer);
            
            waitingConsumer.waitUntil(frame -> 
                frame.getUtf8String().contains("STARTED"), 30, TimeUnit.SECONDS);
            
            String utf8String = toStringConsumer.toUtf8String();

            log.info("======== OUTPUT FROM cypress ===========");
            log.info(utf8String);
            log.info("======== END OUTPUT FROM cypress ========");


        } catch (Exception e) {
            log.error("Exception starting container:",e);
        }
    }

    @NotNull
    private CypressContainer createCypressContainer() {
        CypressContainer result = new CypressContainer();
        result.withClasspathResourceMapping("e2e", "/e2e", BindMode.READ_WRITE);
        result.setWorkingDirectory("/e2e");
        result.addEnv("CYPRESS_baseUrl", "http://host.testcontainers.internal:" + port);
        return result;
    }

}
