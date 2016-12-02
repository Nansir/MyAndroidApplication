package com.sir.app.web.handler;

import com.yanzhenjie.andserver.AndServerRequestHandler;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Ping 接口
 * Created by zhuyinan on 2016/6/28.
 */
public class AndServerPingHandler implements AndServerRequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        response.setEntity(new StringEntity("Successfully received your request", "utf-8"));
    }
}
