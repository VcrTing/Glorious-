package com.glorious.utils.utils.file;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class QFileUtil {

    final static String SYMBOL_CONN = "_";
    final static String SYMBOL_TYPE = ".";
    final static String FILE_DAY_FMT = "yyyyMMdd";
    final static String FILE_TIME_FMT = "yyyyMMdd_hhmm";

    /**
    * 獲取文件夾內的 文件
    */
    public static File[] getFiles(Path path) {
        File folder = new File(getPath(path));
        return folder.listFiles();
    }
    public static File getFileFirst(Path path) {
        File[] files = getFiles(path);
        return (files != null && files.length > 0) ? files[0] : null;
    }
    public static String getFileNameFirst(Path path) {
        File file = getFileFirst(path);
        return file != null ? file.getName() : "";
    }
    public static File getFileFirst(Path path, String typeName) {
        File[] files = getFiles(path);
        if (files == null) return null;
        String name = null;
        for (File f: files) {
            if (f != null) {
                name = f.getName();
                if (name.endsWith(typeName.toLowerCase())) return f;
            }
        }
        return null;
    }
    public static String getFileNameFirst(Path path, String typeName) {
        File file = getFileFirst(path, typeName);
        return file != null ? file.getName() : "";
    }

    /**
    * 關閉
    */
    public static void close(OutputStream os) {
        try {
            if (os != null) os.close();
        } catch (Exception ignored) { } }


    /**
    * 清空文件內的所有文件
    */
    public static void clearPath(Path path) {
        File folder = new File(getPath(path));
        if (folder.exists()) {
            clearFolder(folder);
        } else {
            folder.mkdirs();
        }
    }
    public static void clearFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    clearFolder(file);
                } else {
                    file.delete();
                }
            }
        }
    }


    /**
    * 獲取 地址值
    */
    public static String getPath(Path path) {
        return path.toAbsolutePath().normalize().toString();
    }

    /**
    * 生成 名稱
    */
    public static String genName(String name, String suffix, String fileTyped) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (suffix != null) sb.append(SYMBOL_CONN).append(suffix);
        return sb.append(SYMBOL_TYPE).append(fileTyped).toString();
    }

    public static String genNameTime(String name, String fileTyped) {
        String ss = new SimpleDateFormat(FILE_TIME_FMT).format(new Date());
        return genName(name, ss, fileTyped);
    }
    public static String genNameDay(String name, String fileTyped) {
        String ss = new SimpleDateFormat(FILE_DAY_FMT).format(new Date());
        return genName(name, ss, fileTyped);
    }

}
