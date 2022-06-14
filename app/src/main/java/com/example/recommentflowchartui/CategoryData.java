package com.example.recommentflowchartui;

public class CategoryData {

    private int star;
    private String cname;
    private String owner;

    public CategoryData(int star, String cname) {
        this.star = star;
        this.cname = cname;

    }

    public CategoryData(int star, String cname, String owner) {
        this.star = star;
        this.cname = cname;
        this.owner=owner;

    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return cname;
    }

    public void setName(String cname) {
        this.cname = cname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
