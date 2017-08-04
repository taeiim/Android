package com.example.parktaeim.requestresponse;

/**
 * Created by parktaeim on 2017. 8. 2..
 */

public class Notice {

    private int Category;
    private int Number;
    private String Content;
    private String Title;
    private int Size;

    public Notice(String name, String writer, String date) {
        Name = name;
        Writer = writer;
        Date = date;
    }

    private String Link;
    private String Name;
    private String Writer;
    private String Date;
    private int HomeNumber;

    public int getCategory() {
        return Category;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getHomeNumber() {
        return HomeNumber;
    }

    public void setHomeNumber(int homeNumber) {
        HomeNumber = homeNumber;
    }


}
