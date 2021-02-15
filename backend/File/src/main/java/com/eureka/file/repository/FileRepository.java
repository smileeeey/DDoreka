package com.eureka.file.repository;

import com.eureka.file.dto.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Image, String> {

    Image findById(int fileId);

    void deleteAllById(int fileId);
}
