package demo.springboot.tree_v1;


import lombok.Builder;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/5/27 11:07
 */
@Data
@Builder
public class ViewTree implements TreeMethod {

    private String id;

    private String name;

    private String sourceId;

    private String parentId;

    private Boolean isFolder;


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
