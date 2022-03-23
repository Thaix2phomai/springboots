package vn.techmaster.myfirstweb.model;

public class BMI {
    private double height;
    private double weight;
    public BMI(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
}
