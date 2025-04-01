package gdg.daejuju.daehakjumak.image.ui;

import gdg.daejuju.daehakjumak.common.domain.exception.ErrorCode;
import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.image.application.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/image")
public class ImageTestController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/upload")
    public Response<String> uploadImage(@RequestPart("image")MultipartFile file){
        try{
            String imageUrl = imageUploadService.uploadImage(file);
            return Response.ok(imageUrl);
        } catch (IOException e) {
            return Response.error(ErrorCode.INTERNAL_ERROR);
        }
    }
}
