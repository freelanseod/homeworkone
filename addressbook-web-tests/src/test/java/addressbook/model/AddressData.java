package addressbook.model;

import java.io.File;
import java.util.Objects;

public class AddressData {
    private int id = Integer.MAX_VALUE;
    private String firstname;
    private String middlename;
    private String lastname;
    private String nickname;
    private String company;
    private String home;
    private String mobile;
    private String work;
    private String allPhones;
    private String postAddress;
    private String email;
    private String email2;
    private String email3;
    private String allEmails;
    private File photo;

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
    public String toString() {
        return "AddressData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressData that = (AddressData) o;
        return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

}
