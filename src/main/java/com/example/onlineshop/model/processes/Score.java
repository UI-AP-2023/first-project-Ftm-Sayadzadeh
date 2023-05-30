package com.example.onlineshop.model.processes;

import com.example.onlineshop.model.products.Product;
import com.example.onlineshop.model.user.User;

public class Score {
    private final User ratingUser;
    private final double score;
    private final Product product;

    public Score(User ratingUser, double score, Product product) {
        this.ratingUser = ratingUser;
        this.score = score;
        this.product = product;
    }

    public User getRatingUser() {
        return ratingUser;
    }

    public double getScore() {
        return score;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "rating user name : " + ratingUser.getUsername() + "\n" +
                "product ID : " + product.getProductID() + "\n" +
                "score : " + score + "\n";
    }
}
