package com.example.backend.repositoory;

import com.example.backend.model.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, Long> {
    List<Queue> findAllByIsDone(Boolean isDone);
}
