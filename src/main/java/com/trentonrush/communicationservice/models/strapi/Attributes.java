package com.trentonrush.communicationservice.models.strapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public class Attributes {
    private String name;
    @JsonProperty("shortDescription")
    private List<Description> shortDescriptionList;
    @JsonProperty("longDescription")
    private List<Description> longDescriptionList;
    private BigDecimal price;
    private String category;
    private String createdAt;
    private String updatedAt;
    private String publishedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Description> getShortDescriptionList() {
        return shortDescriptionList;
    }

    public void setShortDescriptionList(List<Description> shortDescriptionList) {
        this.shortDescriptionList = shortDescriptionList;
    }

    public List<Description> getLongDescriptionList() {
        return longDescriptionList;
    }

    public void setLongDescriptionList(List<Description> longDescriptionList) {
        this.longDescriptionList = longDescriptionList;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }
}
