package com.rdi.menu.repository;

import com.rdi.menu.entity.ProductStatus;
import com.rdi.menu.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Long> {

    @Query("SELECT ps FROM ProductStatus ps WHERE ps.status = ?1 and ps.id IN ?2")
    List<ProductStatus> findByStatusAndIdIn(Status status, List<Long> ids);

}
