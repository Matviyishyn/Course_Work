package com.example.sweetgift.service;

import com.example.sweetgift.dao.gift.GiftDao;
import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.Gift;
import com.example.sweetgift.model.exceptions.InvalidPropertiesException;
import com.example.sweetgift.model.exceptions.NotFoundException;
import com.example.sweetgift.utils.candy.CandyValidator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.sweetgift.utils.gift.GiftValidator.isGiftValid;

@Service
public class GiftService {
    private GiftDao giftDao;

    @Autowired
    GiftService(GiftDao dao){
        this.giftDao = dao;
    }

    public Gift saveGift(Gift gift){
        if(!isGiftValid(gift)){
            throw new InvalidPropertiesException(gift + " is invalid");
        }

        return giftDao.saveGift(gift);
    }

    public Gift getGiftById(ObjectId giftId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        return giftDao.getGiftById(giftId);
    }

    public List<Gift> getAllGifts(){
        return giftDao.getAll();
    }

    public boolean deleteGiftById(ObjectId giftId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        return giftDao.deleteGiftById(giftId);
    }

    public Gift updateGift(ObjectId giftId, Gift gift){
        if(!isGiftValid(gift)){
            throw new InvalidPropertiesException();
        }

        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        gift.setId(giftId);
        return giftDao.updateGift(gift);
    }

    public Gift addCandyToGift(ObjectId giftId, Candy candy) {
        if(!CandyValidator.isCandyValid(candy)){
            throw new InvalidPropertiesException(candy + " is invalid");
        }

        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        List<Candy> giftCandyList = gift.getCandyList();
        giftCandyList.add(candy);

        return giftDao.updateGift(gift);
    }

    public Gift addCandiesToGift(ObjectId giftId, List<Candy> candyList) {
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        List<Candy> giftCandyList = gift.getCandyList();
        giftCandyList.addAll(candyList);

        return giftDao.updateGift(gift);
    }

    public Gift removeCandyFromGift(ObjectId giftId, ObjectId candyId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        if(candyId == null){
            throw new InvalidPropertiesException("Candy id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        List<Candy> giftCandyList = gift.getCandyList();
        giftCandyList.removeIf(candy -> candy.getId() == candyId);
        giftDao.updateGift(gift);

        return gift;
    }

    public Gift removeCandiesFromGift(ObjectId giftId, List<ObjectId> candyIdList){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        if(candyIdList.isEmpty()){
            return gift;
        }

        List<Candy> giftCandyList = gift.getCandyList();
        giftCandyList.removeIf(candy -> candyIdList.contains(candy.getId()));
        giftDao.updateGift(gift);

        return gift;
    }

    public double getTotalGiftPrice(ObjectId giftId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        return calculateTotalCandyListPrice(gift.getCandyList());
    }

    public int getTotalGiftWeight(ObjectId giftId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        return calculateTotalCandyListWeight(gift.getCandyList());
    }

    public int getTotalGiftSugarAmount(ObjectId giftId){
        if(giftId == null){
            throw new InvalidPropertiesException("Gift id is invalid");
        }

        Gift gift = getGiftById(giftId);
        if(gift == null){
            throw new NotFoundException("Gift with id: " + giftId.toHexString() + " not found");
        }

        return calculateTotalCandyListSugarAmount(gift.getCandyList());
    }

    public static double calculateTotalCandyListPrice(List<Candy> candyList){
        return candyList.stream()
                .map(Candy::getPrice)
                .reduce(0d, (total, price) -> total += price);
    }

    public static int calculateTotalCandyListWeight(List<Candy> candyList){
        return candyList.stream()
                .map(Candy::getWeight)
                .reduce(0, (total, weight) -> total += weight);
    }

    public static int calculateTotalCandyListSugarAmount(List<Candy> candyList){
        return candyList.stream()
                .map(Candy::getSugar)
                .reduce(0, (total, sugar) -> total += sugar);
    }
}
