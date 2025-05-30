package gdg.daejuju.daehakjumak.image.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${aws.cloudfront.domain}")
    private String CLOUDFRONT_DOMAIN;

    // 이미지 업로드
    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        // 파일 업로드 (퍼블릭 권한 제거, CloudFront OAC 접근만 허용됨)
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
        );

        return getCloudFrontUrl(fileName);
    }

    private String getCloudFrontUrl(String fileName) {
        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        return CLOUDFRONT_DOMAIN + "/" + encodedFileName;
    }
}
