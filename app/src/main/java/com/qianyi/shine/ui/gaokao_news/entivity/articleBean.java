package com.qianyi.shine.ui.gaokao_news.entivity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class articleBean {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public articleData getData() {
        return data;
    }

    public void setData(articleData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private articleData data;

    public class articleData{
        public articleInfo getInfo() {
            return Info;
        }
        public void setInfo(articleInfo info) {
            Info = info;
        }

        private articleInfo Info;

        public class articleInfo{
            public List<articleList> getArticleList() {
                return ArticleList;
            }

            public void setArticleList(List<articleList> articleList) {
                ArticleList = articleList;
            }

            private List<articleList> ArticleList;

            public class articleList{
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getHits() {
                    return hits;
                }

                public void setHits(String hits) {
                    this.hits = hits;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getWeburl() {
                    return weburl;
                }

                public void setWeburl(String weburl) {
                    this.weburl = weburl;
                }

                private String id;
                private String title;
                private String image;
                private String hits;
                private String create_time;
                private String weburl;

            }


        }


    }

}
