package model.products;

import model.user.User;

public class Score {
    private final User ratingUser;       //final or not?
    private final int score;
    private final Product product;

    public Score(User ratingUser, int score, Product product) {
        this.ratingUser = ratingUser;
        this.score = score;
        this.product = product;
    }

    public User getRatingUser() {
        return ratingUser;
    }

    public int getScore() {
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
