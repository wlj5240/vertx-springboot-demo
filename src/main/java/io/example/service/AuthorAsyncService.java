package io.example.service;

import io.example.entity.Author;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import java.util.List;

/**
 * Created by XHD on 2018/3/15.
 */
@ProxyGen
public interface AuthorAsyncService {

    String ADDRESS = AuthorAsyncService.class.getName();

    void add(Author author, Handler<AsyncResult<Author>> resultHandler);

    void getAll(Handler<AsyncResult<List<Author>>> resultHandler);
}
