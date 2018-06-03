import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class robet {

    public static String httpRequestToString(String url, String requestMethod,
                                             Map<String, String> params){

        String result = null;
        try {
            InputStream is = httpRequestToStream(url, requestMethod, params);
            byte[] b = new byte[is.available()];
            is.read(b);
            result = new String(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static InputStream httpRequestToStream(String url, String requestMethod,
                                                  Map<String, String> params){

        InputStream is = null;
        try {
            String parameters = "";
            boolean hasParams = false;

            /*for(String key : params.keySet()){
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                parameters += key +"="+ value +"&";
                hasParams = true;
            }*/
            for(String key : params.keySet()){
                /*for (int i = params.get(key).length();--i>=0;){
                    if (Character.isDigit(params.get(key).charAt(i))){
                        params.get(key).toString ();
                    }
                }*/
                String value = URLEncoder.encode(params.get(key), "UTF-8");
                parameters += key +"="+ value +"&";
                hasParams = true;
            }
            if(hasParams){
                parameters = parameters.substring(0, parameters.length()-1);
            }


            boolean isGet = "get".equalsIgnoreCase(requestMethod);

            boolean isPost = "post".equalsIgnoreCase(requestMethod);
            if(isGet){
                url += "?"+ parameters;
            }

            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();

            conn.setRequestProperty("Content-Type", "application/octet-stream");
            //设置连接超时时间
            conn.setConnectTimeout(50000);
            //设置读取返回内容超时时间
            conn.setReadTimeout(50000);
            //设置向HttpURLConnection对象中输出，因为post方式将请求参数放在http正文内，因此需要设置为ture，默认false
            if(isPost){
                conn.setDoOutput(true);
            }
            //设置从HttpURLConnection对象读入，默认为true
            conn.setDoInput(true);
            if(isPost){
                conn.setUseCaches(false);
            }
            conn.setRequestMethod(requestMethod);

            //post方式需要将传递的参数输出到conn对象中
            if(isPost){
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.writeBytes(parameters);
                dos.flush();
                dos.close();
            }

            //从HttpURLConnection对象中读取响应的消息
            //执行该语句时才正式发起请求
            is = conn.getInputStream();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }
}

