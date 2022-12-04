package com.example.sweetgift.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "gift")
public class Gift implements Serializable {
    @Id
    private ObjectId id;
    private String receiver;
    private String address;
    private LocalDate orderDate;
    private List<Candy> candyList;

    public Gift(){
        id = new ObjectId();
        this.candyList = new ArrayList<>();
        orderDate = LocalDate.now();
    }

    public Gift(String receiver, String address){
        this();
        this.receiver = receiver;
        this.address = address;
    }

    public Gift(String receiver, String address, List<Candy> candyList) {
        this();
        this.receiver = receiver;
        this.address = address;
        this.candyList = candyList;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public List<Candy> getCandyList() {
        return candyList;
    }

    public void setCandyList(List<Candy> candyList) {
        this.candyList = candyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gift gift = (Gift) o;
        return Objects.equals(id, gift.id) && Objects.equals(receiver, gift.receiver) && Objects.equals(address, gift.address) && Objects.equals(orderDate, gift.orderDate) && Objects.equals(candyList, gift.candyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receiver, address, orderDate, candyList);
    }

    @Override
    public String toString() {
        return "Gift{" +
                "id=" + id +
                ", receiver='" + receiver + '\'' +
                ", address='" + address + '\'' +
                ", orderDate=" + orderDate +
                ", candyList=" + candyList +
                '}';
    }
}
