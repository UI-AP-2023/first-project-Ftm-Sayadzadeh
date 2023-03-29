package model.user;

abstract public class User {
    private final String username;
    private String email;
    private String phoneNumber;
    private String password;
    public User(String username , String email ,String phoneNumber , String password ) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString(){
        return "Username : " + username + "\n" +
                "Email : " + email + "\n" +
                "phone number : " + phoneNumber + "\n" +
                "password : " + phoneNumber + "\n" ;
    }
}
