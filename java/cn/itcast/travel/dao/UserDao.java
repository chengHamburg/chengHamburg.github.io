package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    boolean register(User user);
    void save(User user);
}
