package team.cfc.lostandfound.common.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class SomeUtils {
    public static void saveImage(MultipartFile imageFile, String imageLocation) throws IOException {
        FileInputStream fileInputStream = (FileInputStream) imageFile.getInputStream();
        BufferedOutputStream bufferedOutputStream =
                                            new BufferedOutputStream(
                                                    new FileOutputStream(imageLocation)
                                            );
        byte[] bs = new byte[1024*1024*15];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bufferedOutputStream.write(bs, 0, len);
        }
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
