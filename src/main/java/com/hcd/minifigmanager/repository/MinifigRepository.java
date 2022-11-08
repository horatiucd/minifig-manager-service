package com.hcd.minifigmanager.repository;

import com.hcd.minifigmanager.model.Minifig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinifigRepository extends JpaRepository<Minifig, Long> {

}
