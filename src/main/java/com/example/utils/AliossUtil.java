package com.example.utils;

import com.aliyun.oss.*;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.FileInputStream;
import java.io.InputStream;

public class AliossUtil {
    private static final String ENDPOINT = "https://oss-cn-shanghai.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAI5tB9uRWNQQ4rNAjLQmKk";
    private static final String ACCESS_KEY_SECRET = "7gVkXxgVwUlWDtWSqNgnlNKCkyMcs5";
    private static final String BUCKETNAME = "big-event-rabbittank";
    private static final String REGION = "cn-shanghai";
    public static String uploadFile(String objectName , InputStream in) throws Exception {
//     创建OSSClient实例。
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(ENDPOINT)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(REGION)
                .build();
    String url = "";
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, objectName, in);
            // 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);
            // 上传文件。

            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (
                OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (
                ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return url=
                "https://"+BUCKETNAME+"."+ENDPOINT.substring(ENDPOINT.lastIndexOf("/")+1)+"/"+objectName;
    }
}
// Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//    String endpoint = "https://oss-cn-shanghai.aliyuncs.com";
// 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
//        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//    String ACCESS_KEY_ID = "LTAI5tB9uRWNQQ4rNAjLQmKk";
//    String ACCESS_KEY_SECRET = "7gVkXxgVwUlWDtWSqNgnlNKCkyMcs5";
// 填写Bucket名称，例如examplebucket。
//    String bucketName = "big-event-rabbittank";
// 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//    String objectName = "001.jpg";
// 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
// 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
//    String filePath = "D:\\upload\\001.jpg";
// 填写Bucket所在地域。以华东1（杭州）为例，Region填写为cn-hangzhou。
//    String region = "cn-shanghai";
