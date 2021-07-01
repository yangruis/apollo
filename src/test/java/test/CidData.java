package test;



import jdk.nashorn.internal.objects.annotations.Getter;

import java.io.Serializable;

public class CidData implements Serializable {

    private String cid;
    private Long last_login_time;
    private String status;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Long getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Long last_login_time) {
        this.last_login_time = last_login_time;
    }
}
