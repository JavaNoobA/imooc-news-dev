package com.imooc.files.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengfei.zhao
 * @date 2020/11/23 17:22
 */
public interface UploadService {
    /**
     * 通过 fastdfs 上传文件
     * @param file 上传的文件
     * @param fileExt 文件后缀
     * @return 该文件全路径名称
     * @throws Exception ex
     */
    String uploadFdfs(MultipartFile file, String fileExt) throws Exception;
}
