package org.example.csvRead.csv;

public class StructureCSV {
    private String name;
    private String artucul;
    private int minItem;
    private int price;
    private int item;

    public StructureCSV(String name, String articular, int minItem, int price, int item) {
        this.name = name;
        this.artucul = articular;
        this.minItem = minItem;
        this.price = price;
        this.item = item;
    }


    public String getName() {
        return name;
    }

    public String getArtucul() {
        return artucul;
    }

    public int getMinItem() {
        return minItem;
    }

    public int getPrice() {
        return price;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "name= " + name + ", artucul= " + artucul +", minItem= " + minItem + ", price= " + price + ", item= " + item;
    }

}
