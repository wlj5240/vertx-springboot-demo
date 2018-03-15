package io.example.handler;

import io.example.common.HttpUtils;
import io.example.entity.Book;
import io.example.service.BookAsyncService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by XHD on 2018/3/15.
 */
public class BookHandler {

    public static void addBook(RoutingContext routingContext, BookAsyncService bookAsyncService) {
        Book book = new Book(routingContext.getBodyAsJson());
        bookAsyncService.add(book, ar -> {
            if (ar.succeeded()) {
                routingContext.response().setStatusCode(HTTP_CREATED).end();
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }

    public static void getAllBooks(RoutingContext routingContext, BookAsyncService bookAsyncService) {
        bookAsyncService.getAll(ar -> {
            if (ar.succeeded()) {
                List<Book> result = ar.result();
                JsonArray jsonArray = new JsonArray(result);
                HttpUtils.fireJsonResponse(routingContext.response(), HTTP_OK, jsonArray.encodePrettily());
            } else {
                routingContext.fail(ar.cause());
            }
        });
    }
}
