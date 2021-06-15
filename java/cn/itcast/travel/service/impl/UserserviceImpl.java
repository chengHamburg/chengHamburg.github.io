package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.Usersevice;

public class UserserviceImpl implements Usersevice {
    UserDao dao = new UserDaoImpl();
    @Override
    public boolean register1(User user) {
        boolean flag = dao.register(user);
        System.out.println("register启动");
        return flag;
    }
}
