package demo.springboot.markdown_utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: xjs
 * @date: 2023/7/5 12:30
 */
public class SortTitle {

    private static final Pattern TITLE_PATTERN = compile("^(## .+)$", MULTILINE);

    private static String file1 = "C:\\Users\\BigStar\\Desktop\\study-markdown\\八股文整理\\1.Java基本问题.md";
    private static String file1Output = "C:\\Users\\BigStar\\Desktop\\study-markdown\\八股文整理\\Java基本问题2.md";

    public static void main(String[] args) {
        String inputFilePath = file1;
        String outputFilePath = file1Output;

        try {
            // 读取Markdown文件
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();

            // 给二级标题添加序号
            String numberedContent = addNumberingToSecondLevelTitles(content.toString());

            // 写回Markdown文件
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write(numberedContent);
            writer.close();

            System.out.println("Markdown文件处理完成！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String addNumberingToSecondLevelTitles(String content) {
        Matcher matcher = TITLE_PATTERN.matcher(content);

        List<String> titles = new ArrayList<>();

        // 提取所有的二级标题
        while (matcher.find()) {
            titles.add(matcher.group(1));
        }

        // 对标题进行排序
/*        Collections.sort(titles, (title1, title2) -> {
            int number1 = extractNumberFromTitle(title1);
            int number2 = extractNumberFromTitle(title2);
            return Integer.compare(number1, number2);
        });*/

        StringBuilder numberedContent = new StringBuilder();
        int counter = 1;

        // 替换原有标题为带有序号的标题
        for (String title : titles) {
            // 找到标题的字符串 存在数字 以.为分隔符
            int beginIndex = title.indexOf(".");
            if (beginIndex == -1) {
                beginIndex = 3;
            }
            String numberedTitle = "## " + counter + "." + title.substring(beginIndex + 1);
            content = content.replaceFirst(quote(title), numberedTitle);
            counter++;
        }

        return content;
    }

    private static int extractNumberFromTitle(String title) {
        // 使用正则表达式提取标题中的数字
        Pattern numberPattern = compile("\\d+");
        Matcher matcher = numberPattern.matcher(title);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

}
