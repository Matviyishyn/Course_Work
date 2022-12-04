package com.example.sweetgift.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "candy")
public class Candy{
    @Id
    private ObjectId id;

    private String name;
    private String brand;
    private String imageUrl;

    private double price;
    private int weight;
    private int sugar;

    public Candy(){
        id = new ObjectId();
        imageUrl = "https://cdn-icons-png.flaticon.com/512/102/102452.png";
    }

    public Candy(String name, String brand, String imageUrl, double price, int weight, int sugar) {
        id = new ObjectId();
        this.name = name;
        this.brand = brand;
        this.imageUrl = imageUrl;
        this.price = price;
        this.weight = weight;
        this.sugar = sugar;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSugar() {
        return sugar;
    }

    public void setSugar(int sugar) {
        this.sugar = sugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candy candy = (Candy) o;
        return Double.compare(candy.price, price) == 0 && weight == candy.weight && sugar == candy.sugar && Objects.equals(id, candy.id) && Objects.equals(name, candy.name) && Objects.equals(brand, candy.brand) && Objects.equals(imageUrl, candy.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brand, imageUrl, price, weight, sugar);
    }

    @Override
    public String toString() {
        return "Candy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", sugar=" + sugar +
                '}';
    }
}
