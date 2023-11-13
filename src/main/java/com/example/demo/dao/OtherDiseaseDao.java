package com.example.demo.dao;

import com.example.demo.entities.OtherDisease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherDiseaseDao extends JpaRepository<OtherDisease, Long> {

    List<OtherDisease> findByPersonId(Long id);

    void deleteByPersonId(Long id);
}
