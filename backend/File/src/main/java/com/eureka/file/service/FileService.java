package com.eureka.file.service;

import com.eureka.file.repository.FileRepository;
import com.eureka.file.dto.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private FileRepository repository;


    public void addFiles(MultipartFile[] files) throws Exception {

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
            String fsl = File.pathSeparator;

            StringBuilder pathRoot = new StringBuilder();
            pathRoot.append("C:\\Users\\sumin")
                    .append(fsl).append("etc")
                    .append(fsl).append("upload")
                    .append(fsl).append("eureka");

            StringBuilder modulePath = new StringBuilder();
            modulePath.append(fsl).append(st.nextToken())
                    .append(fsl).append(st.nextToken())
                    .append(fsl).append(st.nextToken());

            File pFile = new File(pathRoot.toString()+modulePath.toString());
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
        //if(file.isEmpty())  return;
        System.out.println("파일은 있다.");
        String originName = file.getOriginalFilename();
        System.out.println("오리지날이름:"+originName);
        String ext = "";
        int index = originName.lastIndexOf(".");
        if(index!=-1){
            ext = originName.substring(index);
        }
        System.out.println("추가정보"+ext);


        //저장할 이름
        String systemName = UUID.randomUUID().toString() + ext;
        System.out.println(systemName);

        String currentTime = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        StringTokenizer st = new StringTokenizer(currentTime,"/");
        //저장될 경로
        String fsl = File.pathSeparator;

        StringBuilder pathRoot = new StringBuilder();
        pathRoot.append("C://Users/sumin")
                .append(fsl).append("etc")
                .append(fsl).append("upload")
                .append(fsl).append("eureka");

        StringBuilder modulePath = new StringBuilder();
        modulePath.append(fsl).append(st.nextToken())
                .append(fsl).append(st.nextToken())
                .append(fsl).append(st.nextToken());

        File pFile = new File(pathRoot.toString()+modulePath.toString());
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
