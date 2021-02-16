package com.eureka.file.controller;
import com.eureka.file.dto.Image;
import com.eureka.file.dto.Response;
import com.eureka.file.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@Api(tags = {"1. File"})
@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
public class FileController {

    private final FileService service;

    public FileController(FileService service){
        this.service = service;
    }

    @ApiOperation(value="이미지 등록(upload)", notes = "이미지 파일을 업로드한다.", httpMethod = "POST")
    @PostMapping(value = "/upload")
    public Response uploadFile(
            @ApiParam(value="MultiipartFile 형태의 이미지 배열") MultipartHttpServletRequest request)
    {
        Response response;

        try {
            List<MultipartFile> inputs = request.getFiles("files");
            List<Image> images = service.addFiles(inputs);

            response = new Response("success", images.size()+"개 파일 등록 성공", images);
        } catch(Exception e){
            return response = new Response("error","파일 등록 실패",e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="이미지 1개 등록(upload)", notes = "이미지 파일을 업로드한다.", httpMethod = "POST")
    @PostMapping(value = "/upload1")
    public Response uploadOne(
            @ApiParam(value="MultiipartFile 파일") MultipartFile file) throws IllegalStateException {
        Response response;

        try {

            response = new Response("success", "1개 파일 등록 성공", service.addFile(file));
        } catch(Exception e){
            System.out.println("sad");
            response = new Response("error","파일 등록 실패",e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="이미지 서빙(n개)", notes = "여러개의 이미지 불러오기", httpMethod = "GET")
    @GetMapping(value = "/fileServe")
    public Response fileServe(@ApiParam(value="파일 고유값 리스트") @RequestParam(value="fileIds",required = true) List<Integer> fileIds) throws IllegalStateException {
        Response response;

        try {
            response = new Response("success", "파일 서빙 성공", service.filesServe(fileIds));
        } catch(Exception e){
            System.out.println("sad");
            response = new Response("error","파일 서빙 실패",e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="이미지 서빙(1개)", notes = "이미지 불러오기", httpMethod = "GET")
    @GetMapping(value = "/fileServe/{fileId}")
    public Response fileServe(@ApiParam(value="파일 고유값") @PathVariable int fileId) throws IllegalStateException {
        Response response;

        try {
            response = new Response("success", "파일 서빙 성공", service.fileServe(fileId));
        } catch(Exception e){
            System.out.println("sad");
            response = new Response("error","파일 서빙 실패",e.getMessage());
        }

        return response;
    }

    @ApiOperation(value="이미지 삭제", notes = "이미지 삭제하기", httpMethod = "DELETE")
    @DeleteMapping(value = "/delete/{fileId}")
    public Response delete(@ApiParam(value="파일 고유값") @PathVariable int fileId) throws IllegalStateException {
        Response response;

        try {
            service.delete(fileId);
            response = new Response("success", "파일 삭제 성공", null);
        } catch(Exception e){
            System.out.println("sad");
            response = new Response("error","파일 삭제 실패",e.getMessage());
        }

        return response;
    }
}
