package com.example.queryjpa.Repository;

import com.example.queryjpa.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {

}
