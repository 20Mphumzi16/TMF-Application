/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.tmf.domain;

/**
 *
 * @author Mpumzi Mbula
 */
public class Category {
    
    private String categoryNumber;
    private String categoryDescription;

    public Category() {
    }

    public Category(String categoryNumber, String categoryDescription) {
        this.categoryNumber = categoryNumber;
        this.categoryDescription = categoryDescription;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public String toString() {
        return "Category{" + "categoryNumber=" + categoryNumber + ", categoryDescription=" + categoryDescription + '}';
    }
    
    
}
