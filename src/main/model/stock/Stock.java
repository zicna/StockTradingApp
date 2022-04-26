package main.model.stock;

public class Stock{
    private String name;
    private double value;

    public Stock(String name, double value){
        this.name = name;
        this.value = value;
    }

    public Stock (Stock source){
        this.name = source.name;
        this.value = source.value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
}