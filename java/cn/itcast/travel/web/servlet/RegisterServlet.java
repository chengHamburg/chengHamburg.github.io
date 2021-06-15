package cn.itcast.travel.web.servlet;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.Usersevice;
import cn.itcast.travel.service.impl.UserserviceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        request.removeAttribute("CHECKCODE_SERVER");
        ResultInfo result = new ResultInfo();
        if (checkcode_server == null || check == null || !checkcode_server.equalsIgnoreCase(check)) {
            result.setFlag(false);
            result.setErrorMsg("验证码错误！");
            ObjectMapper om = new ObjectMapper();
            String s = om.writeValueAsString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
            return;
        } else {
            result.setFlag(true);
            ObjectMapper om = new ObjectMapper();
            String s = om.writeValueAsString(result);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(s);
        }
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Usersevice service = new UserserviceImpl();
        UserDao dao = new UserDaoImpl();
        boolean flag = service.register1(user);
        if (flag) {
            dao.save(user);
        }
    }
}
