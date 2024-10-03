package com.cc.idle.framework.common.util.compress;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class CompressUtil {
    //压缩到1M
    static long COMPRESS_SIZE_LIMIT = 1024 * 1024;


    public static void compressToJpg(MultipartFile oriFile, OutputStream targetFile) throws IOException {
        //临时保存文件
        File tempFile = File.createTempFile("temp", "." + "jpg", null);
        tempFile.deleteOnExit();
        oriFile.transferTo(tempFile);
        long size = oriFile.getSize();
        float quality = 1 / ((float) size / COMPRESS_SIZE_LIMIT);
        if (quality > 1) {
            quality = 1.0f;
        }
        if (quality <= 0.1f) {
            quality = 0.1f;
        }
        Thumbnails.of(tempFile)
                .scale(1f)//缩放比例
                .outputQuality(quality)//压缩倍率
                .outputFormat("jpg")
                .toOutputStream(targetFile);
    }
}
