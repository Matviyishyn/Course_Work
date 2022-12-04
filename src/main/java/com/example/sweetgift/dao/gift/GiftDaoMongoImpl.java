package com.example.sweetgift.dao.gift;

import com.example.sweetgift.model.Gift;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GiftDaoMongoImpl implements GiftDao{
    private final GiftMongoRepository repository;

    @Autowired
    GiftDaoMongoImpl(GiftMongoRepository repository){
        this.repository = repository;
    }

    @Override
    public Gift saveGift(Gift gift) {
        return repository.save(gift);
    }

    @Override
    public List<Gift> saveAll(List<Gift> giftList) {
        return repository.saveAll(giftList);
    }

    @Override
    public Gift getGiftById(ObjectId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Gift> getAll() {
        return (List<Gift>) repository.findAll();
    }

    @Override
    public boolean deleteGiftById(ObjectId id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Gift updateGift(Gift gift) {
        return repository.save(gift);
    }
}
