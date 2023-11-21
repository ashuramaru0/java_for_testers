package model;

public record ContactData(String id, String firstname, String lastname, String photo,
                          String home, String mobile, String work, String secondary, String address, String address2,
                          String email, String email2, String email3) {

    public ContactData() {
        this("", "", "", "src/test/resources/images/avatar.png", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);

    }

    public ContactData withFirstName(String firstname) {
        return new ContactData(this.id, firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);

    }

    public ContactData withLastName(String lastname) {
        return new ContactData(this.id, this.firstname, lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);

    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname, this.lastname, photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);

    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, work, this.secondary, this.address, this.address2, this.email, this.email2, this.email3);
    }

    public ContactData withSecondary(String secondary) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, secondary, this.address, this.address2, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, address, this.address2, this.email, this.email2, this.email3);
    }

    public ContactData withAddress2(String address2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, address2, this.email, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, email, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstname, this.lastname, this.photo, this.home, this.mobile, this.work, this.secondary, this.address, this.address2, this.email, this.email2, email3);
    }

}