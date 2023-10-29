package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;
    @Entity
    @Table(name = "addressbook")
    public class ContactRecord {
        @Id
        public int id;
        @Column(name = "firstname")
        public String firstName;
        @Column(name = "lastname")
        public String lastName;
        public String address;
        public String email;
        @Column(name = "home")
        public String phone;

        public String middlename = "";
        public String nickname = "";
        public String company = "";
        public String title = "";
        public String mobile = "";
        public String work = "";
        public String fax = "";
        public String email2 = "";
        public String email3 = "";
        public String im = "";
        public String im2 = "";
        public String im3 = "";
        public String homepage = "";
        public int bday = 0;
        public String bmonth = "";
        public String byear = "";
        public int aday = 0;
        public String amonth = "";
        public String ayear = "";
        public String address2 = "";
        public String phone2 = "";
        public String notes = "";
        public ContactRecord() {
        }

        public ContactRecord(int id, String firstName, String lastName, String address, String email, String phone) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.email = email;
            this.phone = phone;
        }
}
