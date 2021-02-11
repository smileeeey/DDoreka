package com.eureka.file.service;

import com.eureka.file.repository.FileRepository;
import com.eureka.file.dto.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;


    public void addFiles(List<MultipartFile> files) throws Exception {

        for (MultipartFile file : files) {
            if(file.isEmpty())  continue;

            String originName = file.getOriginalFilename();
            String ext = "";
            int index = originName.lastIndexOf(".");
            if(index!=-1){
                ext = originName.substring(index);
            }

            //저장할 이름
            String systemName = UUID.randomUUID().toString() + ext;


            String currentTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            StringTokenizer st = new StringTokenizer(currentTime,"/");
           //저장될 경로
            //String fsl = File.pathSeparator;
            //String fsl = "\\";
            String fsl="/";

            StringBuilder pathRoot = new StringBuilder();
            pathRoot.append("/home/upload/image");

            StringBuilder modulePath = new StringBuilder();
            modulePath.append(st.nextToken())
                    .append(fsl).append(st.nextToken())
                    .append(fsl).append(st.nextToken());

            File pFile = new File(pathRoot.toString()+fsl+modulePath.toString());
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

            repository.save(image);
        }


    }

    public void addFile(MultipartFile file) throws Exception{
        if(file.isEmpty())  return;
        String originName = file.getOriginalFilename();
        String ext = "";
        int index = originName.lastIndexOf(".");
        if(index!=-1){
            ext = originName.substring(index);
        }


        //저장할 이름
        String systemName = UUID.randomUUID().toString() + ext;

        String currentTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        StringTokenizer st = new StringTokenizer(currentTime,"/");
        //저장될 경로
        //String fsl = File.pathSeparator;
        //String fsl = "\\";
        String fsl="/";

        StringBuilder pathRoot = new StringBuilder();
        pathRoot.append("/home/upload/image");

        StringBuilder modulePath = new StringBuilder();
        modulePath.append(st.nextToken())
                .append(fsl).append(st.nextToken())
                .append(fsl).append(st.nextToken());

        File pFile = new File(pathRoot.toString()+fsl+modulePath.toString());
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

        repository.save(image);
    }

    public List<Image> filesServe(List<Integer> fileIds) {
        List<Image> images = new ArrayList<>();
        for (Integer fileId : fileIds) {
            images.add(repository.findById(fileId));
        }
        return images;
    }

    public Image fileServe(int fileId) {
        return repository.findById(fileId);
    }

    @Transactional
    public void delete(int fileId) throws Exception {

        Image image = repository.findById(fileId);
        repository.deleteAllById(fileId);

        //String fsl = File.pathSeparator;
        //String fsl = "\\";
        String fsl="/";

        StringBuilder pathRoot = new StringBuilder();
        pathRoot.append("/home/upload/image");

        pathRoot.append(fsl).append(image.getPath())
                .append(fsl).append(image.getSystemName());
        File file = new File(pathRoot.toString());
        if(file.exists()){
            if(!file.delete())  throw new Exception("파일 삭제 실패");
        }
    }


}
