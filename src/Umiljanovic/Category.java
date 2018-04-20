package Umiljanovic;

public class Category {

    private String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }



    public boolean compareTo(Category category){

        return this.getName().equals(category.getName());
    }
}
