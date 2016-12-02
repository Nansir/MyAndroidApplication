
package com.sir.app.web.handler;

import com.orhanobut.logger.Logger;
import com.yanzhenjie.andserver.AndServerRequestHandler;
import com.yanzhenjie.andserver.util.HttpRequestParser;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Map;

/**
 * Test 接口
 * Created by zhuyinan on 2016/6/28.
 */
public class AndServerTestHandler implements AndServerRequestHandler {

    @Override
    public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
        // 拿到客户端参数key-value。
        Map<String, String> params = HttpRequestParser.parse(request);

        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            stringBuilder.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        Logger.d("客户端提交的参数：" + stringBuilder.toString());

        StringEntity stringEntity = new StringEntity("请求已成功处理", "UTF-8");
        response.setEntity(stringEntity);
        // 如果要更新UI，这里用Handler或者广播发送过去。

    }
}
