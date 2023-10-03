package model;

public record ContactData(String firstName, String lastName, String address, String email, String phone) {

    public ContactData(){
        this("", "", "", "", "");
    }
    public ContactData withFirstName(String firstName){
        return new ContactData(firstName, this.lastName, this.address, this.email,this.phone);
    }

    public ContactData withLastName(String lastName){
        return new ContactData(this.firstName, lastName, this.address, this.email,this.phone);
    }

    public ContactData withAddress(String address){
        return new ContactData(this.firstName, this.lastName, address, this.email,this.phone);
    }

    public ContactData withEmail(String email){
        return new ContactData(this.firstName, this.lastName, this.address, email,this.phone);
    }

    public ContactData withPhone(String phone){
        return new ContactData(this.firstName, this.lastName, this.address, this.email,phone);
    }
}
