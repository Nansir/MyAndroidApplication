package com.sir.app.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.sir.app.base.tools.ToolAlert;
import com.sir.app.base.util.NetWorkUtils;
import com.sir.app.web.service.MyAndServer;

public class MainActivity extends AppCompatActivity {

    private boolean isBind = false;
    private Intent serviceIntent;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg;
                if (isBind) {
                    msg = "Server已经启动";
                } else {
                    if (NetWorkUtils.isConnected(MainActivity.this)) {
                        doBusiness();

                        serviceIntent = new Intent(MainActivity.this, MyAndServer.class);
                        startService(serviceIntent);
                        isBind = true;
                        msg = "启动Server";
                    } else {
                        ToolAlert.showShort(MainActivity.this,"请检查网络");
                        return;
                    }
                }
                Snackbar.make(view, msg, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();


    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.tv_content);
    }


    private void doBusiness() {
        String ip = NetWorkUtils.getLocalIpAddress() + ":" + MyAndServer.PORT;
        StringBuffer msg = new StringBuffer();
        msg.append("http://" + ip + "/test\n");
        msg.append("http://" + ip + "/ping\n");
        msg.append("http://" + ip + "/upload\n");
        tvContent.setText(msg);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(serviceIntent);
    }
}
