package demo.springboot.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/5/27 19:25
 */
public class BuildTree {
    public static void main(String[] args) {
        List<ViewTree> views = new ArrayList<>();
        mockData(views);
        List<Tree> dataList = new ArrayList<>();
        for (ViewTree viewTree : views) {
            Tree tree = new Tree();
            tree.setParentId(viewTree.getParentId());
            tree.setData(viewTree);
            dataList.add(tree);
        }
        Tree root = new Tree();
        root.setParentId(null);
        buildTree(dataList, root);
        //打印json
        String json = generateJson(root);
        System.out.println(json);
    }

    private static String generateJson(Tree node) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(node.getChildren());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void mockData(List<ViewTree> views) {
        ViewTree.ViewTreeBuilder builder = ViewTree.builder();
        String[] ids = {null, null, null, "1", "1", "3", "4"};
        for (int i = 0; i < 7; i++) {
            ViewTree value = builder.id(String.valueOf(i+1)).parentId(ids[i]).name(String.valueOf(i+1)).build();
            views.add(value);
        }
    }

    private static void buildTree(List<Tree> dataList, Tree node) {
        List<Tree> child = findChildByParentId(dataList, node.getId());
        if (!CollectionUtils.isEmpty(child)) {
            node.setChildren(child);
            for (Tree tree : child) {
                buildTree(dataList, tree);
            }
        }
    }

    private static List<Tree> findChildByParentId(List<Tree> dataList, String parentId) {
        List<Tree> children = new ArrayList<>();
        for (Tree node : dataList) {
            if (node.getParentId() == null && parentId == null) {
                children.add(node);
                continue;
            }
            if (node.getParentId() != null && node.getParentId().equals(parentId)) {
                children.add(node);
            }
        }
        return children;
    }
}
