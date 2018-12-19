package cn.footman.util;

import cn.footman.server.LoginServer;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author footman77
 * @create 2018-12-15 10:11
 */
public class MyWsFactoryBean<T> implements FactoryBean<T> {

    private String url;
    private Class<T> t;


    public static <T> T getMyWs(String url, Class<T> t) {

        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(url);
        jaxWsProxyFactoryBean.setServiceClass(t);

        //加入安全协议
        if(t.getSimpleName().equals("TestServer")){
            Map<String, Object> map = new HashMap<>();
            map.put(WSHandlerConstants.ACTION,WSHandlerConstants.USERNAME_TOKEN);
            map.put(WSHandlerConstants.PASSWORD_TYPE,"PasswordText");
            map.put("user","username");
            map.put(WSHandlerConstants.PW_CALLBACK_CLASS,MyCallback.class.getName());


            WSS4JOutInterceptor wss4JOutInterceptor = new WSS4JOutInterceptor(map);
            jaxWsProxyFactoryBean.getOutInterceptors().add(wss4JOutInterceptor);
        }



        T bean = (T) jaxWsProxyFactoryBean.create();
        return bean;
    }


    @Override
    public T getObject() throws Exception {
        return getMyWs(url,this.t);
    }

    @Override
    public Class<?> getObjectType() {
        return this.t;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Class<T> getT() {
        return t;
    }

    public void setT(Class<T> t) {
        this.t = t;
    }
}
