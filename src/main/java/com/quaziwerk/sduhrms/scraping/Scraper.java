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

    public static List<ResultRecord> search(String word) {
        List<ResultRecord> list = new ArrayList<>();
        try {
            String url = "https://hh.kz/search/resume?text=" + word +
                    "&logic=normal&pos=full_text&exp_period=all_time&clusters=true&area=" +
                    "160&currency_code=KZT&order_by=relevance&no_magic=false";
            final Document document = Jsoup.connect(url).get();
            for (Element row : document.select("div.resume-search-item")) {

                ResultRecord resultRecord = new ResultRecord();
                resultRecord.id = row.attr("data-hh-resume-hash");

                resultRecord.position = row.select("a").first().text();
                resultRecord.age = row.select("div.resume-search-item__fullname").select("span").first().text();
                resultRecord.experience = row.select("div.resume-search-item__description-content").first().text();

                if (resultRecord.experience.length() >= 20) {
                    resultRecord.experience = "";
                }
                resultRecord.last_job = row.select("button.bloko-link-switch").text() +" "+
                        row.select("span.resume-search-item__company-name").text();

                list.add(resultRecord);
            }

        } catch (Exception ignored) {
        }
        return list;
    }

}
