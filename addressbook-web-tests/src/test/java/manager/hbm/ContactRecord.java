package manager.hbm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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
        public String home;

        public String mobile;
        public String work ;
        @Column(name = "phone2")
        public String secondary;
        public ContactRecord() {
        }

        public ContactRecord(int id, String firstName, String lastName, String address, String email, String home) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.email = email;
            this.home = home;
        }
        @ManyToMany
        @JoinTable(name = "address_in_groups",
                joinColumns = @JoinColumn(name = "id"),
                inverseJoinColumns = @JoinColumn(name = "group_id"))
        public List<GroupRecord> groups;
}
