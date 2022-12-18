package com.first.firstWeb.repo;

import com.first.firstWeb.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface Post_Repository extends CrudRepository<Post,Long> {
}
