package com.quaziwerk.sduhrms.scraping;

import com.quaziwerk.sduhrms.model.ResultRecord;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Scraper {

    /**
     * регион
     * профобласть
     * зарплата         salary_from; salary_to
     * опыт работы      experience
     * ключевые навыки
     * язык
     * возраст
     * тип занятости
     * график работы
     * категория прав
     * пол
     * исключение
     * etc
     *
     * page;
     */

    public static void main(String[] args) throws Exception {
            String url = "https://hh.kz/search/resume?text=&area=40&clusters=true&exp_period=all_time&logic=normal&pos=full_text";
            final Document document = Jsoup.connect(url).get();
            int count = 0;
            for (Element row : document.select("div.resume-search-item")) {
                count++;
                System.out.println(row.attr("data-hh-resume-hash"));
            }
        System.out.println(count);
    }

    public static List<ResultRecord> search() {
        List<ResultRecord> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ResultRecord resultRecord = new ResultRecord();
            resultRecord.id = "vcds";
            resultRecord.position = "vcds";
            resultRecord.age = 22;
            resultRecord.experience = "dcaxvcds";
            resultRecord.last_job = "ewdasfvcds";
            list.add(resultRecord);
        }
        return list;
    }

}
