//package com.application.entity;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "ingredient")
//public class Ingredient {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ingredient_id")
//    private int ingredientId;
//    @Column(name = "isAllergic")
//    private boolean isAllergic;
//    @Column(name = "isVegetarian")
//    private boolean isVegetarian;
//    @Column(name = "isVegan")
//    private boolean isVegan;
//    @Column(name = "shelf_time")
//    private int shelfTime;
//
//    //Used to be: private List<MenuItemIngredient> menuItems = new ArrayList<>();
//    @OneToMany(mappedBy = "ingredient")
//    private List<MenuItemIngredient> menuItems;
//
//
//    public Ingredient() {
//    }
//
//    public Ingredient(int ingredientId, boolean isAllergic, boolean isVegetarian, boolean isVegan, int shelfTime) {
//        this.ingredientId = ingredientId;
//        this.isAllergic = isAllergic;
//        this.isVegetarian = isVegetarian;
//        this.isVegan = isVegan;
//        this.shelfTime = shelfTime;
//    }
//
//    public int getIngredientId() {
//        return ingredientId;
//    }
//
//    public void setIngredientId(int ingredientId) {
//        this.ingredientId = ingredientId;
//    }
//
//    public boolean isAllergic() {
//        return isAllergic;
//    }
//
//    public void setAllergic(boolean allergic) {
//        isAllergic = allergic;
//    }
//
//    public boolean isVegetarian() {
//        return isVegetarian;
//    }
//
//    public void setVegetarian(boolean vegetarian) {
//        isVegetarian = vegetarian;
//    }
//
//    public boolean isVegan() {
//        return isVegan;
//    }
//
//    public void setVegan(boolean vegan) {
//        isVegan = vegan;
//    }
//
//    public int getShelfTime() {
//        return shelfTime;
//    }
//
//    public void setShelfTime(int shelfTime) {
//        this.shelfTime = shelfTime;
//    }
//
//    @Override
//    public String toString() {
//        return "Ingredient{" +
//                "ingredientId=" + ingredientId +
//                ", isAllergic=" + isAllergic +
//                ", isVegetarian=" + isVegetarian +
//                ", isVegan=" + isVegan +
//                ", shelfTime=" + shelfTime +
//                '}';
//    }
//}
