package com.jacklog.jacklog.repository;

import com.jacklog.jacklog.domain.Post;
import com.jacklog.jacklog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getList(PostSearch postSearch);
}
