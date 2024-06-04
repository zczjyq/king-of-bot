package PTAJAVA5;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main7 {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in); // 创建 Scanner 对象用于读取输入
        StringBuilder stringBuilder = new StringBuilder(); // 创建 StringBuilder 对象，用于存储输入的多行文本
        String line;
        while (cin.hasNextLine()) { // 循环读取输入的每一行
            line = cin.nextLine(); // 获取当前行的内容
            if (line.isEmpty()) { // 如果当前行为空行，退出循环
                break;
            }
            stringBuilder.append(line); // 将当前行内容添加到 StringBuilder 中
        }
        String html = stringBuilder.toString(); // 将 StringBuilder 转换为 String，得到完整的 HTML 文本

        // 使用正则表达式提取 HTML 中的链接
        Pattern pattern = Pattern.compile("href=\"(.*?)\""); // 定义匹配 href 属性的正则表达式
        Matcher matcher = pattern.matcher(html); // 创建 Matcher 对象，用于在 HTML 文本中进行匹配
        while (matcher.find()) { // 循环查找匹配的链接
            String url = matcher.group(1); // 获取匹配到的链接内容（去除 href=" " 部分）
            System.out.println(url); // 输出匹配到的链接
        }
    }
}