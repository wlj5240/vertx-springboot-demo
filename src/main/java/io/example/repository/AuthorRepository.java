package io.example.repository;

import io.example.entity.Author;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by XHD on 2018/3/15.
 */
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
