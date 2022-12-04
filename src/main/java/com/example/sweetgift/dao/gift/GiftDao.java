package com.example.sweetgift.dao.gift;

import com.example.sweetgift.model.Gift;
import org.bson.types.ObjectId;

import java.util.List;


public interface GiftDao {
    Gift saveGift(Gift gift);

    List<Gift> saveAll(List<Gift> giftList);

    Gift getGiftById(ObjectId id);

    List<Gift> getAll();

    boolean deleteGiftById(ObjectId id);

    Gift updateGift(Gift gift);
}
