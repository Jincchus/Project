package kMarket.admin.request;

import java.util.ArrayList;
import java.util.List;

public class ArticleMultipleDeleteRequest {
    private List<Integer> articleIds = new ArrayList<>();

    public List<Integer> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }
}
