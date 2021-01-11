package com.vinhtt.baseProject.repository;

import com.vinhtt.baseProject.model.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyFileRepository extends JpaRepository<MyFile, Long> {
}
