package addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity //tied to db hibernate
@Table(name = "addressbook")
public class AddressData {
    @XStreamOmitField
    @Id
    private int id = Integer.MAX_VALUE;

    @Expose
    private String firstname;
    private String middlename;

    @Expose
    private String lastname;
    private String nickname;

    @Expose
    private String company;

    @Expose
    @Type(type = "text")
    private String home;

    @Expose
    @Type(type = "text")
    private String mobile;

    @Expose
    @Type(type = "text")
    private String work;

    @Transient
    private String allPhones;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String postAddress;

    @Expose
    @Type(type = "text")
    private String email;

    @Expose
    @Type(type = "text")
    private String email2;

    @Expose
    @Type(type = "text")
    private String email3;

    @Transient
    private String allEmails;

    @Expose
    @Transient
    private File photo;

    @ManyToMany(fetch = FetchType.EAGER) // fix failed to lazily initialize a collection of role FetchType.EAGER or FetchType.LAZY
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id")) //id relates to contact id, group_id relates to group
    private Set<GroupData> groups = new HashSet<>();

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public int getId() {
        return id;
    }

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        return photo;
    }

    public Groups getGroups() {
        return new Groups(groups); //create a copy
    }

    public AddressData withId(int id) {
        this.id = id;
        return this;
    }

    public AddressData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public AddressData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public AddressData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public AddressData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public AddressData withCompany(String company) {
        this.company = company;
        return this;
    }

    public AddressData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public AddressData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public AddressData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public AddressData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public AddressData withPostAddress(String postAddress) {
        this.postAddress = postAddress;
        return this;
    }

    public AddressData withEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public AddressData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public AddressData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public AddressData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressData that = (AddressData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(home, that.home) && Objects.equals(mobile, that.mobile) && Objects.equals(work, that.work) && Objects.equals(email, that.email) && Objects.equals(email2, that.email2) && Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, home, mobile, work, email, email2, email3);
    }

    @Override
    public String toString() {
        return "AddressData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", home='" + home + '\'' +
                ", mobile='" + mobile + '\'' +
                ", work='" + work + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", email3='" + email3 + '\'' +
                '}';
    }

    public AddressData inGroup(GroupData group) {
        groups.add(group); //add contact to group by id
        return this;
    }

}