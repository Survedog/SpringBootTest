package com.example.test2.repository;

import com.example.test2.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestEntity, String> {
    public TestEntity findByName(String name);
}
