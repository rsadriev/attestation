package org.example;

public class TV {
    private String color;
    private String vendor;
    private Double size;

    public TV(String color, String vendor, Double size) {
        this.color = color;
        this.vendor = vendor;
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "цвет = " + color +
                ", производитель = " + vendor +
                ", диагональ = " + size;
    }
}
