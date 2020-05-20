package top.seven.controller;

import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.seven.enums.ImageTypeEnum;
import top.seven.vo.ServerResponseVO;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@Api(tags = "文件管理")
@Slf4j
public class FileController {

    @Value("${image.save-path}")
    private String imageSavePath;

    @Value("${image.view-path}")
    private String imageViewPath;

    @PostMapping("/upload/image")
    @ApiOperation("上传图片")
    @ApiImplicitParam(name = "type", value = "图片类型", required = true, defaultValue = "3", paramType = "form")
    public ServerResponseVO uploadImage(@RequestParam("file") MultipartFile multipartFile,
                                        @RequestParam(value = "type", defaultValue = "3") Integer type) {
        if (multipartFile == null) {
            return ServerResponseVO.success("图片不能为空");
        }
        List<String> imageType = Lists.newArrayList("jpg","jpeg", "png", "bmp", "gif");
        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.indexOf('.');
        String suffix = originalFilename.substring(index + 1);
        if (imageType.contains(suffix)) {
            String path = ImageTypeEnum.getPathByType(type);
            String fileName = System.currentTimeMillis() + "." + suffix;
            String destPath = imageSavePath + File.separator + path + File.separator + fileName;
            File file = new File(destPath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                log.error("图片上传失败，Message: {}", e.getMessage());
                return ServerResponseVO.error("图片上传失败");
            }
            String viewPath = imageViewPath + "/" + path + "/" + fileName;
            return ServerResponseVO.success(viewPath);
        } else {
            return ServerResponseVO.error("图片格式不合法");
        }
    }
}
