package cn.footman.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author footman77
 * @create 2018-12-14 11:19
 */
@WebService
public interface TestServer {
    @WebMethod
    public String ping(String hello);
}
