import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "talkServlet",value = "/talkServlet")
public class talkServlet extends HttpServlet {



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        client client = new client ();
        robet rob = new robet ();
        String massage2;
        String massage3;

        try {
            client.setNotification (request.getParameter ("Notification"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String text = client.getNotification ();
        System.out.println ("-------------------------"+text+"---------------------------------");
        String message = "{\n" + "\t\"reqType\":\"0\",\n" + "    \"perception\": {\n" + "        \"inputText\": {\n" + "            \"text\": \"" + text + "\"\n" + "        },\n" + "        \"inputImage\": {\n" + "            \"url\": \"\"\n" + "        },\n" + "        \"selfInfo\": {\n" + "            \"location\": {\n" + "                \"city\": \"\",\n" + "                \"province\": \"\",\n" + "                \"street\": \"\"\n" + "            }\n" + "        }\n" + "    },\n" + "    \"userInfo\": {\n" + "        \"apiKey\": \"3979fb3bb0c84d279bc90946311356c3\",\n" + "        \"userId\": \"\"\n" + "    }\n" + "}";
        System.out.println (message);
        JSONObject jsonObject =JSONObject.fromObject (message);
        Map map = jsonObject;

        try{
        if(client!=null){
            massage2 = rob.httpRequestToString("http://openapi.tuling123.com/openapi/api/v2","post",map);
            JSONObject jo =  JSONObject.fromObject (massage2);
            massage3 = jo.getString ("text");
            request.setAttribute("msg", massage3);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else request.setAttribute ("msg2","你好像没有输入你的话哦@_@");
    }catch (Exception e){
            e.printStackTrace ();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
