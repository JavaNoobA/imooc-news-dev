package com.imooc.api.controller.files;

import com.imooc.common.grace.result.GraceJSONResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pengfei.zhao
 * @date 2020/11/23 17:48
 */
@Api(value = "文件上传的Controller", tags = {"文件上传的Controller"})
@RequestMapping("/fs")
public interface FileUploadControllerApi {

    @PostMapping("/uploadFace")
    GraceJSONResult uploadFace(@RequestParam String userId, MultipartFile file) throws Exception;
}
