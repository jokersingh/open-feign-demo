package com.barun.openfeigndemo.service;

import com.barun.openfeigndemo.client.JSONPlaceHolderClient;
import com.barun.openfeigndemo.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JSONPlaceHolderService {
    @Autowired
    private JSONPlaceHolderClient jsonPlaceHolderClient;

    public List<Post> getPosts() {
        return jsonPlaceHolderClient.getPosts();
    }

    public Post getPostById(Long id) {
        return jsonPlaceHolderClient.getPostById(id);
    }
}
