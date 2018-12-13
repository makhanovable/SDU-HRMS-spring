package com.quaziwerk.sduhrms.controller;

import com.quaziwerk.sduhrms.model.ResultRecordWrapper;
import com.quaziwerk.sduhrms.scraping.Scraper;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("search")
public class SearchController {

    @GetMapping()
    public ResultRecordWrapper getResult(@RequestParam("wrd") String wrd,
                                         @RequestParam("exp") String exp,
                                         @RequestParam("sal") String sal,
                                         @RequestParam("src") String src,
                                         @RequestParam("page") String page) {
        return Scraper.search(wrd, exp, sal, src, page);
    }

}
