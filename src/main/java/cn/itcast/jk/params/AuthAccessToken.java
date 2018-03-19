package cn.itcast.jk.params;

/**
 * 授权token的返回结果
 *
 * @author phil
 */
public class AuthAccessToken {

    private String access_token; // 范围授权token
    private int expires_in; // 过时时间
    private String refresh_token;// 刷新token
    private String openid; // 用户的openid
    private String scope; // 范围
    private String unionid; // 当且仅当该网站应用已获得该用户的userinfo授权时，才会出现该字段

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
