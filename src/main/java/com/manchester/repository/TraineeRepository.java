package com.manchester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manchester.entities.Trainee;

@Repository
public interface TraineeRepository extends JpaRepository <Trainee,Integer> {

}
