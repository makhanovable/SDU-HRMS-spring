package com.quaziwerk.sduhrms.scraping;

import com.quaziwerk.sduhrms.model.ResultRecord;
import com.quaziwerk.sduhrms.model.ResultRecordWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class Scraper {

    private static final String URL = "https://hh.kz/search/resume?clusters=true&" +
            "currency_code=KZT&" +
            "exp_period=all_time&" +
            "logic=normal&" +
            "no_magic=false&" +
            "order_by=relevance&" +
            "pos=full_text&" +
            "area=160";

    public static ResultRecordWrapper search(String wrd, String exp, String sal, String src, String page) {
        if (src.equals("hh"))
            return fromHH(wrd, exp, sal, page);
        else
            return fromLinkedIn(wrd, page);
    }

    public static void main(String[] args) {
        fromHH("android ios", "no", "300#450", "0"); // for test
    }

    private static ResultRecordWrapper fromHH(String text, String exp, String sal, String page) {
        ResultRecordWrapper result = new ResultRecordWrapper();
        List<ResultRecord> list = new ArrayList<>();
        result.count = "0";

        try {
            String url = URL + "&text=" + text;
            if (!exp.equals("no"))
                url = url + "&experience=" + exp;
            if (!sal.equals("no")) {
                String[] fromTo = sal.split("#");
                url = url + "&salary_from=" + fromTo[0];
                if (!fromTo[1].equals("inf"))
                    url = url + "&salary_to=" + fromTo[1];
            }
            url = url + "&page=" + page;
            System.out.println(url);
            final Document document = Jsoup.connect(url).get();

//            List<String> temp = new ArrayList<>();
//            for (Element span: document.select("div.bloko-gap").last().select("a.bloko-button"))
//                temp.add(span.text());
//
//            if (temp.size() != 0) {
//                String last = temp.get(temp.size() - 1);
//                if (last.chars().allMatch(Character::isDigit))
//                    result.count = last;
//                else
//                    result.count = temp.get(temp.size() - 2);
//            }
//            System.out.println(result.count);

            try {
                Element element = document.select("div.resumesearch__result").first().selectFirst("strong");
                result.count = element.text();
            } catch (Exception ignored) {
            }

            for (Element row : document.select("div.resume-search-item")) {

                ResultRecord resultRecord = new ResultRecord();
                resultRecord.id = "https://hh.kz/resume/" + row.attr("data-hh-resume-hash");

                resultRecord.position = row.select("a").first().text();
                resultRecord.age = row.select("div.resume-search-item__fullname").select("span").first().text();
                resultRecord.experience = row.select("div.resume-search-item__description-content").first().text();

                if (resultRecord.experience.length() >= 20) {
                    resultRecord.experience = "";
                }
                resultRecord.last_job = row.select("button.bloko-link-switch").text() + " " +
                        row.select("span.resume-search-item__company-name").text();

                list.add(resultRecord);
            }
        } catch (Exception e) {
            System.out.println("Some error");
            e.printStackTrace();
        }
        result.list = list;
        return result;
    }

    private static ResultRecordWrapper fromLinkedIn(String text, String page) {
        ResultRecordWrapper result = new ResultRecordWrapper();
        List<ResultRecord> list = new ArrayList<>();
        result.count = "0";

        // TODO

        result.list = list;
        return result;
    }

}
