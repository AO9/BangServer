package com.gto.bang.vo;

import com.gto.bang.common.constant.Constant;
import com.gto.bang.common.string.StringUtil;
import com.gto.bang.util.CommonUtil;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenjialong on 16/6/26.
 */
public class UserVo implements RowMapper<UserVo>, Serializable {

    int id;
    String username;
    String password;
    String phone;
    String email;
    String city;
    String createtime;
    String education;
    String school;
    String signature;
    String gender;
    String level;
    String academy;
    String imei;

    String wechat;
    int vip;
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
        return INFO;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    String prompt;
    String info;

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    @Override
    public UserVo mapRow(ResultSet rs, int i) throws SQLException {
        UserVo userBo = new UserVo();
        userBo.setId(rs.getInt("id"));
        userBo.setVip(rs.getInt("vip"));
        userBo.setUsername(rs.getString("username"));
        userBo.setCity(rs.getString("city"));
        userBo.setEmail(rs.getString("email"));
        userBo.setPassword(rs.getString("password"));

        String phone = rs.getString("phone");
        StringBuffer sb = new StringBuffer();
        if (null != phone && phone.length() == 11) {
            sb.append(phone.substring(0, 3)).append("****").append(phone.substring(8, 11));
            userBo.setPhone(sb.toString());
        } else {
            userBo.setPhone("无");
        }

        userBo.setSchool(rs.getString("school"));
        userBo.setEducation(rs.getString("education"));
        userBo.setSignature(rs.getString("signature"));
        userBo.setLevel(CommonUtil.getLevel(rs.getInt("grade")));
        userBo.setAcademy(rs.getString("academy"));

        if (null != rs.getObject("gender")) {
            if (Constant.BOY == rs.getInt("gender")) {
                userBo.setGender(Constant.BOY_GENDER);
            } else if (Constant.GIRL == rs.getInt("gender")) {
                userBo.setGender(Constant.GIRL_GENDER);
            }
        }
        String createtime = rs.getTimestamp("createtime").toString();
        createtime = StringUtil.formatToDateTime(createtime);
        userBo.setCreatetime(createtime);
        return userBo;
    }


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
