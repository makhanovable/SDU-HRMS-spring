package com.quaziwerk.sduhrms.scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scraper {

    /**
     * регион
     * профобласть
     * зарплата
     * опыт работы
     * ключевые навыки
     * язык
     * возраст
     * тип занятости
     * график работы
     * категория прав
     * пол
     * исключение
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

}
