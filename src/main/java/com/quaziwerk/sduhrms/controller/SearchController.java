package com.quaziwerk.sduhrms.controller;

import com.quaziwerk.sduhrms.model.ResultRecord;
import com.quaziwerk.sduhrms.scraping.Scraper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("search")
public class SearchController {

    @GetMapping
    public List<ResultRecord> getResult() {
        return Scraper.search();
    }

}
