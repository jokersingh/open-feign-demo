package com.barun.openfeigndemo.config;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.util.Calendar;

@Configuration
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new ErrorDecoder.Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if(HttpStatus.NOT_FOUND.value() == response.status()){
            log.info("Error while executing :{}, Error code :{}", methodKey,HttpStatus.NOT_FOUND);
            return new RetryableException(response.status(), methodKey, null, Calendar.getInstance().getTime(),response.request());
        }
        return defaultErrorDecoder.decode(methodKey,response);
    }

//    @Bean
//    public ErrorDecoder  getErrorDecoder(){
//        return new FeignErrorDecoder();
//    }
}
