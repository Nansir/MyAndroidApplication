package com.space.app.base.common;

import android.os.Environment;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.space.app.base.base.BaseApplication;
import com.space.app.base.util.LiteOrmDBUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 整个应用程序Application
 * Created by zhuyinan on 2016/8/29.
 */
public class MyApplication extends BaseApplication {

    private DisplayImageOptions.Builder mImageOptionsBuilder;

    private String errorPath = "/Base/ErrorLog/";

    private String DBName = "Base";

    @Override
    public void onCreate() {
        super.onCreate();

        LiteOrmDBUtil.createDb(getApplicationContext(), DBName);
        //日志收集
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
    }

    /**
     * 记录错误崩溃日志
     */
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            System.err.println("程序崩溃了");
            String info = null;
            ByteArrayOutputStream baos = null;
            PrintStream printStream = null;
            try {
                baos = new ByteArrayOutputStream();
                printStream = new PrintStream(baos);
                ex.printStackTrace(printStream);
                byte[] data = baos.toByteArray();
                info = new String(data);
                data = null;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (printStream != null) {
                        printStream.close();
                    }
                    if (baos != null) {
                        baos.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.err.println(info);
            writeLog(info, "ErrorLog.txt");
            System.exit(10);

        }
    };

    /**
     * 写入日志
     *
     * @param info
     */
    private void writeLog(String info, String fileName) {
        info = getCurrentDateString() + "\n" + info;
        File dir = new File(Environment.getExternalStorageDirectory() + errorPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(dir, fileName);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(info.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    private String getCurrentDateString() {
        String timeString;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date nowDate = new Date();
        timeString = sdf.format(nowDate);
        return timeString;
    }
}
