import com.google.gson.*;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.*;
import java.nio.charset.StandardCharsets;

public class BitcoinAlertBot {

    static final String TOKEN = System.getenv("BOT_TOKEN");

    static final String TELEGRAM =
            "https://api.telegram.org/bot" + TOKEN;

    static final String ALERT_API =
            "http://localhost:8080/alerts";

    static final HttpClient client =
            HttpClient.newHttpClient();

    static long offset = 0;

    public static void main(String[] args) throws Exception {

        System.out.println("Telegram Bitcoin Bot started");

        while (true) {

            String response = request(
                    "GET",
                    TELEGRAM + "/getUpdates?timeout=30&offset=" + offset,
                    null
            );

            JsonArray updates =
                    JsonParser.parseString(response)
                            .getAsJsonObject()
                            .getAsJsonArray("result");

            for (JsonElement element : updates) {

                JsonObject update =
                        element.getAsJsonObject();

                offset =
                        update.get("update_id").getAsLong() + 1;

                if (!update.has("message"))
                    continue;

                JsonObject message =
                        update.getAsJsonObject("message");

                if (!message.has("text"))
                    continue;

                long chatId =
                        message.getAsJsonObject("chat")
                                .get("id")
                                .getAsLong();

                String text =
                        message.get("text")
                                .getAsString();

                handleCommand(chatId, text);
            }
        }
    }

    static void handleCommand(
            long chatId,
            String text) throws Exception {

        String[] parts = text.split("\\s+");

        switch (parts[0].toLowerCase()) {

            case "/start" -> {

                sendMessage(
                        chatId,
                        """
                        🤖 Bitcoin Alert Bot

                        Commands:

                        /get
                        View alerts

                        /post 85000
                        Create alert

                        /put 1 90000
                        Update alert

                        /delete 1
                        Delete alert

                        /price
                        Current Bitcoin price
                        """
                );
            }

            // GET
            case "/get" -> {

                String result =
                        request(
                                "GET",
                                ALERT_API,
                                null
                        );

                sendMessage(
                        chatId,
                        "📋 Alerts:\n" + result
                );
            }

            // POST
            case "/post" -> {

                if (parts.length < 2) {

                    sendMessage(
                            chatId,
                            "Use: /post 85000"
                    );

                    return;
                }

                String result =
                        request(
                                "POST",
                                ALERT_API,
                                parts[1]
                        );

                sendMessage(
                        chatId,
                        "✅ " + result
                );
            }

            // PUT
            case "/put" -> {

                if (parts.length < 3) {

                    sendMessage(
                            chatId,
                            "Use: /put 1 90000"
                    );

                    return;
                }

                String result =
                        request(
                                "PUT",
                                ALERT_API + "?id=" + parts[1],
                                parts[2]
                        );

                sendMessage(
                        chatId,
                        "✏️ " + result
                );
            }

            // DELETE
            case "/delete" -> {

                if (parts.length < 2) {

                    sendMessage(
                            chatId,
                            "Use: /delete 1"
                    );

                    return;
                }

                String result =
                        request(
                                "DELETE",
                                ALERT_API + "?id=" + parts[1],
                                null
                        );

                sendMessage(
                        chatId,
                        "🗑️ " + result
                );
            }

            case "/price" -> {

                String url =
                        "https://api.coingecko.com/api/v3/simple/price"
                        + "?ids=bitcoin&vs_currencies=usd";

                String result =
                        request(
                                "GET",
                                url,
                                null
                        );

                JsonObject json =
                        JsonParser.parseString(result)
                                .getAsJsonObject();

                double price =
                        json.getAsJsonObject("bitcoin")
                                .get("usd")
                                .getAsDouble();

                sendMessage(
                        chatId,
                        "💰 BTC Price: $" + price
                );
            }

            default ->

                    sendMessage(
                            chatId,
                            "Unknown command. Use /start"
                    );
        }
    }

    static String request(
            String method,
            String url,
            String body) throws Exception {

        HttpRequest.Builder builder =
                HttpRequest.newBuilder()
                        .uri(URI.create(url));

        switch (method) {

            case "POST" ->

                    builder.POST(
                            HttpRequest.BodyPublishers
                                    .ofString(body)
                    );

            case "PUT" ->

                    builder.PUT(
                            HttpRequest.BodyPublishers
                                    .ofString(body)
                    );

            case "DELETE" ->

                    builder.DELETE();

            default ->

                    builder.GET();
        }

        HttpResponse<String> response =
                client.send(
                        builder.build(),
                        HttpResponse.BodyHandlers.ofString()
                );

        return response.body();
    }

    static void sendMessage(
            long chatId,
            String message) throws Exception {

        String body =
                "chat_id="
                + chatId
                + "&text="
                + URLEncoder.encode(
                        message,
                        StandardCharsets.UTF_8
                );

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(
                                        TELEGRAM
                                        + "/sendMessage"
                                )
                        )
                        .header(
                                "Content-Type",
                                "application/x-www-form-urlencoded"
                        )
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(body)
                        )
                        .build();

        client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
    }
}