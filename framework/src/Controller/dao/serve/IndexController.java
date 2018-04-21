package Controller.dao.serve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexController {

    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().println("hello world");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
