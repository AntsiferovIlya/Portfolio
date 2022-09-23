import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.RecursiveAction;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;

public class WebsiteNodeRecursiveAction extends RecursiveAction {
    private WebsiteNode node;

    public WebsiteNodeRecursiveAction(WebsiteNode node) {
        this.node = node;
    }

    @Override
    protected void compute() {
        try {
            sleep(500);
            Connection connection = Jsoup.connect(node.getUrl())
                    .timeout(10000);
            Document page = connection.get();
            Elements elements = page.select("body").select("a");
            for (Element a : elements) {
                String childUrl = a.absUrl("href");
                if (isCorrectUrl(childUrl)) {
                    childUrl = stripParams(childUrl);
                    node.addChild(new WebsiteNode(childUrl));
                }
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.toString());;
        }

        for (WebsiteNode child : node.getChildren()) {
            WebsiteNodeRecursiveAction task = new WebsiteNodeRecursiveAction(child);
            task.compute();
        }
    }

    private String stripParams(String url) {
        return url.replaceAll("\\?.+","");
    }

    private boolean isCorrectUrl(String url) {
        Pattern patternRoot = Pattern.compile("^" + node.getUrl());
        Pattern patternNotFile = Pattern.compile("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|pdf))$)");
        Pattern patternNotAnchor = Pattern.compile("#([\\w\\-]+)?$");

        return patternRoot.matcher(url).lookingAt()
                && !patternNotFile.matcher(url).find()
                && !patternNotAnchor.matcher(url).find();
    }
}