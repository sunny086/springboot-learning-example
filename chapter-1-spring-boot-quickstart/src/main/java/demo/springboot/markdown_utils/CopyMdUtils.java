package demo.springboot.markdown_utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/6/14 15:22
 */
public class CopyMdUtils {
    public static void main(String[] args) {
        String originPath = "C:\\Users\\BigStar\\Desktop\\study-markdown\\日常学习笔记汇总\\assert\\";
        String targetPath = "C:\\Users\\BigStar\\Desktop\\study-markdown\\MySQL\\assert\\";
        String targetFilePath = "C:\\Users\\BigStar\\Desktop\\study-markdown\\MySQL\\MySQL笔记.md";
        //读取md文件

        try {
            // 读取 targetFilePath 文件内容
            String content = new String(Files.readAllBytes(Paths.get(targetFilePath)));

            // 使用正则表达式匹配图片名称
            Pattern pattern = Pattern.compile("assert/[^)\"]+");
            Matcher matcher = pattern.matcher(content);

            // 遍历匹配到的图片名称
            while (matcher.find()) {
                String imageName = matcher.group(1);
                Path originFile = Paths.get(originPath + imageName);
                Path targetFile = Paths.get(targetPath + imageName);

                // 如果目标位置已存在相同名称的图片，则跳过移动操作
                if (Files.exists(targetFile)) {
                    System.out.println("图片已存在，跳过移动操作：" + imageName);
                    continue;
                }
                // 删除原有位置的图片并移动到目标位置
                Files.move(originFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("移动图片：" + imageName);
            }
            System.out.println("图片移动完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
