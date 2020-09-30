package com.inconspicuousy.pattern.responsibility;

/*
 * 职责链模式（Chain Of Resposibility Pattern）：又叫责任链模式，为请求创建了一个接收者对象的链。
 *
 * 简单来说， 将客户端与处理客户端请求的接收者进行解耦， 防止出现客户端与请求接收者交互式处理问题。
 * 当客户端发出请求时，由请求的接收者链式处理完之后将结果返回给客户端。
 *
 * 举例来说， 当客户端发起请求时， 接收者A处理完交给接收者B处理， 接收者B处理完交给接收者C处理，以此类推，直到链式处理完请求将结果返回给客户端。
 * 在比如， Spring中队请求处理的各级拦截器， 一旦拦截器拦截到目标直接返回或者请求被处理完后返回。
 *
 * ======================
 *
 * 相关角色
 * 1、Handler： 抽象的处理请求的接口。 用来声明处理请求的方法，以及定义另一个Handler， 用来将当前请求转发给下一个处理器。
 * 2、ConcreteHandler： 具体的处理器，用来实现抽象Handler
 * 3、Request： 实际的请求， 处理就是处理当前的请求对象。（也可以将当前的请求抽象出来，然后处理子类请求）
 *
 * =========================
 *
 * 示例情景：
 * 当前我们模拟请求拦截器进行处理。
 *
 * */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
class Request {
    // 请求的地址
    private String url;
    // 请求的头部信息
    private Map<String, String> header = new HashMap<>();
}

// 抽象拦截
abstract class Handler {
    // 下一个处理请求的拦截器
    protected final Handler handler;

    public Handler(Handler handler) {
        this.handler = handler;
    }
    public abstract Boolean handler(Request request);
}

// URL拦截器
class UrlHandler extends Handler {
    public UrlHandler(Handler handler) {
        super(handler);
    }
    @Override
    public Boolean handler(Request request) {
        // 当前只处理路由中包含api的请求
        if (request.getUrl().contains("/api")) {
            return handler.handler(request);
        }
        return false;
    }
}

// 请求头处理器
class HeaderHandler extends Handler {
    public HeaderHandler(Handler handler) {
        super(handler);
    }
    @Override
    public Boolean handler(Request request) {
        // 请求头中必须含有auth字段
        return Objects.nonNull(request.getHeader()) && Objects.nonNull(request.getHeader().get("auth"));
    }
}

/**
 * 责任链模式代码测试
 * @author peng.yi
 */
public class ChainOfResponsibilityTest {
    public static void main(String[] args) {

        Request request = new Request("http://localhost/test", null);
        UrlHandler urlHandler = new UrlHandler(new HeaderHandler(null));
        Boolean handler = urlHandler.handler(request);
        System.out.println("handler = " + handler);

        request = new Request("http://localhost/api/test", Collections.singletonMap("auth", "123"));
        System.out.println(urlHandler.handler(request));
    }
}
