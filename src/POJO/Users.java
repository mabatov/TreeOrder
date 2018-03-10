
package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("rusName")
    @Expose
    private String rusName;
    @SerializedName("rusSurname")
    @Expose
    private String rusSurname;
    @SerializedName("rusPatronymic")
    @Expose
    private String rusPatronymic;
    @SerializedName("engName")
    @Expose
    private String engName;
    @SerializedName("engSurname")
    @Expose
    private String engSurname;
    @SerializedName("intraId")
    @Expose
    private Object intraId;
    @SerializedName("phones")
    @Expose
    private Object phones;
    @SerializedName("city")
    @Expose
    private Object city;
    @SerializedName("position")
    @Expose
    private Object position;
    @SerializedName("department")
    @Expose
    private Object department;
    @SerializedName("imageId")
    @Expose
    private Object imageId;
    @SerializedName("lastUpdate")
    @Expose
    private Long lastUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getRusName() {
        return rusName;
    }

    public void setRusName(String rusName) {
        this.rusName = rusName;
    }

    public String getRusSurname() {
        return rusSurname;
    }

    public void setRusSurname(String rusSurname) {
        this.rusSurname = rusSurname;
    }

    public String getRusPatronymic() {
        return rusPatronymic;
    }

    public void setRusPatronymic(String rusPatronymic) {
        this.rusPatronymic = rusPatronymic;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getEngSurname() {
        return engSurname;
    }

    public void setEngSurname(String engSurname) {
        this.engSurname = engSurname;
    }

    public Object getIntraId() {
        return intraId;
    }

    public void setIntraId(Object intraId) {
        this.intraId = intraId;
    }

    public Object getPhones() {
        return phones;
    }

    public void setPhones(Object phones) {
        this.phones = phones;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getPosition() {
        return position;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public Object getDepartment() {
        return department;
    }

    public void setDepartment(Object department) {
        this.department = department;
    }

    public Object getImageId() {
        return imageId;
    }

    public void setImageId(Object imageId) {
        this.imageId = imageId;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}