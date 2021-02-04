package team.cfc.lostandfound.common.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

public class SomeUtils {
    public static void saveImage(MultipartFile imageFile, String imageLocation) throws IOException {
        imageFile.transferTo(Paths.get(imageLocation));
//        FileInputStream fileInputStream = (FileInputStream) imageFile.getInputStream();
//        FileWriter fileWriter = new FileWriter(imageLocation);
//        fileWriter.write();
//        Thumbnails.of(fileInputStream).size(720,1080).outputFormat("jpg").toFile(imageLocation);
    }
}
