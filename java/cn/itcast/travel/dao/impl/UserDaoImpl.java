package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jt =  new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public void save(User user) {
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email) values(?,?,?,?,?,?,?)";

        jt.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail()
        );
        //2.执行sql

    }
    @Override
    public boolean register(User user) {
        User user1 = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ?";
            //2.执行sql
            user1 = jt.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), user.getUsername());
        } catch (Exception e) {
        }
        if (user1==null){
            return true;

        }else{
            System.out.println("dao启动");
            return  false;
        }
    }


}
