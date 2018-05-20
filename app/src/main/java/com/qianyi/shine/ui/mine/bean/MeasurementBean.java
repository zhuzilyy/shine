package com.qianyi.shine.ui.mine.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MeasurementBean {
    public MeasurementData getData() {
        return data;
    }

    public void setData(MeasurementData data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private MeasurementData data;
    private String info;
    private String code;

    public class MeasurementData{
        public MeasurementInfo getInfo() {
            return Info;
        }

        public void setInfo(MeasurementInfo info) {
            Info = info;
        }

        private MeasurementInfo Info;
        public class MeasurementInfo{
            public List<MeasurementInfo.CollectionJobList> getCollectionJobList() {
                return CollectionJobList;
            }

            public void setCollectionJobList(List<MeasurementInfo.CollectionJobList> collectionJobList) {
                CollectionJobList = collectionJobList;
            }

            private List<CollectionJobList> CollectionJobList;

            public class CollectionJobList{
                private String id;
                private String member_id;
                private String type;
                private String intro;
                private String create_time;
                private String test_type;
                private String keystring;

                public String getKeystring() {
                    return keystring;
                }

                public void setKeystring(String keystring) {
                    this.keystring = keystring;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMember_id() {
                    return member_id;
                }

                public void setMember_id(String member_id) {
                    this.member_id = member_id;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getIntro() {
                    return intro;
                }

                public void setIntro(String intro) {
                    this.intro = intro;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }

                public String getTest_type() {
                    return test_type;
                }

                public void setTest_type(String test_type) {
                    this.test_type = test_type;
                }
            }

        }
    }


}
