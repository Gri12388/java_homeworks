package com.gmail.gri12388;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://msk.top-academy.ru/").get();
            System.out.println(document.title());

            Element body = document.body();

            Elements h1s = body.getElementsByTag("h1");
            for(Element h1 : h1s) {
                System.out.println(h1.text());
            }

//            Elements ps = body.getElementsByTag("p");
//            for(Element p : ps) {
//                System.out.println(p.text());
//            }

            Elements as = body.getElementsByTag("a");
            Iterator<Element> iterator =  as.iterator();
            if (iterator.hasNext()) System.out.println(iterator.next().text());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
