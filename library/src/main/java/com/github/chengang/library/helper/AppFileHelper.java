package com.github.chengang.library.helper;

import android.os.Environment;
import android.text.TextUtils;

import com.github.chengang.library.BaseApplication;
import com.github.chengang.library.utils.FileUtil;

import java.io.File;



/**
 * Created by fengchengang on 2017/4/28.
 */

public class AppFileHelper {
    private static final String TAG = "AppFileHelper";

    private static final String ROOT_PATH = "com/github/albumCloud/";
    private static final String DATA_PATH = ROOT_PATH + ".data/";
    private static final String CACHE_PATH = ROOT_PATH + ".cache/";
    private static final String PIC_PATH = ROOT_PATH + "pic/";
    private static final String LOG_PATH = ROOT_PATH + ".log/";
    private static final String TEMP_PATH = ROOT_PATH + ".temp/";

    private static String storagePath;

    public static void initStoryPath() {
        if (TextUtils.isEmpty(storagePath)) {
            storagePath = FileUtil.getStoragePath(BaseApplication.getInstance(), FileUtil.hasSDCard());
            if (TextUtils.isEmpty(storagePath) || !isCanWriteNRead(storagePath)) {
                storagePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (TextUtils.isEmpty(storagePath) || !isCanWriteNRead(storagePath)) {
                    storagePath = BaseApplication.getInstance().getFilesDir().getAbsolutePath();
                }
            }
        }

        storagePath = FileUtil.checkFileSeparator(storagePath);

        File rootDir = new File(storagePath.concat(ROOT_PATH));
        checkAndMakeDir(rootDir);

        File dataDir = new File(storagePath.concat(DATA_PATH));
        checkAndMakeDir(dataDir);

        File cacheDir = new File(storagePath.concat(CACHE_PATH));
        checkAndMakeDir(cacheDir);

        File picDir = new File(storagePath.concat(PIC_PATH));
        checkAndMakeDir(picDir);

        File logDir = new File(storagePath.concat(LOG_PATH));
        checkAndMakeDir(logDir);

        File tempDir = new File(storagePath.concat(TEMP_PATH));
        checkAndMakeDir(tempDir);

    }

    private static void checkAndMakeDir(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static boolean isCanWriteNRead(String filePath) {
        File file = new File(filePath);
        return file.canWrite() && file.canRead();
    }

    public static String getAppStoragePath() {
        return storagePath;
    }

    public static String getAppDataPath() {
        return storagePath.concat(DATA_PATH);
    }


    public static String getAppCachePath() {
        return storagePath.concat(CACHE_PATH);
    }

    public static String getAppPicPath() {
        return storagePath.concat(PIC_PATH);
    }

    public static String getAppLogPath() {
        return storagePath.concat(LOG_PATH);
    }

    public static String getAppTempPath() {
        return storagePath.concat(TEMP_PATH);
    }

}
