package com.holeksa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by bourbonkid on 11.01.17.
 */

@Entity
public class CoffeeProduct {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    private Integer rate;

    private Integer price;

    private String description;

    public CoffeeProduct() {

    }

    public CoffeeProduct(CoffeeProduct coffeeProduct) {
        this.setId(coffeeProduct.id);
        this.setName(coffeeProduct.getName());
        this.setRate(coffeeProduct.getRate());
        this.setPrice(coffeeProduct.getPrice());
        this.setDescription(coffeeProduct.getDescription());
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return description;
    }

    public void setContent(String description) {
        this.description = description;
    }
}
