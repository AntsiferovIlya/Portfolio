import java.util.concurrent.CopyOnWriteArrayList;

public class WebsiteNode {
    private volatile WebsiteNode parent;
    private volatile int depth;
    private String url;
    private volatile CopyOnWriteArrayList<WebsiteNode> children;

    public WebsiteNode(String url) {
        depth = 0;
        this.url = url;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.calculateDepth();
        return result;
    }

    public synchronized void addChild(WebsiteNode element) {
        WebsiteNode root = getRootElement();
        if(!root.contains(element.getUrl())) {
            element.setParent(this);
            children.add(element);
        }
    }

    private boolean contains(String url) {
        if (this.url.equals(url)) {
            return true;
        }
        for (WebsiteNode child : children) {
            if(child.contains(url))
                return true;
        }

        return false;
    }

    public String getUrl() {
        return url;
    }

    private void setParent(WebsiteNode sitemapNode) {
        synchronized (this) {
            this.parent = sitemapNode;
            this.depth = calculateDepth();
        }
    }

    public WebsiteNode getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }

    public CopyOnWriteArrayList<WebsiteNode> getChildren() {
        return children;
    }
}