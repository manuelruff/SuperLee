public class ContactMember {

    private String phone_number;
    private String name;
    private String email;

    public ContactMember( String phone_number, String name, String email) {
        this.phone_number = phone_number;
        this.name = name;
        this.email = email;
    }


    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void printContactMember()
    {
        System.out.println("Name: "+this.name+" Email: "+this.email+ " Phone Number: "+this.phone_number );
    }
}
