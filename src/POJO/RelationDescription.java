package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelationDescription {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("node_order")
    @Expose
    private Long nodeOrder;
    @SerializedName("objectId")
    @Expose
    private Long objectId;
    @SerializedName("lastUpdate")
    @Expose
    private Long lastUpdate;
    @SerializedName("space")
    @Expose
    private String space;
    @SerializedName("type")
    @Expose
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(Long nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}