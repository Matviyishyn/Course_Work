package com.example.sweetgift.controller;

import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.CandyFilters;
import com.example.sweetgift.model.Gift;
import com.example.sweetgift.model.exceptions.InvalidPropertiesException;
import com.example.sweetgift.model.exceptions.NotFoundException;
import com.example.sweetgift.service.CandyService;
import com.example.sweetgift.service.GiftService;
import com.example.sweetgift.utils.candy.CandyFiltersUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.sweetgift.utils.gift.GiftValidator.arePropertiesValid;
import static com.example.sweetgift.utils.gift.GiftValidator.isGiftValid;

@Controller
public class GiftController {
    GiftService giftService;
    CandyService candyService;

    public GiftController(@Autowired GiftService giftService, @Autowired CandyService candyService) {
        this.giftService = giftService;
        this.candyService = candyService;
    }

    @GetMapping("/gift")
    public String getGifts(Model model){
        List<Gift> giftList = giftService.getAllGifts();
        model.addAttribute(giftList);

        return "gift/gift";
    }

    @GetMapping("/gift/{id}")
    public String getGiftDetails(@PathVariable String id, Model model){
        Gift gift = giftService.getGiftById(new ObjectId(id));

        if(gift == null) {
            throw new NotFoundException(id.toString());
        }

        Integer weight = GiftService.calculateTotalCandyListWeight(gift.getCandyList());
        Double price = GiftService.calculateTotalCandyListPrice(gift.getCandyList());

        model.addAttribute("weight", weight);
        model.addAttribute("price", price);
        model.addAttribute("gift", gift);

        return "gift/gift_details";
    }

    @GetMapping("/gift/{id}/filter")
    public String filterGiftCandies(@PathVariable String id, CandyFilters filters, Model model){
        Gift gift = giftService.getGiftById(new ObjectId(id));

        if(gift == null) {
            throw new NotFoundException(id.toString());
        }

        Integer weight = GiftService.calculateTotalCandyListWeight(gift.getCandyList());
        Double price = GiftService.calculateTotalCandyListPrice(gift.getCandyList());
        model.addAttribute("weight", weight);
        model.addAttribute("price", price);

        List<Candy> candyList = gift.getCandyList();
        CandyFiltersUtils candyFilters = new CandyFiltersUtils(candyList, filters);
        gift.setCandyList(candyFilters.filter());

        model.addAttribute("gift", gift);

        return "gift/gift_details";
    }

    @GetMapping("/gift/new")
    public String getNewGift(Model model){
        List<Candy> candyList = candyService.getAllCandies();

        model.addAttribute("candyList", candyList);

        return "gift/new_gift";
    }

    @PostMapping("/gift/new")
    public String makeNewGift(
            @RequestParam String receiver,
            @RequestParam String address,
            @RequestParam String candyList
    ){
        if(candyList.isEmpty()){
            throw new InvalidPropertiesException();
        }

        List<String> candyStringIdList = List.of(candyList.split(","));
        List<ObjectId> candyIdList = candyStringIdList.stream()
                .map(ObjectId::new)
                .collect(Collectors.toList());

        List<Candy> candies = candyService.getCandiesById(candyIdList);

        Gift gift = new Gift(receiver, address, candies);

        if(!isGiftValid(gift)){
            throw new InvalidPropertiesException();
        }

        giftService.saveGift(gift);

        return "redirect:/gift";
    }

    @DeleteMapping("/gift/delete/{id}")
    public String deleteCandy(@PathVariable String id){
        giftService.deleteGiftById(new ObjectId(id));

        return "redirect:/";
    }

    @GetMapping("/gift/edit/{id}")
    public String getEditGift(@PathVariable String id, Model model){
        if(!ObjectId.isValid(id)){
            throw new InvalidPropertiesException();
        }

        Gift gift = giftService.getGiftById(new ObjectId(id));
        if(gift == null){
            throw new NotFoundException(id);
        }

        List<Candy> candyList = candyService.getAllCandies();

        model.addAttribute(gift);
        model.addAttribute(candyList);

        return "gift/edit_gift";
    }

    @PostMapping("/gift/edit/{id}")
    public String editGift(
            @PathVariable String id,
            @RequestParam String receiver,
            @RequestParam String address,
            @RequestParam String deleteCandies,
            @RequestParam String addCandies
    ){
        if(!ObjectId.isValid(id)){
            throw new InvalidPropertiesException();
        }
        ObjectId giftId = new ObjectId(id);

        Gift original = giftService.getGiftById(giftId);
        if(original == null){
            throw new NotFoundException(id);
        }

        original.setReceiver(receiver);
        original.setAddress(address);

        if(!isGiftValid(original)){
            throw new InvalidPropertiesException();
        }

        giftService.updateGift(giftId, original);

        if(!deleteCandies.isEmpty()) {
            List<String> deleteStringIdList = List.of(deleteCandies.split(","));
            List<ObjectId> deleteIdList = deleteStringIdList.stream()
                    .map(ObjectId::new).collect(Collectors.toList());
            giftService.removeCandiesFromGift(giftId, deleteIdList);
        }

        if(!addCandies.isEmpty()) {
            List<String> addStringIdList = List.of(addCandies.split(","));
            List<ObjectId> addIdList = addStringIdList.stream()
                    .map(ObjectId::new).collect(Collectors.toList());
            List<Candy> candies = candyService.getCandiesById(addIdList);
            giftService.addCandiesToGift(giftId, candies);
        }

        return "redirect:/gift/" + id;
    }


    @ExceptionHandler({InvalidPropertiesException.class})
    public ModelAndView invalidPropertiesHandler(Exception e) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e.getMessage());
        mav.setViewName("exception");

        return mav;
    }

    @ExceptionHandler({NotFoundException.class})
    public ModelAndView notFoundHandler(Exception e) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e.getMessage());
        mav.setViewName("exception");

        return mav;
    }
}
