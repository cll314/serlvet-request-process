package com.dsdj.requestlearn.wrapper;


import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.*;

/**
 * @ClassName RequestParameterWrapper
 * @Description TODO
 * @Author ChenLiLin
 * @Date 2018/10/9 10:24
 * @Version 1.0
 **/
public class RequestParameterWrapper extends HttpServletRequestWrapper {

    private Map<String,String[]> parameterMap = new HashMap<>();
    private byte[] body;


    public RequestParameterWrapper(HttpServletRequest request) throws IOException{
        super(request);
        // 获取表单的全部参数键值对
        this.parameterMap.putAll(request.getParameterMap());
        // 获取body中的字节流
        body = toByteArray(request.getInputStream());
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    private byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    @Override
    public Map getParameterMap(){
        return this.parameterMap;
    }

    /**
     * 重写获取元素的方法
     * 从当前类中获取
     *
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name){
        String[] values = parameterMap.get(name);
        if (values==null || values.length==0){
            return null;
        }
        return values[0];
    }

    /**
     * 获取全部值
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(parameterMap.keySet());
    }

    /**
     * 添加或修改参数
     *
     * @param key
     * @param value
     */
    public void addParameters(String key, Object value){
        if (value!=null){
            if (value instanceof String[]){
                parameterMap.put(key,(String[]) value);
            }else if (value instanceof String){
                parameterMap.put(key, new String[]{(String) value});
            }else {
                parameterMap.put(key,new String[]{String.valueOf(value)});
            }
        }
    }


    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (body==null || body.length==0) {
            return null;
        }
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }
        };
    }

}