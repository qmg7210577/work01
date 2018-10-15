package com.qunar.qmg.demo01;

import com.google.common.base.Objects;

/**
 * Created by menggao.qi on 2018/10/15.
 */
public class UserInfo implements Comparable<UserInfo> {
    private String level;
    private String username;
    private String userId;

    public UserInfo(String level, String username, String userId) {
        this.level = level;
        this.username = username;
        this.userId = userId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(UserInfo o) {
        return this.getUsername().compareTo(o.getUsername());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserInfo userInfo = (UserInfo) o;
        return Objects.equal(level, userInfo.level) &&
                Objects.equal(username, userInfo.username) &&
                Objects.equal(userId, userInfo.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(level, username, userId);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "level='" + level + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
