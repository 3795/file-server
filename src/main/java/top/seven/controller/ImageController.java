package top.seven.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

@RestController
@RequestMapping("/image")
@Api(tags = "查看图片")
public class ImageController {

    @Value("${image.save-path}")
    private String imageSavePath;

    @GetMapping("/{name:.+}")
    @ApiOperation("查看图片")
    @ApiImplicitParam(name = "name", value = "图片名称", required = true, paramType = "query")
    public void view(@PathVariable String name,
                     HttpServletResponse response) throws IOException {
        if (Strings.isBlank(name)) {
            return;
        }
        response.setContentType("image/jpeg");
        String imgPath = URLDecoder.decode(imageSavePath + File.separator + name, "utf-8");
        File file = new File(imgPath);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                os.write(b);
            }
            in.close();
            os.flush();
            os.close();
        }
    }
}
