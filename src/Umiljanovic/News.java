package Umiljanovic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class News implements Comparable<News>{


    private String text;
    private String title;
    private List<Category> categories;
    private Date date;
    private Author author;

    public News( String title,String text, Author author) {
        this.title= title;
        this.text = text;
        this.author = author;
        this.categories= new ArrayList<>();
        this.date=new Date();

    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    public String getText() {
        return text;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Date getDate() {
        return date;
    }

    public Author getAuthor() {
        return author;
    }



    private void setText(String text) {
        this.text = text;
    }

    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    private void setAuthor(Author author) {
        this.author = author;
    }

    public void addCategory(Category category){
        categories.add(category);
    }


    public void updateNewsText(String txt){
        this.text=txt;
        this.date=new Date();
    }

    public void printNews(){


        System.out.println("-------------------------------   "+ title +"   --------------------------------");
        System.out.println(text);
        System.out.println("-----------------------------------------------------------------------------------");
    }


    public void updateNewsAuthor(Author newAuthor){
        this.author = newAuthor;
    }

    public int compareTo(News n) {
        return getDate().compareTo(n.getDate());
    }

    public boolean containsCategory(Category category){
        return categories.contains(category);
    }


    public void updateNewsCategories(List<Category> newCategories){
        this.categories=newCategories;
    }

}
