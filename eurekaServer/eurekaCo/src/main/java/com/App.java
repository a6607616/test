package com;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class App 
{
    public static void main( String[] args )
    {
       SpringApplication.run(App.class,args);
    }

    /*@Bean
    @LoadBalanced
    RestTemplate loadBalancer() {
        return new RestTemplate();
    }*/

    @Bean
    IRule iRule() {
        return new RandomRule();
    }

    @Bean
    public Retryer feignRetryer() {
        Retryer.Default retryer = new Retryer.Default();
        return retryer;
    }
}
