package com.example.sweetgift.dao.candy;

import com.example.sweetgift.model.Candy;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CandyDaoMongoImpl implements CandyDao{
    private CandyMongoRepository repository;

    @Autowired
    CandyDaoMongoImpl(CandyMongoRepository repository){
        this.repository = repository;
    }

    @Override
    public Candy saveCandy(Candy candy) {
        return repository.save(candy);
    }

    @Override
    public List<Candy> saveAll(List<Candy> candyList) {
        return repository.saveAll(candyList);
    }

    @Override
    public Candy getCandyById(ObjectId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Candy> getAll() {
        return (List<Candy>) repository.findAll();
    }

    @Override
    public boolean deleteCandyById(ObjectId id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public Candy updateCandy(Candy candy) {
        return repository.save(candy);
    }
}
