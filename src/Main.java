import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;

/** A small dependency-free Java web server for the personal CV site. */
public final class Main {
    private static final int PORT = portFromEnvironment();

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/", exchange -> serve(exchange, "public/index.html", "text/html; charset=utf-8"));
        server.createContext("/style.css", exchange -> serve(exchange, "public/style.css", "text/css; charset=utf-8"));
        server.createContext("/app.js", exchange -> serve(exchange, "public/app.js", "application/javascript; charset=utf-8"));
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Website gestartet: http://localhost:" + PORT);
    }

    /** Uses the port supplied by a hosting provider, or 8080 on the local computer. */
    private static int portFromEnvironment() {
        String port = System.getenv("PORT");
        if (port == null || port.trim().isEmpty()) {
            return 8080;
        }
        try {
            return Integer.parseInt(port);
        } catch (NumberFormatException ignored) {
            return 8080;
        }
    }

    private static void serve(HttpExchange exchange, String resource, String contentType) throws IOException {
        if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
            exchange.sendResponseHeaders(405, -1);
            return;
        }
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream(resource)) {
            if (input == null) {
                exchange.sendResponseHeaders(404, -1);
                return;
            }
            byte[] data = input.readAllBytes();
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.getResponseHeaders().set("Cache-Control", "no-store");
            exchange.sendResponseHeaders(200, data.length);
            try (OutputStream output = exchange.getResponseBody()) {
                output.write(data);
            }
        }
    }
}
