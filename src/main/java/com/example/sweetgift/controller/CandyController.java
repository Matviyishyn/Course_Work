package com.example.sweetgift.controller;

import com.example.sweetgift.model.Candy;
import com.example.sweetgift.model.CandyFilters;
import com.example.sweetgift.model.exceptions.InvalidPropertiesException;
import com.example.sweetgift.service.CandyService;
import com.example.sweetgift.utils.candy.CandyFiltersUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CandyController {
    private CandyService candyService;

    @Autowired
    CandyController(CandyService service){
        this.candyService = service;
    }

    @GetMapping("/candy")
    public String getCandies(Model model){
        List<Candy> candyList = candyService.getAllCandies();

        model.addAttribute(candyList);

        return "candy";
    }

    @GetMapping("/candy/filter")
    public String filterCandies(CandyFilters filters, Model model){
        List<Candy> candyList = candyService.getAllCandies();
        CandyFiltersUtils candyFilter = new CandyFiltersUtils(candyList, filters);

        candyList = candyFilter.filter();

        model.addAttribute(candyList);

        return "candy";
    }

    @PostMapping("/candy/new")
    public String makeNewCandy(Candy candy, BindingResult result){
        candyService.saveCandy(candy);

        return "redirect:/candy";
    }

    @PostMapping("/candy/edit/{id}")
    public String updateCandy(@PathVariable String id, Candy candy, BindingResult result){
        candyService.updateCandy(new ObjectId(id), candy);

        return "redirect:/candy";
    }

    @DeleteMapping("/candy/delete/{id}")
    public String deleteCandy(@PathVariable String id){
        candyService.deleteCandyById(new ObjectId(id));

        return "redirect:/candy";
    }

    @ExceptionHandler({InvalidPropertiesException.class})
    public ModelAndView invalidPropertiesHandler(Exception e) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("exception", e.getMessage());
        mav.setViewName("exception");

        return mav;
    }
}
