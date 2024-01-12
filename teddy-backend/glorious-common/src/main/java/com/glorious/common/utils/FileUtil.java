package com.glorious.common.utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class FileUtil {

    static void close(FileOutputStream fos, OutputStreamWriter osw, BufferedWriter out) {
        try { fos.close(); } catch (Exception e) { }
        try { osw.close(); } catch (Exception e) { }
        try { out.close(); } catch (Exception e) { }
    }

    public static void writeStr(String content, String path) {

        File file = new File(path);

        boolean exist = file.getParentFile().exists();
        if (!exist) file.getParentFile().mkdirs();

        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter out = null;

        try {
            if (file.exists()) file.delete();
            file.createNewFile();

            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos);
            out = new BufferedWriter(osw);

            out.write(content);
            out.flush();
        }
        catch (Exception e) {
            // log.error("writeStr => " + e.toString());
        }
        finally { close(fos, osw, out); }
    }

}
