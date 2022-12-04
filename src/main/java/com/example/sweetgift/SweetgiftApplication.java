package com.example.sweetgift;

import com.example.sweetgift.dao.candy.CandyMongoRepository;
import com.example.sweetgift.dao.gift.GiftMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(
        basePackageClasses = {CandyMongoRepository.class, GiftMongoRepository.class}
)
public class SweetgiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetgiftApplication.class, args);
    }

}
