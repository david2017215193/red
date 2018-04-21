package Controller.dao.Servlet;

import Controller.dao.domain.Table;
import Controller.dao.serve.ControllerLoader;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RequestUtil extends HttpServlet {

    private static Enumeration<URL> resources;

    public static <T>T getObject(HttpServletRequest request, Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException {
//得到两个对象-------------------------------------------------------------------------------
        resources = ControllerLoader.class.getClassLoader ().getResources ("clazz");
    while (resources.hasMoreElements ()) {
        URL resource = resources.nextElement ();
    }
    Map<String, Object> urlControllersMap = new HashMap<> ();
    Set<Class<?>> classSet = new HashSet<> ();

    String className = null;
    for (Class claz : classSet) {
        className = claz.getSimpleName ();
        String controllerName = className.substring (0, className.lastIndexOf ("Controller")).toLowerCase ();
        urlControllersMap.put (controllerName, claz.newInstance ());
    }
    //遍历第二个对象的属性----------------------------------------------------------------------------------
    //得到其属性的名字---------------------------------------------------------------
    Enumeration<String> e = request.getParameterNames();
    String parameterName = null;
        while(e.hasMoreElements())
    {
        parameterName = e.nextElement();
        Object parameterValue = request.getParameter (parameterName);

    //在request里看有没有这些名字--------------------------------------------------------
    //对request里的参数进行类型转换---------------------------------------------------------
        if(parameterName != null&&clazz.getTypeName ().equals ("String")) {
            if (parameterName.equals (className)) {
                if(clazz.getTypeName ().equals ("integer")){
                    parameterValue = Integer.parseInt(parameterName);
                }
                if(clazz.getTypeName ().equals ("double")){
                    parameterValue = Double.parseDouble(parameterName);
                }
            }
        }
    }
    //将得到的request参数赋值到Object对象属性中-----------------------------------------
        Map<String, Object> object = new HashMap<> ();
        object.put("Value",parameterName);
    //返回对象------------------------------------------------------------------------
    return (T) object;
}

    public static void main(String[] args) throws IllegalAccessException, IOException, InstantiationException {
        HttpServletRequest request = null;
        Table tab = RequestUtil.getObject(request,Table.class);
    }

    /*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Table tab = new Table ();

        String Mon = request.getParameter ("Mon");
        String Tue = request.getParameter ("Tue");
        String Wed = request.getParameter ("Wed");
        String Thu = request.getParameter ("Thu");
        String Fri = request.getParameter ("Fri");
        String Sat = request.getParameter ("Sat");
        String Sun = request.getParameter ("Sun");

        tab.setMon (Mon);
        tab.setTue (Tue);
        tab.setWed (Wed);
        tab.setThu (Thu);
        tab.setFri (Fri);
        tab.setSat (Sat);
        tab.setSun (Sun);

        *//*try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e1) {
            e1.printStackTrace();
        }*//*

//        UserService us = new UserServiceImpl();

        *//*User u;
        try {
            u = us.login(user);

            //分发转向
            if(u!=null){
                request.getSession().setAttribute("u", user);//如果登录成功，就把user对象放到session对象 中
                request.getRequestDispatcher("/main.jsp").forward(request, response);

            }else{//登录失败跳回登陆页面
                request.setAttribute("msg", "用户名或密码不正确！");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*//*


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }*/
}

