package com.eureka.file.service;

import com.eureka.file.dto.ImageDTO;
import com.eureka.file.repository.FileRepository;
import com.eureka.file.dto.Image;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;

    private boolean isUbuntu = false;
    private String fsl,rootPath;
    //private String fsl = File.pathSeparator;
    private String fslUbuntu = "/";
    private String fslWindow = "\\";
    private String rootPathUbuntu = "/var/www/folder/image";
    private String rootPathWindow = "C:\\Users\\sumin\\etc\\upload\\eureka";


    public List<Image> addFiles(List<MultipartFile> files) throws Exception {

        System.out.println("파일 개수:"+files.size());
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : files) {
            images.add(addFile(file));
        }

        return images;
    }

    public Image addFile(MultipartFile file) throws Exception{
        if(file.isEmpty())  throw new Exception("파일 등록 실패 (파일 객체 비었음)");
        String originName = file.getOriginalFilename();
        System.out.println(originName);
        String ext = "";
        int index = originName.lastIndexOf(".");
        if(index!=-1){
            ext = originName.substring(index);
        }

        //저장할 이름
        String systemName = UUID.randomUUID().toString() + ext;

        String currentTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        StringTokenizer st = new StringTokenizer(currentTime,"/");

        if(isUbuntu){
            fsl = fslUbuntu;
            rootPath = rootPathUbuntu;
        }
        else{
            fsl = fslWindow;
            rootPath = rootPathWindow;
        }


        StringBuilder modulePath = new StringBuilder();
        modulePath.append(st.nextToken())
                .append(fsl).append(st.nextToken())
                .append(fsl).append(st.nextToken());

        String totalPath = rootPath+fsl+modulePath.toString();
        File pFile = new File(totalPath);
        //폴더 있는지 확인하고 폴더 생성
        if(pFile.exists()==false) {
            pFile.mkdirs();
        }

        //서버에 파일 생성하기
        //pfile의 설정된 경로를 준다
        file.transferTo(new File(pFile,systemName));

        Image image = new Image();
        image.setOriginName(originName);
        image.setSystemName(systemName);
        image.setType(file.getContentType());
        image.setSize((int)file.getSize());
        image.setPath(modulePath.toString());

        Image doneImage = repository.save(image);
        InputStream imgStream = new FileInputStream(totalPath+fsl+systemName);
        byte[] imgByteArray = IOUtils.toByteArray(imgStream);
        imgStream.close();

        doneImage.setImageBytes(imgByteArray);

        return doneImage;
    }

    public List<Image> filesServe(List<Integer> fileIds) throws IOException {
        List<Image> images = new ArrayList<>();
        for (Integer fileId : fileIds) {
            images.add(fileServe(fileId));
        }
        return images;
    }

    public Image fileServe(int fileId) throws IOException {

        Image image = repository.findById(fileId);


        StringBuilder path = new StringBuilder();

        if(isUbuntu){
            fsl = fslUbuntu;
            rootPath = rootPathUbuntu;
        }
        else{
            fsl = fslWindow;
            rootPath = rootPathWindow;
        }

        path.append(rootPath);

        path.append(fsl).append(image.getPath())
                .append(fsl).append(image.getSystemName());

        InputStream imgStream = new FileInputStream(path.toString());
        byte[] imgByteArray = IOUtils.toByteArray(imgStream);
        imgStream.close();

        image.setImageBytes(imgByteArray);

        return image;
    }

    @Transactional
    public void delete(int fileId) throws Exception {

        Image image = repository.findById(fileId);
        repository.deleteAllById(fileId);


        StringBuilder pathRoot = new StringBuilder();

        if(isUbuntu){
            fsl = fslUbuntu;
            rootPath = rootPathUbuntu;
        }
        else{
            fsl = fslWindow;
            rootPath = rootPathWindow;
        }

        pathRoot.append(rootPath);

        pathRoot.append(fsl).append(image.getPath())
                .append(fsl).append(image.getSystemName());
        File file = new File(pathRoot.toString());
        if(file.exists()){
            if(!file.delete())  throw new Exception("파일 삭제 실패");
        }
    }


    public byte[] fileServeOne(int fileId) throws IOException {
        System.out.println("fileId "+fileId);
        ImageDTO imageDTO;

        Image image = repository.findById(fileId);

        StringBuilder path = new StringBuilder();

        if(isUbuntu){
            fsl = fslUbuntu;
            rootPath = rootPathUbuntu;
        }
        else{
            fsl = fslWindow;
            rootPath = rootPathWindow;
        }

        path.append(rootPath);

        path.append(fsl).append(image.getPath())
                .append(fsl).append(image.getSystemName());

        InputStream imgStream = new FileInputStream(path.toString());
        byte[] imgByteArray = IOUtils.toByteArray(imgStream);
        imgStream.close();
        System.out.println("바이트: "+imgByteArray.length +" "+imgByteArray.toString());

        return imgByteArray;
    }

//    public String fileServes(String imagesParam) throws IOException {
//        System.out.println(imagesParam);
//        Gson gson = new Gson();
//
//        Type listType = new TypeToken<ArrayList<ImageDTO>>(){}.getType();
//        List<ImageDTO> images = gson.fromJson(imagesParam, listType);
//
//        System.out.println("넘겨받은 파라미터의 사이즈:"+images.size());
//        for (int i = 0 ; i < images.size() ; ++i){
//            images.get(i).setImageBytes(fileServeOne(images.get(i).getFileId()));
//        }
//
//        System.out.println(images.size()+"완성");
//        String result = gson.toJson(images);
//        System.out.println(result.length());
//
//        return result;
//    }

    public List<ImageDTO> fileServes(String imagesParam) throws IOException {
        System.out.println(imagesParam);
        Gson gson = new Gson();

        Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
        List<Integer> fileIds = gson.fromJson(imagesParam, listType);

        System.out.println(fileIds.size());
        List<ImageDTO> images = new ArrayList<>();
        ImageDTO imageDTO;
        for (int i = 0 ; i < fileIds.size() ; ++i){
            imageDTO = new ImageDTO();
            imageDTO.setFileId(fileIds.get(i));
            imageDTO.setImageBytes(fileServeOne(fileIds.get(i)));
            images.add(imageDTO);
        }

        System.out.println(images.size()+"완성");
        String result = gson.toJson(images);
        System.out.println(result.length());

        return images;
    }

}
