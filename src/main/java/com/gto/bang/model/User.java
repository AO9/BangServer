package com.gto.bang.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String imei;

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    private String androidId;


    private String wechat;

    //向下兼容
    private String username;

    private String password;

    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    private String email;

    private String city;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    //向下兼容
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String education;

    private String school;

    private String signature;

    private Byte gender;

    private String level;

    private String academy;

    private Byte vip;

    private Integer grade;

    private Date lastLoginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy == null ? null : academy.trim();
    }

    public Byte getVip() {
        return vip;
    }

    public void setVip(Byte vip) {
        this.vip = vip;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }


    public static final String INSTRUCTION = "发布一篇高质量经验 +5分\n 贡献一个好回答 +2分;\n 0-25分 论文新人;\n 25-75分 论文达人;\n 75-300 论文长老;\n 300-800 论文副首领;\n 800-1000 论文首领;\n 1000+ 论文资深达人";
    public static final String INFO = "[更新说明]\n" +
            "本站禁止用户发布广告性质的内容,提醒各位用户,请勿相信任何涉及金钱交易的言行,服务等[2017-11-25]" +
            "[免责声明]\n" +
            "本应用为知识分享型开放社区,所有功能[免费].请勿相信任何涉及金钱交易的言行,服务等.\n" +
            "1.用户通过[论文帮]发布的任何信息、传递的任何观点不代表本平台之立场.\n " +
            "2.论文帮亦不对其完整性、真实性、准确性或可靠性负责.\n" +
            "3.用户对于可能会接触到的非法的、非道德的、错误的或存在其他失宜之处的信息，及被错误归类或是带有欺骗性的发布内容，应自行做出判断.";

    public String getLevel_instruction() {
        return INSTRUCTION;
    }

    public void setLevel_instruction(String level_instruction) {
        this.level_instruction = level_instruction;
    }

    String level_instruction;

    public String getPrompt() {
        return "抱歉, 该服务暂未开通";
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    String prompt;
    String info;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

}