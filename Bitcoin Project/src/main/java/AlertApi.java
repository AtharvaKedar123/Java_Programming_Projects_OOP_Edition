import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AlertApi {

    static Map<Integer, Double> alerts = new HashMap<>();
    static int nextId = 1;

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/alerts", AlertApi::handleRequest);

        server.start();

        System.out.println("REST API running at http://localhost:8080/alerts");
    }

    static void handleRequest(HttpExchange exchange)
            throws IOException {

        String method = exchange.getRequestMethod();

        switch (method) {

            case "GET" -> getAlerts(exchange);

            case "POST" -> createAlert(exchange);

            case "PUT" -> updateAlert(exchange);

            case "DELETE" -> deleteAlert(exchange);

            default -> send(exchange, 405, "Method not allowed");
        }
    }

    static void getAlerts(HttpExchange exchange)
            throws IOException {

        send(exchange, 200, alerts.toString());
    }

    static void createAlert(HttpExchange exchange)
            throws IOException {

        String body = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        double price = Double.parseDouble(body);

        alerts.put(nextId, price);

        send(
                exchange,
                201,
                "Alert created. ID = " + nextId
        );

        nextId++;
    }

    static void updateAlert(HttpExchange exchange)
            throws IOException {

        String query =
                exchange.getRequestURI().getQuery();

        int id =
                Integer.parseInt(
                        query.split("=")[1]
                );

        String body = new String(
                exchange.getRequestBody().readAllBytes(),
                StandardCharsets.UTF_8
        );

        double price =
                Double.parseDouble(body);

        if (!alerts.containsKey(id)) {

            send(exchange, 404, "Alert not found");
            return;
        }

        alerts.put(id, price);

        send(exchange, 200, "Alert updated");
    }

    static void deleteAlert(HttpExchange exchange)
            throws IOException {

        String query =
                exchange.getRequestURI().getQuery();

        int id =
                Integer.parseInt(
                        query.split("=")[1]
                );

        if (alerts.remove(id) != null) {

            send(exchange, 200, "Alert deleted");

        } else {

            send(exchange, 404, "Alert not found");
        }
    }

    static void send(
            HttpExchange exchange,
            int status,
            String message) throws IOException {

        byte[] response =
                message.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(
                status,
                response.length
        );

        exchange.getResponseBody().write(response);
        exchange.close();
    }
}