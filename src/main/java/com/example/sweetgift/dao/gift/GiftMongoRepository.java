package com.example.sweetgift.dao.gift;

import com.example.sweetgift.model.Gift;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftMongoRepository extends MongoRepository<Gift, ObjectId> {
    Gift findGiftsByReceiver(String buyer);
    Gift findGiftByAddress(String address);
}
