import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@QuarkusMain
@ApplicationPath("/")
public class MainApplication extends Application {

    public static void main(String[] args) {
        Quarkus.run(args);
    }
}