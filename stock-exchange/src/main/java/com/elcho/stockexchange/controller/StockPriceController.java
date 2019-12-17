package com.elcho.stockexchange.controller;

import com.elcho.stockexchange.model.PriceUploadSummary;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping( value = "/stockexchange/stockPrice")
public class StockPriceController {
    private static final Logger log = Logger.getLogger(String.valueOf(UserController.class));
    private final String UPLOAD_DIR = "C:\\upload_dir\\";

    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/UploadFile")
    public ResponseEntity<PriceUploadSummary> UploadFile(@RequestParam("file") MultipartFile uploadFile, UriComponentsBuilder ucBuilder, HttpSession session) throws IOException {
        PriceUploadSummary summary = new PriceUploadSummary();
        System.out.println(uploadFile);
        log.info("-- 上传的文件名："+uploadFile.getOriginalFilename());
        log.info("-- 文件大小： "+uploadFile.getSize());
        //
        String originalFilename = uploadFile.getOriginalFilename();
        //构建目标的完整文件名
        String fullname = UPLOAD_DIR + originalFilename;
        log.info("** 目标文件名"+fullname);
        //利用IO流进行读写，此处可以利用 commons-io中的工具类来快速完成
        FileCopyUtils.copy(uploadFile.getInputStream(), new FileOutputStream(new File(fullname)));
        log.info("-- 把上传的源文件COPY到目标文件完成....");

        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @RequestMapping(value = "/download", method = RequestMethod.POST)
    public void downloadFile(@RequestBody HashMap map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String realpath = UPLOAD_DIR;
        log.info("要下载的文件名："+map.get("id"));
        //找到指定的文件
        String id = (String) map.get("id");
        File parent = new File(realpath);
        File target = new File(parent, id);
        //
        if(target.isFile()) {
            //根据文件的类型不同，设置不同的响应内容类型
            if(id.endsWith(".zip")) {
                log.info("-- 文件内容是 zip 格式，应响头为：application/zip");
                response.setContentType("application/zip");
            } else if(id.endsWith(".jpg") || id.endsWith(".jpeg")) {
                log.info("-- 文件内容是图片格式，应响头为：image/jpeg");
                response.setContentType("image/jpeg");
            } else {
                log.info("-- 设置成通用的格式：application/octet-stream ");
                response.setContentType("application/octet-stream");
            }
            //这个头可以让浏览器弹出保存框
            response.setHeader("Content-disposition","attachment;filename=\""+id+"\"");
            //            //把此文件以流的方式输出到客户端
            try(FileInputStream fis = new FileInputStream(target);) {
                //利用commons-io的工具类来完成读写的COPY
                IOUtils.copy(fis, response.getOutputStream());
                //刷新缓存
                response.flushBuffer();
                log.info("下载完成");
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("下载的文件："+id);
    }
}
