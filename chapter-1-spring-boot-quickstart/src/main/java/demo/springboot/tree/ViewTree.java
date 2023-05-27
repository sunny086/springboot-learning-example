package demo.springboot.tree;


import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/5/27 11:07
 */
@Data
public class ViewTree implements TreeMethod {

    private String id;

    private String name;

    private String description;

    private String orgId;

    private String sourceId;

    private String script;

    private String type;

    private String model;

    private String config;

    private String parentId;

    private Boolean isFolder;

    private Double index;

    private Byte status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParentId() {
        return parentId;
    }

}
