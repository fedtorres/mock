import com.google.gson.Gson;
import org.mockserver.client.MockServerClient;
import org.mockserver.model.Delay;
import org.mockserver.model.Header;

import java.util.concurrent.TimeUnit;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class Servicio {

    static MockServerClient mockServer = startClientAndServer(8081);
    static Gson gson;

    public static void consulta(String metodo, String ruta, int codigo,
                                String content, String body, int delay) {
        mockServer.when(
                request().withMethod(metodo)
                .withPath(ruta)
        ).respond(
                response().withStatusCode(codigo)
                .withHeader(new Header("Content-Type", content))
                .withBody(body)
                .withDelay(new Delay(TimeUnit.MILLISECONDS, delay))
        );
    }

    public static void main(String[] args) {
        gson = new Gson();
        Item item = new Item(12345);
        User user = new User(1234567, "VE", "MLV");
        Category c1 = new Category("MLV1747", "Accesorios para Vehículos");
        Category c2 = new Category("MLV1071", "Animales y Mascotas");
        Category[] categories = new Category[] {c1, c2};
        Country country = new Country("VE", "VES");
        Currency currency = new Currency("VES", "Bolivar Soberano", "Bs.", 2);

        consulta("GET", "/items/.*", 200,
                "application/json; charset=utf-8",
                gson.toJson(item), 1000);

        consulta("GET", "/users/.*", 200,
                "application/json; charset=utf-8",
                gson.toJson(user), 1000);

        consulta("GET", "/sites/.*/categories", 200,
                "application/json; charset=utf-8",
                gson.toJson(categories), 1000);

        consulta("GET", "/countries/.*", 200,
                "application/json; charset=utf-8",
                gson.toJson(country), 1000);

        consulta("GET", "/currencies/.*", 200,
                "application/json; charset=utf-8",
                gson.toJson(currency), 1000);
    }
}
