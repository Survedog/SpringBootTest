package com.example.test2.service;

import com.example.test2.dto.PersonDTO;
import com.example.test2.entity.TestEntity;
import com.example.test2.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public void save(PersonDTO dto) {
        testRepository.save(new TestEntity(dto.getName(), dto.getAge()));
    }

    public void saveAll(PersonDTO[] dto) {
        List<TestEntity> entityList = new ArrayList<>();
        for (PersonDTO personDto : dto) {
            entityList.add(new TestEntity(personDto.getName(), personDto.getAge()));
        }

        testRepository.saveAll(entityList);
    }

    public PersonDTO find(String name) {
        TestEntity entity = testRepository.findByName(name);

        return new PersonDTO(entity.getName(), entity.getAge());
    }

    public List<PersonDTO> findAll() {
        List<TestEntity> entityList = testRepository.findAll();
        List<PersonDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> {
            dtoList.add(new PersonDTO(entity.getName(), entity.getAge()));
        });

        return dtoList;
    }
}
