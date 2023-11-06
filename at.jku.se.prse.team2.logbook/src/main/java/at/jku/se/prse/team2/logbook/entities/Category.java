package at.jku.se.prse.team2.logbook.entities;

public class Category {
    private Integer category_id;
    private String name;

    public Category(Integer category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public Category() {

    }

    public Category(String name) {
        this.name = name;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
