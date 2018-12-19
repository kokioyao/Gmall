package cn.footman.util;



import org.apache.wss4j.common.ext.WSPasswordCallback;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * @author footman77
 * @create 2018-12-15 14:59
 */
public class MyCallback implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        //配置username/password
        WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];

        wsc.setIdentifier("cxf");
        String password = "123";

        wsc.setPassword(password);
    }

}
