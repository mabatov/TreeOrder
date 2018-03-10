package POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relations {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("node")
    @Expose
    private Long node;
    @SerializedName("parent")
    @Expose
    private Long parent;
    @SerializedName("level")
    @Expose
    private Long level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNode() {
        return node;
    }

    public void setNode(Long node) {
        this.node = node;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

}