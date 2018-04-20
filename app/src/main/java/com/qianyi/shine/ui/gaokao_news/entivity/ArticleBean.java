package com.qianyi.shine.ui.gaokao_news.entivity;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17.
 */

public class ArticleBean {
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

    public AriticleData getData() {
        return data;
    }

    public void setData(AriticleData data) {
        this.data = data;
    }

    private String code;
    private String info;
    private AriticleData data;
    public class AriticleData {
        public AriticleInfo getInfo() {
            return Info;
        }

        public void setInfo(AriticleInfo info) {
            Info = info;
        }

        private AriticleInfo Info;
        public class AriticleInfo {
            public List<AriticleList> getArticleList() {
                return ArticleList;
            }

            public void setArticleList(List<AriticleList> articleList) {
                ArticleList = articleList;
            }

            private List<AriticleList> ArticleList;
            public class AriticleList{
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
