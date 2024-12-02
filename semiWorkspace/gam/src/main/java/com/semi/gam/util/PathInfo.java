package com.semi.gam.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PathInfo {
    private final String staticPath =
            "C:" + File.separator
                    + "JAVA" + File.separator
                    + "springbootWorkspace" + File.separator
                    + "kh" + File.separator
                    + "src" + File.separator
                    + "main" + File.separator
                    + "resources" + File.separator
                    + "static" + File.separator
            ;

    public String getStorePath() {
        String path = staticPath
                + "img" + File.separator
                + "store" + File.separator
                + "attachment" + File.separator;
        return path;
    }

    public String getBoardAttachmentPath() {
        String path = staticPath
                + "img" + File.separator
                + "board" + File.separator
                + "attachment" + File.separator;
        return path;
    }

    public String getNoticeAttachmentPath() {
        String path = staticPath
                + "img" + File.separator
                + "notice" + File.separator
                + "attachment" + File.separator;
        return path;
    }
}
