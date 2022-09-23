import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static String ROOT_SITE = "https://skillbox.ru/";
    public static void main(String[] args) throws IOException {
        WebsiteNode websiteNode = new WebsiteNode(ROOT_SITE);
        new ForkJoinPool().invoke(new WebsiteNodeRecursiveAction(websiteNode));
//        WebsiteNodeRecursiveAction websiteNodeRecursiveAction = new WebsiteNodeRecursiveAction(websiteNode);
//        websiteNodeRecursiveAction.fork();
//        websiteNodeRecursiveAction.join();


        FileOutputStream stream = new FileOutputStream("D:\\Ycheba\\SkillBox\\Module17.21\\Multithreading\\ForkJoinPool\\src\\main\\resources\\website.txt");
        String result = createSitemapString(websiteNode, 0);
        stream.write(result.getBytes());
        stream.flush();
        stream.close();
    }

    public static String createSitemapString(WebsiteNode node, int depth) {
        String tabs = String.join("", Collections.nCopies(depth, "\t"));
        StringBuilder result = new StringBuilder(tabs + node.getUrl());
        node.getChildren().forEach(child -> {
            result.append("\n").append(createSitemapString(child, depth + 1));
        });
        return result.toString();
    }
}