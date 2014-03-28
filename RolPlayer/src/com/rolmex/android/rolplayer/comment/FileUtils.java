
package com.rolmex.android.rolplayer.comment;


import com.rolmex.android.rolplayer.utils.StringUtils;

import java.io.File;

public class FileUtils {
    /**
     * 根据文件绝对路径获取文件名
     * 
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath))
            return "";
        return filePath.substring(filePath.lastIndexOf(File.separator) + 1);
    }

}
