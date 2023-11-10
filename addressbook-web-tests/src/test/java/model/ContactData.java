package model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String address,
        String email,
        String photo,
        String home,
        String mobile,
        String work,
        String secondary,
        String email2,
        String email3,
        String address2
) {
    public ContactData() {
        this("","", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.email, this.photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, email, this.photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, photo, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withHomePhone(String home) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, home, this.mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withMobilePhone(String mobile) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, mobile, this.work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withWorkPhone(String work) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, work, this.secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withSecondaryPhone(String secondary) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, secondary, this.email2, this.email3, this.address2);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, secondary, email2, this.email3, this.address2);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, secondary, this.email2, email3, this.address2);
    }

    public ContactData withAddress2(String address2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.email, this.photo, this.home, this.mobile, this.work, secondary, this.email2, email3, address2);
    }

}