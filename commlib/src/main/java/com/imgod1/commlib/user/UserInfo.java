package com.imgod1.commlib.user;

/**
 * Created by Snow on 2017/3/4.
 * Description:
 */

public class UserInfo {


    /**
     * nick_name : %E7%89%B5%E6%89%8B%E5%B9%B8%E7%A6%8F
     * user_money : 0
     * log_url : https://img.qingym.cn/public/uploads/log_url/20190117/93bdebe410cedce549b052d66c279d3e.jpg
     * gender : 0
     * province : 0
     * city : 0
     * phone_tel : 13716101111
     * shop_key : 610937548
     * qr_url : https://img.qingym.cn/public/uploads/qr_url/20181217/c6086ceffd8a8bae5337c077ca533be0.jpg
     * name : 1111
     * idcard :
     * level : 1
     * token : R6iDQJauew2wgz5z
     * uid : 153
     * account : 13716101111
     */

    private String nick_name;
    private String user_money;
    private String log_url;
    private String gender;
    private String province;
    private String city;
    private String phone;
    private String shop_key;
    private String qr_url;
    private String name;
    private String idcard;
    /**
     * 1  D 普通会员
     * 2  C
     * 3  B
     * 4  A
     */
    private String level;
    private String token;
    private String uid;
    private String account;

    private PersonBanner banner;

    private String create_time;

    private String email;

    private String payword;
    private String wxbind;
    private String agent_order;
    private String my_agent;

    private String level_name;

    private String month_rebate;
    private String today_rebate;
    private String total_rebate;

    public String getMonth_rebate() {
        return month_rebate;
    }

    public void setMonth_rebate(String month_rebate) {
        this.month_rebate = month_rebate;
    }

    public String getToday_rebate() {
        return today_rebate;
    }

    public void setToday_rebate(String today_rebate) {
        this.today_rebate = today_rebate;
    }

    public String getTotal_rebate() {
        return total_rebate;
    }

    public void setTotal_rebate(String total_rebate) {
        this.total_rebate = total_rebate;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public String getAgent_order() {
        return agent_order;
    }

    public void setAgent_order(String agent_order) {
        this.agent_order = agent_order;
    }

    public String getMy_agent() {
        return my_agent;
    }

    public void setMy_agent(String my_agent) {
        this.my_agent = my_agent;
    }

    public String getWxbind() {
        return wxbind;
    }

    public void setWxbind(String wxbind) {
        this.wxbind = wxbind;
    }

    /**
     * 审核状态 （1-审核、0-未审核
     */
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayword() {
        return payword;
    }

    public void setPayword(String payword) {
        this.payword = payword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PersonBanner getBanner() {
        return banner;
    }

    public void setBanner(PersonBanner banner) {
        this.banner = banner;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_money() {
        return user_money;
    }

    public void setUser_money(String user_money) {
        this.user_money = user_money;
    }

    public String getLog_url() {
        return log_url;
    }

    public void setLog_url(String log_url) {
        this.log_url = log_url;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoto() {
        return phone;
    }

    public void setPhoto(String phone) {
        this.phone = phone;
    }

    public String getShop_key() {
        return shop_key;
    }

    public void setShop_key(String shop_key) {
        this.shop_key = shop_key;
    }

    public String getQr_url() {
        return qr_url;
    }

    public void setQr_url(String qr_url) {
        this.qr_url = qr_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
