package io.example.service.impl;

import io.example.entity.Author;
import io.example.service.AuthorAsyncService;
import io.example.service.AuthorService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by XHD on 2018/3/15.
 */
@Component
public class AuthorAsyncServiceImpl implements AuthorAsyncService {

    @Autowired
    private AuthorService authorService;

    @Override
    public void add(Author author, Handler<AsyncResult<Author>> resultHandler) {
        Author save = authorService.save(author);
        Future.succeededFuture(save).setHandler(resultHandler);
    }

    @Override
    public void getAll(Handler<AsyncResult<List<Author>>> resultHandler) {
        Future.succeededFuture(authorService.getAll()).setHandler(resultHandler);
    }
}
