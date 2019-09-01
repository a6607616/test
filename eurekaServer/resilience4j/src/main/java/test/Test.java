package test;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

import java.time.Duration;

public class Test {

    public static void main(String[] args) {

       /* CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker
                .decorateCheckedSupplier(circuitBreaker, () -> "This can be any method which returns: 'Hello");
        Try<String> result = Try.of(decoratedSupplier)
                .map(value -> value + " world'");
        System.out.println(result.isSuccess());
        System.out.println(result.get());*/


        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        Try<String> result = Try.of(CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> "Hello"))
                .map(value -> value + " world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());

    }
}
