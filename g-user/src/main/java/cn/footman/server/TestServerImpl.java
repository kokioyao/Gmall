package cn.footman.server;

/**
 * @author footman77
 * @create 2018-12-14 11:20
 */
public class TestServerImpl implements TestServer {
    @Override
    public String ping(String hello) {
        System.out.println("cxf接口调用" + hello);
        return "pong";
    }
}
