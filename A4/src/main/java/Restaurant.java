public class Restaurant {
    private String name;
    private String address;
    private String rating;
    private String cuisineType;
    private String phoneNumber;
    private String price;
    private String id;

    // Constructor
    public Restaurant(String name, String address, String rating, String cuisineType, String phoneNumber, String price, String id) {
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.cuisineType = cuisineType;
        this.phoneNumber = phoneNumber;
        this.price = price;
        this.id = id;
     }

    // Getters and Setters
    public String getId() {
    	return this.id;
    }
    public String getPrice() {
    	return this.price;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", cuisineType='" + cuisineType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
