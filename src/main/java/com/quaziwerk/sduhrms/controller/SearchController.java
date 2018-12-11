package com.quaziwerk.sduhrms.controller;

import com.quaziwerk.sduhrms.model.ResultRecord;
import com.quaziwerk.sduhrms.scraping.Scraper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("search")
public class SearchController {

    @GetMapping("/{word}/{source}")
    public List<ResultRecord> getResult(@PathVariable("word") String word, @PathVariable("source") String source) {
        return Scraper.search(word);
    }

}
