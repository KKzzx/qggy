package cn.itcast.jk.domain;

/**
 * 初始化时自动设置有效期7000*1000毫秒
 *
 * @author Administrator
 */
public class AcToken {
    private String access_token;
    private long expires_in = 7000000;
    private long valid_time;

    public String getAccess_token() {
        return access_token;
    }

    /**
     * 有效期，默认为7200*1000毫秒（实际计算时是7000*1000毫秒）
     *
     * @param expires_in
     */
    public long getExpires_in() {
        return expires_in;
    }

    /**
     * 令牌有效时间，以毫秒为单位（获取时间加上有效期,valid_time<System.currentTimeMillis(),无效）
     *
     * @param valid_time
     */
    public long getValid_time() {
        return valid_time;
    }

    /**
     * 设置access_token，同时自动设置令牌有效时间，如需自由设置，调用其他setter
     *
     * @param access_token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
        this.valid_time = System.currentTimeMillis() + this.expires_in;
    }

    /**
     * 有效期，默认为7200秒（实际是7000秒）
     *
     * @param expires_in
     */
    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    /**
     * 令牌有效时间，以毫秒为单位（获取时间毫秒加上有效期毫秒，建议有效期7000*1000毫秒）
     *
     * @param valid_time
     */
    public void setValid_time(long valid_time) {
        this.valid_time = valid_time;
    }

    /**
     * 是否有效
     *
     * @param valid_time
     */
    public boolean isValid() {
        if (this.access_token != null) {
            return this.valid_time > System.currentTimeMillis() ? true : false;
        }
        return false;
    }

    @Override
    public String toString() {
        return "AcToken [access_token=" + access_token + ", expires_in="
                + expires_in + ", valid_time=" + isValid() + "]";
    }

}
