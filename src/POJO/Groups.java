package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groups {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("space")
    @Expose
    private String space;
    @SerializedName("lastUpdate")
    @Expose
    private Long lastUpdate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("deleted")
    @Expose
    private Object deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

}