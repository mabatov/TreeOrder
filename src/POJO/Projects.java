package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Projects {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lastUpdate")
    @Expose
    private Long lastUpdate;
    @SerializedName("deleted")
    @Expose
    private Object deleted;
    @SerializedName("pmId")
    @Expose
    private Object pmId;
    @SerializedName("reporterId")
    @Expose
    private Object reporterId;
    @SerializedName("deputyReporterId")
    @Expose
    private Object deputyReporterId;

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

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Object getDeleted() {
        return deleted;
    }

    public void setDeleted(Object deleted) {
        this.deleted = deleted;
    }

    public Object getPmId() {
        return pmId;
    }

    public void setPmId(Object pmId) {
        this.pmId = pmId;
    }

    public Object getReporterId() {
        return reporterId;
    }

    public void setReporterId(Object reporterId) {
        this.reporterId = reporterId;
    }

    public Object getDeputyReporterId() {
        return deputyReporterId;
    }

    public void setDeputyReporterId(Object deputyReporterId) {
        this.deputyReporterId = deputyReporterId;
    }

}

