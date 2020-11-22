package com.barun.openfeigndemo.controller;

import com.barun.openfeigndemo.client.JSONPlaceHolderClient;
import com.barun.openfeigndemo.model.Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class DemoController {

    @Autowired
    private JSONPlaceHolderClient jsonPlaceHolderClient;
    
    @GetMapping("/test")
    public @ResponseBody
    List<Post> test(){
        return jsonPlaceHolderClient.getPosts();
    }
}
