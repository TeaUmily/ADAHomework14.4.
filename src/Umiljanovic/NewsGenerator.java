package Umiljanovic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;

//Create a CMS using console interface. Types used: Author, Category, News. Program enables CRUD on each type,
//list news by categories, authors and dates of news. One news can be in many categories.

public class NewsGenerator {

    int a;
    Scanner in;
    List<News> allNews;
    List<Category> allCategories;
    List<Author> allAuthors;
    List<News> newsToRemove;
    List<Author> authorsToRemove;
    List<Category> categoriesToRemove;

    public NewsGenerator() {

        in= new Scanner(System.in);
        allNews= new ArrayList<>();
        allCategories= new ArrayList<>();
        allAuthors= new ArrayList<>();
        newsToRemove = new ArrayList<>();
        authorsToRemove= new ArrayList<>();
        categoriesToRemove = new ArrayList<>();


        do{
            System.out.println("Please select:\n 1-Create News \n 2-Update News \n 3-Delete News\n 4-Show News\n 5-Quit ");
            a= in.nextInt();
            String buff= in.nextLine();
            switch (a){
                case (1):{
                    createNews();
                    break;}
                case (2):{
                    updateNews();
                    break;}
                case (3):{
                    deleteNews();
                    break;}
                case (4):{
                    showNews();
                    break;}
                case (5):{
                    break;}
                    default: System.out.println("Please enter number from 1 to 5! \n");
            }
        }while(a!=5);



    }

    private void showNews() {

        if(allNews.isEmpty()){
            System.out.println("There is no news to show!");
            return;
        }

        int a;
        System.out.println("List news:\n 1- by date \n 2- by categories \n 3- by authors");

        do {
            a = in.nextInt();

            switch (a) {
                case (1): {
                    showNewsByDate();
                    break;
                }
                case (2): {
                    ShowNewsByCategories();
                    break;
                }
                case (3): {
                    ShowNewsByAuthors();
                    break;
                }
                default: {
                    System.out.println("Please enter number form 1 to 3!");
                }

            }
        }while(a!=1 && a!=2 && a!=3);

    }

    private void ShowNewsByAuthors() {

        for (Author a: allAuthors
                ) {
            System.out.println("A U T H O R: "+a.getName()+" "+a.getSurname());
            for (News n:allNews
                    ) { if(checkIfContainsAuthor(n,a)){
                        n.printNews();
            }

            }

        }

    }

    private boolean checkIfContainsAuthor(News n, Author a) {
        return n.getAuthor().CompareTo(a);
    }

    private void ShowNewsByCategories() {

        for (Category c:allCategories
                ) {
            System.out.println("C A T E G O R Y: "+c.getName());
            for (News n:allNews
                    ) { if(checkIfContainsCategory(n,c)){

                n.printNews();
            }

            }

        }
    }

    private boolean checkIfContainsCategory(News n, Category c) {
        boolean flag=false;
        for (Category cat : n.getCategories()
             ) {
            if(cat.getName().equals(c.getName())){
                 flag=true;
        }

        }
        return flag;
    }

    private void showNewsByDate() {

        System.out.println("** LATEST NEWS ON TOP **");
        Collections.sort(allNews);
        Collections.reverse(allNews);

        for (News n:allNews
             ) {n.printNews();

        }


    }

    private void deleteNews() {

        int a;
        if(allNews.isEmpty()){
            System.out.println("There is no news to delete!");
            return;
        }

        System.out.println("Delete by: \n 1-title\n 2-author\n 3-category");
        do {
            a = in.nextInt();
            String buff = in.nextLine();
            switch (a) {
                case (1): {
                    deleteByTitle();
                    break;
                }
                case (2): {
                    deleteByAuthor();
                    break;
                }
                case (3): {
                    deleteByCategory();
                    break;
                }
                default:
                    System.out.println("Please enter number form 1 to 3!");
            }

        }while(a!=1 && a!=2 && a!=3);

        }

    private void deleteByAuthor() {
        System.out.println("Author with whom you would like to delete the news:");
        Author author= generateAuthor();

        for (News n:allNews
             ) {
            if (checkIfContainsAuthor(n, author)) {
                newsToRemove.add(n);
            }
        }
        allNews.removeAll(newsToRemove);


        for (Author a:allAuthors
             ) {if(a.CompareTo(author)){
                 authorsToRemove.add(a);
        }
        }
       allAuthors.removeAll(authorsToRemove);

    }

    private void deleteByCategory() {

        System.out.println("Write category by which you would like to delete the news:");
        String cat=in.nextLine();

        Category category= new Category(cat);

        for (News n:allNews
             ) {
            if(checkIfContainsCategory(n,category)){
                newsToRemove.add(n);
            }

        }
        allNews.removeAll(newsToRemove);

        for (Category c:allCategories
             ) {if(c.compareTo(category)){
                 categoriesToRemove.add(c);
        }

        }
        allCategories.removeAll(categoriesToRemove);


    }

