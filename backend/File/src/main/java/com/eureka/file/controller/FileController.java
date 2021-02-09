package com.eureka.file.controller;
import com.eureka.file.dto.Response;
import com.eureka.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"1. File"})
@RestController
@RequestMapping("/file")
public class FileController {

    private final FileService service;

    public FileController(FileService service){
        this.service = service;
    }

    @ApiOperation(value="이미지 등록(upload)", notes = "이미지 파일을 업로드한다.", httpMethod = "POST")
    @PostMapping(value = "/upload", consumes = {"multipart/form-data","application/x-www-form-urlencoded"})
    public Response uploadFile(
            @ApiParam(value="MultiipartFile 형태의 이미지 배열") @RequestPart(value = "imgUrlBase", required = false) MultipartFile[] files){
        Response response;

        try {
            service.addFiles(files);
            response = new Response("success", "1개 파일 등록 성공", null);
        } catch(Exception e){
            return response = new Response("error","파일 등록 실패",e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="이미지 1개 등록(upload)", notes = "이미지 파일을 업로드한다.", httpMethod = "POST")
    @PostMapping(value = "/upload1", consumes = {"multipart/form-data"})
    public Response uploadOne(
            @ApiParam(value="MultiipartFile 파일") @RequestPart(value = "imgUrlBase", required = false) MultipartFile file){
        Response response;

        try {
            service.addFile(file);
            response = new Response("success", "1개 파일 등록 성공", null);
        } catch(Exception e){
            System.out.println("sad");
            response = new Response("error","파일 등록 실패",e.getMessage());
        }

        return response;
    }

}
