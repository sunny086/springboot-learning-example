package demo.springboot.tree;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/5/27 16:43
 */
@Data
public class Tree<T extends TreeMethod> {

    T data;

    private String id;

    private String parentId;

    List<Tree> children;

    public String getParentId() {
        return parentId;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public void setData(T data) {
        this.parentId = data.getParentId();
        this.id = data.getId();
        this.data = data;
    }

}
