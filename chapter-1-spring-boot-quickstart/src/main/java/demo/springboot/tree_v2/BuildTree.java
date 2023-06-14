package demo.springboot.tree_v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.springboot.tree_v1.Tree;
import demo.springboot.tree_v1.ViewTree;
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
        List<demo.springboot.tree_v1.ViewTree> views = new ArrayList<>();
        mockData(views);
        List<demo.springboot.tree_v1.Tree> dataList = new ArrayList<>();
        for (demo.springboot.tree_v1.ViewTree viewTree : views) {
            demo.springboot.tree_v1.Tree tree = new demo.springboot.tree_v1.Tree();
            tree.setParentId(viewTree.getParentId());
            tree.setData(viewTree);
            dataList.add(tree);
        }
        demo.springboot.tree_v1.Tree root = new demo.springboot.tree_v1.Tree();
        root.setParentId(null);
        buildTree(dataList, root);
        //打印json
        String json = generateJson(root);
        System.out.println(json);
    }

    private static String generateJson(demo.springboot.tree_v1.Tree node) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(node.getChildren());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void mockData(List<demo.springboot.tree_v1.ViewTree> views) {
        demo.springboot.tree_v1.ViewTree.ViewTreeBuilder builder = demo.springboot.tree_v1.ViewTree.builder();
        String[] ids = {null, null, null, "1", "1", "3", "4"};
        for (int i = 0; i < 7; i++) {
            ViewTree value = builder.id(String.valueOf(i+1)).parentId(ids[i]).name(String.valueOf(i+1)).build();
            views.add(value);
        }
    }

    private static void buildTree(List<demo.springboot.tree_v1.Tree> dataList, demo.springboot.tree_v1.Tree node) {
        List<demo.springboot.tree_v1.Tree> child = findChildByParentId(dataList, node.getId());
        if (!CollectionUtils.isEmpty(child)) {
            node.setChildren(child);
            for (demo.springboot.tree_v1.Tree tree : child) {
                buildTree(dataList, tree);
            }
        }
    }

    private static List<demo.springboot.tree_v1.Tree> findChildByParentId(List<demo.springboot.tree_v1.Tree> dataList, String parentId) {
        List<demo.springboot.tree_v1.Tree> children = new ArrayList<>();
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
