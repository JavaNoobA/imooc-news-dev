package com.imooc.files.service.impl;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.imooc.files.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author pengfei.zhao
 * @date 2020/11/23 17:22
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public String uploadFdfs(MultipartFile file, String fileExt) throws Exception {
        try (InputStream inputStream = file.getInputStream()){
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), fileExt, null);
            return storePath.getFullPath();
        }
    }
}
