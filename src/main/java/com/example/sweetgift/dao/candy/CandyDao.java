package com.example.sweetgift.dao.candy;

import com.example.sweetgift.model.Candy;
import org.bson.types.ObjectId;

import java.util.List;

public interface CandyDao {
    Candy saveCandy(Candy candy);

    List<Candy> saveAll(List<Candy> candyList);

    Candy getCandyById(ObjectId id);

    List<Candy> getAll();

    boolean deleteCandyById(ObjectId id);

    Candy updateCandy(Candy candy);
}
