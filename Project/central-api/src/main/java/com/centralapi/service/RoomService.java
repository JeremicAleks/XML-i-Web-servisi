package com.centralapi.service;

import com.centralapi.domain.xml.xml_ftn.rooms.Image;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class RoomService {


    public void roomAddImage(Image request) throws IOException {

        Path path = Paths.get("src", "main", "resources", "static");
        ByteArrayInputStream bis = new ByteArrayInputStream(request.getImage());
        BufferedImage bImage2 = ImageIO.read(bis);
        ImageIO.write(bImage2, "jpg",
                new File(path.toString() + "/" + request.getRoomId() + "-" + request.getNameForImage()));

    }


}
