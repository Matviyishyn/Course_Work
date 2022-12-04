package com.example.sweetgift.dao.candy;

import com.example.sweetgift.model.Candy;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandyMongoRepository extends MongoRepository<Candy, ObjectId> {
    Candy findCandyByName(String name);

    Candy findCandyByBrand(String brand);
}
