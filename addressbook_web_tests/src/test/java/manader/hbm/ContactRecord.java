package manader.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public String email;
    public String home;
    public String mobile;
    public String work;
    public String phone2;

    public String nickname = "";
    public String company = "";
    public String title = "";

    public String middlename = "";

    public String fax = "";
    public String email2;
    public String email3;
    public String im = "";
    public String im2 = "";
    public String im3 = "";
    public String homepage = "";
    public String bmonth = "";
    public int bday = 0;
    public String byear = "";
    public int aday = 0;
    public String amonth = "";
    public String ayear = "";
    public String address2;
    public String notes = "";
    public String photo = "";

    public Date deprecated = null;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstname, String lastname, String photo, String home, String mobile, String work, String phone2, String address, String address2, String email, String email2, String email3) {

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.photo = photo;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.phone2 = phone2;
        this.address = address;
        this.address2 = address2;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;

    }
}