    private void deleteByTitle() {

        System.out.println("Write the title of a news you want to delete: ");
        String title  = in.nextLine();

        if(checkIfContainsTitle(title)==false){
            System.out.println("There is no news with that title!");
            return;
        }

        for (News n:allNews
             ) {
            if (n.getTitle().equals(title)) {
            newsToRemove.add(n);
            }

        }
        allNews.removeAll(newsToRemove);



    }

    private void updateNews() {

        if(allNews.isEmpty()){
            System.out.println("There is no news to update!");
            return;
        }

        int a;

        System.out.println("Write the title of a news you want to update: ");
        String title  = in.nextLine();

        if(checkIfContainsTitle(title)==false){
            System.out.println("There is no news with that title!");
            return;
        }

        System.out.println("Update:\n 1- author \n 2- categories \n 3- news text");

        do {
            a = in.nextInt();
            String buff = in.nextLine();
            switch (a) {
                case (1): {
                    updateAuthor(title);
                    break;
                }
                case (2): {
                    updateCategories(title);
                    break;
                }
                case (3): {
                    updateText(title);
                    break;
                }
                default:
                    System.out.println("Please enter number form 1 to 3!");
            }

        }while(a!=1 && a!=2 && a!=3);


    }

    private boolean checkIfContainsTitle(String title) {
        boolean flag=false;
        for (News n: allNews
             ) {
            if(n.getTitle().equals(title)) {
                flag=true;
            }

        }
        return  flag;
    }

    private void updateText(String title) {


        System.out.println("Write new text: ");
        String newText= in.nextLine();

        for (News n:allNews
                ) {if(n.getTitle().equals(title)){
            n.updateNewsText(newText);
        }
        else{
            System.out.println("There is no news with that title, please try again!");
        }
        }


    }

    private void updateCategories(String title) {


        List<Category> categories= new ArrayList<>();
        createCategories(categories);
        for (News n:allNews
                ) {if(n.getTitle().equals(title)){
            n.updateNewsCategories(categories);
        }
        }

    }

    private void updateAuthor(String title) {


        System.out.println("Enter new author's name:");
        String name=in.nextLine();
        System.out.println("Enter new author's surname:");
        String surname= in.nextLine();
        Author newAuthor= new Author(name, surname);

        for (News n:allNews
                ) {
            if(n.getTitle().equals(title)){
                updateAllAuthors(n.getAuthor(),newAuthor);
                    n.updateNewsAuthor(newAuthor);
        }

        }


    }

    private void updateAllAuthors(Author oldAuthor, Author newAuthor) {

        int counter=0;
        for (News n:allNews
             ) {if(oldAuthor.CompareTo(n.getAuthor())){
                 counter++;
        }

        }

        if(counter==1){
        for (Author a: allAuthors
             ) {
            if (a == oldAuthor) {
                a.setName(newAuthor.getName());
                a.setSurname(newAuthor.getSurname());
            }
        }
        }
    }


    private void createNews() {

        List<Category> categories= new ArrayList<>();

        String txt;
        String title;
        System.out.println("add news title:");
        title= in.nextLine();

        System.out.println("add news text:");
        txt= in.nextLine();


        categories= createCategories(categories);

        Author author= generateAuthor();

        addInAllAuthors(author);

        News currentNews= new News(title,txt, author);

        for (Category c: categories
             ) {
            currentNews.addCategory(c);
        }

        allNews.add(currentNews);

    }

    private List<Category> createCategories(List<Category> categories) {

        Category category;
        String a;
        boolean flag= false;


        do{
            System.out.println("news category: ");
            category= new Category(in.nextLine());
            categories.add(category);
            addInAllCategories(category);
            System.out.println("add more categories [yes or no]");
            a= in.nextLine();

            while (!a.equals("yes")&& !a.equals("no")){
                System.out.println("Please write yes or no!");
                a= in.nextLine();
            }


            if(a.equals("yes")){flag=true;}
            else{flag =false;}

        }while (flag==true);

        return categories;
    }


    private void addInAllAuthors(Author author) {

      boolean flag= false;
        for (Author a:allAuthors
             ) {
            if(a.CompareTo(author)){
                flag= true;

            }

        }

        if(flag==false){
            allAuthors.add(author);
        }
    }

    private void addInAllCategories(Category category) {

        boolean flag=false;
        for (Category c:allCategories
             ) {
            if(c.getName().equals(category.getName())){
                flag=true;
            }
        }
        if(flag==false){
            allCategories.add(category);
        }

    }

    private Author generateAuthor(){

        Author author;
        String name;
        String surname;
        System.out.println("author's name: ");
        name=in.nextLine();
        System.out.println("author's surname: ");
        surname = in.nextLine();
        author = new Author(name, surname);
        return author;
    }


    public static void main(String[] args) {
    NewsGenerator s = new NewsGenerator();
    }


    }


