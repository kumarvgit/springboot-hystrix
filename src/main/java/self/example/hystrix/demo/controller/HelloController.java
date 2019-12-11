package self.example.hystrix.demo.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @HystrixCommand(fallbackMethod = "fallbackGetHello")
    public String getHello() {
        if(RandomUtils.nextBoolean()) {
            throw new NullPointerException("Get hello, Send to fallback");
        }
        return "hello";
    }

    @GetMapping("/world")
    @HystrixCommand(fallbackMethod = "fallbackGetWorld")
    public String getWorld() {
        if(RandomUtils.nextBoolean()) {
            throw new NullPointerException("Get world, Send to fallback");
        }
        return "world";
    }

    public String fallbackGetHello() {
        return "Fallback hello";
    }

    public String fallbackGetWorld() {
        return "fallback world";
    }
}
