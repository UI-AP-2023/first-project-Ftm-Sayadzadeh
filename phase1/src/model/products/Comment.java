package model.products;

import model.user.User;

public class Comment {
    private final User commentingUser;
    private final String productID;
    private final String commentText;
    private CommentStatus commentStatus;
    private final boolean isBought;

    public Comment(User commentingUser, String productID, String productText, boolean isBought) {
        this.commentingUser = commentingUser;
        this.productID = productID;
        this.commentText = productText;
        this.commentStatus = CommentStatus.Waiting;
        this.isBought = isBought;
    }

    public User getCommentingUser() {
        return commentingUser;
    }

    public String getProductID() {
        return productID;
    }

    public String getCommentText() {
        return commentText;
    }

    public CommentStatus getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(CommentStatus commentStatus) {
        this.commentStatus = commentStatus;
    }

    public boolean isBought() {
        return isBought;
    }

    @Override
    public String toString() {
        return "commenter username : " + commentingUser.getUsername() + "\n" +
                "product ID : " + productID + "\n" +
                "comment text : " + commentText + "\n" +
                "comment status : " + commentStatus.toString() + "\n" +
                "is bought?  " + isBought + "\n";
    }
}
