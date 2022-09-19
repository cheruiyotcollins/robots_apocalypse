package com.indomidas.apocalypse.repository;

import com.indomidas.apocalypse.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;

public interface RobotRepository extends JpaRepository<Robot, Long > {
    List<Robot> findByCategory(String category);
}
