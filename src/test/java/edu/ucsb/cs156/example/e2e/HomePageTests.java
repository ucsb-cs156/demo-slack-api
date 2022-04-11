package edu.ucsb.cs156.example.e2e;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testcontainers.Testcontainers.exposeHostPorts;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import edu.ucsb.cs156.example.services.SystemInfoServiceImpl;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.context.annotation.FilterType;

@Testcontainers
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomePageTests {

	@LocalServerPort
	private Integer port;

	private static final Path screenshotPath = Paths.get("target", "playwright");

	@Container
	public static GenericContainer<?> chromeContainer = new GenericContainer<>(
			DockerImageName.parse("browserless/chrome:latest"))
			.withAccessToHost(true)
			.withExposedPorts(3000)
			.waitingFor(Wait.forHttp("/"));

	@Test
	public void shouldDisplayBook() {
		exposeHostPorts(port);
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().connect(
					"ws://" + chromeContainer.getHost() + ":" + chromeContainer.getFirstMappedPort() + "/playwright");
			String baseUrl = String.format("http://host.testcontainers.internal:%d", port);
			try (Page page = browser.newPage()) {

				page.navigate(baseUrl);
				page.locator("[data-testid=\"AppNavbar\"] >> text=Example").click();

				// Click [data-testid="AppNavbar"] >> text=Example
				page.locator("[data-testid=\"AppNavbar\"] >> text=Example").click();
				assertThat(page).hasURL(baseUrl);

				// Click text=Log In
				page.locator("text=Log In").click();
				assertThat(page).hasURL(baseUrl + "/login");

				page.locator("input[name=\"username\"]").click();
				page.locator("input[name=\"username\"]").fill("admin");
				page.locator("input[name=\"username\"]").press("Tab");
				page.locator("input[name=\"password\"]").fill("cs156");

				page.waitForNavigation(() -> {
					page.locator("text=Sign In").click();
				});
				assertThat(page).hasURL(baseUrl);

				page.waitForNavigation(() -> {
					page.locator("text=Log Out").click();
				});
				assertThat(page).hasURL(baseUrl);

			}
		}
	}
}
