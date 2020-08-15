package model;

public class Mentor extends User{

    String nickName;
    String city;
    int favouriteNumber;

    public Mentor(int idUser, String firstName, String lastName, String nickName, String phoneNumber, String email, String city, int favouriteNumber) {
        super(idUser, firstName, lastName, phoneNumber, email);
        this.nickName = nickName;
        this.city = city;
        this.favouriteNumber = favouriteNumber;
    }


    public Mentor() {

    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFavouriteNumber() {
        return favouriteNumber;
    }

    public void setFavouriteNumber(int favouriteNumber) {
        this.favouriteNumber = favouriteNumber;
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
