package com.imooc.files.controller;

import com.imooc.api.BaseController;
import com.imooc.api.controller.files.FileUploadControllerApi;
import com.imooc.common.grace.result.GraceJSONResult;
import com.imooc.common.grace.result.ResponseStatusEnum;
import com.imooc.files.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengfei.zhao
 * @date 2020/11/23 17:54
 */
public class FileUploadController extends BaseController implements FileUploadControllerApi {
    @Autowired
    private UploadService uploadService;

    @Override
    public GraceJSONResult uploadFace(String userId, MultipartFile file) throws Exception {
        if (file == null) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_UPLOAD_NULL_ERROR);
        }
        String fileName = file.getName();
        String[] fileExts = fileName.split("\\.");
        String fileExt = fileExts[fileExts.length - 1];
        if (!"png".equalsIgnoreCase(fileExt) && !"jpg".equalsIgnoreCase(fileExt)
                && !"gif".equalsIgnoreCase(fileExt)) {
            return GraceJSONResult.errorCustom(ResponseStatusEnum.FILE_FORMATTER_FAILD);
        }
        String filePath = uploadService.uploadFdfs(file, fileExt);
        return GraceJSONResult.ok(filePath);
    }
}
