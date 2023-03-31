package model.user;

public class CreditIncreaseRequest {
    private final String username;
    private final int creditIncreaseAmount;
    public CreditIncreaseRequest(String username , int creditIncreaseAmount ){
        this.username = username;
        this.creditIncreaseAmount = creditIncreaseAmount;
    }

    public String getUsername() {
        return username;
    }

    public int getCreditIncreaseAmount() {
        return creditIncreaseAmount;
    }
    @Override
    public String toString(){
        return "Username : " + username + "\n" +
                "Credit Amount : " + creditIncreaseAmount + "\n";
    }
}
