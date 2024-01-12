package com.glorious.framework.component;

import com.glorious.common.properties.GloriousStaticProperty;
import com.glorious.utils.utils.file.QFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class StaticComponent {

    @Autowired
    GloriousStaticProperty staticProperty;

    /**
    * 獲取 文件 訪問 路徑
    */
    public String getInterviewLink() {
        return staticProperty.getInterviewHeader();
    }

    // 工具 方法
    public String serStr(Object src) { return (src == null) ? "" : src.toString().trim(); }

    /**
    * 獲取資源 根路徑
    */
    public String resourceRootPath() throws FileNotFoundException { return ResourceUtils.getURL("classpath:").getPath(); }

    /**
    * 獲取 儲存 銷量排行 XLSX 文件的 路徑
    */
    public Path getPathHotSale(String fileName) throws FileNotFoundException {
        return Paths.get(resourceRootPath(), serStr(staticProperty.getXlsxFolder()), serStr(staticProperty.getXlsxFolderHotSale()), serStr(fileName));
    }
    public Path getPathHotSale() throws FileNotFoundException {
        return Paths.get(resourceRootPath(), serStr(staticProperty.getXlsxFolder()), serStr(staticProperty.getXlsxFolderHotSale()));
    }
    /**
     * 獲取 儲存 少量庫存 XLSX 文件的 路徑
    */
    public Path getPathLessInventory(String fileName) throws FileNotFoundException {
        return Paths.get(resourceRootPath(), serStr(staticProperty.getXlsxFolder()), serStr(staticProperty.getXlsxFolderLessInventory()), serStr(fileName));
    }
    public Path getPathLessInventory() throws FileNotFoundException {
        return Paths.get(resourceRootPath(), serStr(staticProperty.getXlsxFolder()), serStr(staticProperty.getXlsxFolderLessInventory()));
    }

    /**
     * 拿取文件
     */
    public String getFileHotSale() {
        try {
            String res = QFileUtil.getFileNameFirst( getPathHotSale(), staticProperty.getXlsxFileType() );
            return StringUtils.hasLength(res) ? staticProperty.getXlsxFolderHotSale() + "/" + res : res;
        } catch (Exception ignored) { } return null;
    }
    public String getFileLessInventory() {
        try {
            String res = QFileUtil.getFileNameFirst( getPathLessInventory(), staticProperty.getXlsxFileType());
            return StringUtils.hasLength(res) ? staticProperty.getXlsxFolderLessInventory() + "/" + res : res;
        } catch (Exception ignored) { } return null;
    }
}
