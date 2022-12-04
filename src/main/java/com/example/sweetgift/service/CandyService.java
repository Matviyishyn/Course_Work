package com.example.sweetgift.service;

import com.example.sweetgift.dao.candy.CandyDao;
import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.exceptions.InvalidPropertiesException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.sweetgift.utils.candy.CandyValidator.isCandyValid;

@Service
public class CandyService {
    private final CandyDao dao;

    @Autowired
    public CandyService(CandyDao dao){
        this.dao = dao;
    }

    public Candy saveCandy(Candy candy){
        if(!isCandyValid(candy)){
            throw new InvalidPropertiesException(candy + " is invalid");
        }

        return dao.saveCandy(candy);
    }

    public Candy getCandyById(ObjectId candyId){
        if(candyId == null){
            throw new InvalidPropertiesException("Candy id is invalid");
        }

        return dao.getCandyById(candyId);
    }

    public List<Candy> getCandiesById(List<ObjectId> candiesId){
        List<Candy> candyList = new ArrayList<>();

        for(var id: candiesId){
            Candy candy = getCandyById(id);

            if(candy != null) {
                candyList.add(candy);
            }
        }

        return candyList;
    }

    public List<Candy> getAllCandies(){
        return dao.getAll();
    }

    public boolean deleteCandyById(ObjectId candyId){
        if(candyId == null) {
            throw new InvalidPropertiesException("Candy id is invalid");
        }

        return dao.deleteCandyById(candyId);
    }

    public Candy updateCandy(ObjectId candyId, Candy candy){
        if(candyId == null){
            throw new InvalidPropertiesException("Candy id is invalid");
        }

        if(!isCandyValid(candy)){
            throw new InvalidPropertiesException();
        }

        return dao.updateCandy(candy);
    }
}
