package com.dataservice.fileService.repository;

import com.dataservice.fileService.dao.DatapointData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatapointDataRepo extends JpaRepository<DatapointData,String> {

}
