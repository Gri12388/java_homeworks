package com.gmail.gri12388;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://msk.top-academy.ru/").get();
            Element body = document.body();
            Iterator<Element> iterator;
            String attribute = "href";
            String selector = ".main-phone--withDropDown";
            String className = "main-phone--withDropDown";

            System.out.println("Первые три тега \"h1\":");
            Elements h1s = body.getElementsByTag("h1");
            iterator = h1s.iterator();
            for (int i = 0; iterator.hasNext() && i < 3; i++) {
                System.out.println("%d) %s".formatted(i+1, iterator.next().text()));
            }

            System.out.println();
            System.out.println("Первые три тега \"p\":");
            Elements ps = body.getElementsByTag("p");
            iterator = ps.iterator();
            for (int i = 0; iterator.hasNext() && i < 3; i++) {
                System.out.println("%d) %s".formatted(i+1, iterator.next().text()));
            }

            System.out.println();
            System.out.println("Первые три тега \"a\":");
            Elements as = body.getElementsByTag("a");
            iterator = as.iterator();
            for (int i = 0; iterator.hasNext() && i < 3; i++) {
                System.out.println("%d) %s".formatted(i+1, iterator.next().text()));
            }

            System.out.println();
            System.out.println("Атрибут \"href\" первых трех тегов \"a\":");
            iterator = as.iterator();
            for (int i = 0; iterator.hasNext() && i < 3; i++) {
                Element element = iterator.next();

                if (element.hasAttr(attribute)) {
                    System.out.println("%d) %s".formatted(i+1, element.attr(attribute)));
                }
            }

            System.out.println();
            System.out.println("первые три элемента с классом \".main-phone--withDropDown\":");
            Elements selectedElements = body.select(".main-phone--withDropDown");
            iterator = selectedElements.iterator();
            for (int i = 0; iterator.hasNext() && i < 3; i++) {
                System.out.println("%d) %s".formatted(i+1, iterator.next().text()));
            }

            System.out.println();
            Element selectedElement;
            iterator = selectedElements.iterator();
            if (iterator.hasNext()) {
                selectedElement = iterator.next();
                System.out.println("Классы элемента до изменения");
                System.out.println(Arrays.toString(selectedElement.classNames().toArray()));
                selectedElement.addClass("temp");
                System.out.println("Добвлен класс \"temp\"");
                System.out.println(Arrays.toString(selectedElement.classNames().toArray()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
