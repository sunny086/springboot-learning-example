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
            return objectMapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void mockData(List<ViewTree> views) {
        ViewTree value1 = new ViewTree();
        value1.setId("1");
        value1.setParentId(null);
        value1.setName("1");
        views.add(value1);

        ViewTree value2 = new ViewTree();
        value2.setId("2");
        value2.setParentId(null);
        value2.setName("1");
        views.add(value2);

        ViewTree value3 = new ViewTree();
        value3.setId("3");
        value3.setParentId(null);
        value3.setName("1");
        views.add(value3);

        ViewTree value4 = new ViewTree();
        value4.setId("4");
        value4.setParentId(null);
        value4.setName("1");
        views.add(value4);

        ViewTree value5 = new ViewTree();
        value5.setId("5");
        value5.setParentId(value1.getId());
        value5.setName("1");
        views.add(value5);

        ViewTree value6 = new ViewTree();
        value6.setId("6");
        value6.setParentId(value1.getId());
        value6.setName("1");
        views.add(value6);

        ViewTree value7 = new ViewTree();
        value7.setId("7");
        value7.setName("1");
        value7.setParentId(value5.getId());
        views.add(value7);

        ViewTree value8 = new ViewTree();
        value8.setId("8");
        value8.setName("1");
        value8.setParentId(value4.getId());
        views.add(value8);
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
