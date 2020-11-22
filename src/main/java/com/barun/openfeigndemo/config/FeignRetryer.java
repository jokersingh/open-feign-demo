package com.barun.openfeigndemo.config;

import feign.RetryableException;
import feign.Retryer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class FeignRetryer implements Retryer {

    private final int maxAttempts;
    private final long backoff;
    int attempt;

    public FeignRetryer(){
        this(10000,5);
    }

    public FeignRetryer(long backoff,int maxAttempts){
        this.maxAttempts = maxAttempts;
        this.backoff = backoff;
        this.attempt = 1;
    }

//    @Bean
//    public Retryer getRetryer(){
//        return new FeignRetryer();
//    }

    @Override
    public void continueOrPropagate(RetryableException e) {
        if(attempt++ >= maxAttempts){
            throw e;
        }
        try {
            TimeUnit.MICROSECONDS.sleep(backoff);
        }catch(InterruptedException ex){

        }
        log.info("Retrying: {}, attempt: {}", e.request().url(),attempt);
    }

    @Override
    public Retryer clone() {
        return new FeignRetryer(backoff,maxAttempts);
    }
}
