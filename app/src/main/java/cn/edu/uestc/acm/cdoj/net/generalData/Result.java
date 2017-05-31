package cn.edu.uestc.acm.cdoj.net.generalData;

/**
 * Created by Grea on 2016/10/24.
 */

public class Result<Content> {

    public static final int DEFAULT = 0x10001000;
    public static final int NO_NET = 0x10001001;
    public static final int CONNECT_OVERTIME = 0x10001002;
    public static final int FALSE = 0x10001003;
    public static final int SUCCESS = 0x10001004;
    public static final int FINISH = 0x10001005;
    public static final int NO_DATA = 0x10001006;
    public static final int NO_RESPONSE = 0x10001007;

    public int status;
    public Content content;
    public Object extra;

    public Result() {

    }

    public Result(int status, Content content, Object extra) {
        this.status = status;
        this.content = content;
        this.extra = extra;
    }
}
