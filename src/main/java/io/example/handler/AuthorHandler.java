package io.example.handler;

import io.example.common.HttpUtils;
import io.example.entity.Author;
import io.example.service.AuthorAsyncService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * Created by XHD on 2018/3/15.
 */
public class AuthorHandler {

    public static void addAuthor(RoutingContext rc, AuthorAsyncService authorAsyncService) {
        Author author = new Author(rc.getBodyAsJson());
        authorAsyncService.add(author, ar -> {
            if (ar.succeeded()) {
                rc.response().setStatusCode(HTTP_CREATED).end();
            } else {
                rc.fail(ar.cause());
            }
        });
    }

    public static void getAllAuthors(RoutingContext rc, AuthorAsyncService authorAsyncService) {
        authorAsyncService.getAll(ar -> {
            if (ar.succeeded()) {
                List<Author> result = ar.result();
                JsonArray jsonArray = new JsonArray(result);
                HttpUtils.fireJsonResponse(rc.response(), HTTP_OK, jsonArray.encodePrettily());
            } else {
                rc.fail(ar.cause());
            }
        });
    }
}
