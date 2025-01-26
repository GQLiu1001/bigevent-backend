package com.example.utils;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MinioUtil {
    static final String BUCKET_NAME = "big-event";
    static final String ACCESS_KEY = "gMCkjT7HqsFBgD0Dw9Em";
    static final String SECRET_KEY = "fu9TtV06S4Mbs85N6LD20zscMJ2TnKm4l4QkLmc5";
    static final String ENDPOINT = "http://192.168.5.3:9000";

    public static String fileUpload(MultipartFile file,String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream inputStream = file.getInputStream();
        MinioClient minioClient = new MinioClient.Builder()
                .endpoint(ENDPOINT)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();

        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .stream(inputStream, file.getSize(), -1)
                        .build());
        String url =
                minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket(BUCKET_NAME)
                                .object(fileName)
                                .expiry(1, TimeUnit.DAYS)
                                .build());
        System.out.println(url);
        return url;
    }



}
