import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"json:target/cucumber-result.json", "html:target/site/cucumber-pretty"}
)
public class TestRunner {
}
