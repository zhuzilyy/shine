package com.qianyi.shine.ui.home.bean;

import android.widget.ListView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class HomeBean {
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

    public HomeData getData() {
        return data;
    }

    public void setData(HomeData data) {
        this.data = data;
    }

    public String code;
    public String info;
    private HomeData data;

    public class HomeData{
        public HomeInfo getInfo() {
            return Info;
        }

        public void setInfo(HomeInfo info) {
            Info = info;
        }

        private HomeInfo Info;

        public class HomeInfo{

            public List<RecommendUniversity> getRecommendUniversityList() {
                return RecommendUniversityList;
            }

            public void setRecommendUniversityList(List<RecommendUniversity> recommendUniversityList) {
                RecommendUniversityList = recommendUniversityList;
            }

            public List<Article> getArticleList() {
                return ArticleList;
            }

            public void setArticleList(List<Article> articleList) {
                ArticleList = articleList;
            }

            private List<RecommendUniversity> RecommendUniversityList;
            private List<Article> ArticleList;
            public class RecommendUniversity{
                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getLogo() {
                    return logo;
                }

                public void setLogo(String logo) {
                    this.logo = logo;
                }

                public String getAttrtext() {
                    return attrtext;
                }

                public void setAttrtext(String attrtext) {
                    this.attrtext = attrtext;
                }

                private String id;
                private String name;
                private String logo;
                private String attrtext;
            }
            public class Article{
                private String id;
                private String title;
                private String hits;
                private String image;

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                private String create_time;
                private String weburl;

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
            }
        }

    }

}
