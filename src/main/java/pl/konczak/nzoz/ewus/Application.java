package pl.konczak.nzoz.ewus;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        builder.run(args);
    }

}
