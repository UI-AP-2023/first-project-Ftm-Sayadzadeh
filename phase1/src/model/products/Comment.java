package model.products;

import model.user.User;

public class Comment {
    private final User commentingUser;
    private String productID;
    private String commentText;            //final?
    private CommentStatus commentStatus;
    private boolean isBought;                                     //if user buy it you need to change it!

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

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
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

    public void setBought(boolean bought) {
        isBought = bought;
    }

    @Override
    public String toString() {
        return "rating user name : " + commentingUser.getUsername() + "\n" +
                "product ID : " + productID + "\n" +
                "comment text : " + commentText + "\n" +
                "comment status : " + commentStatus.toString() + "\n" +
                "is bought?  " + isBought + "\n";
    }
}
