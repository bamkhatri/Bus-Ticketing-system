package com.example.demo.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Model.BusDetails;

public interface BusDetailsRepository extends JpaRepository<BusDetails, Integer>{
	
	@Query(value = "SELECT * FROM bus_details WHERE from_Destination = ?1 AND to_Destination = ?2" , nativeQuery = true)
	public List<BusDetails> findUserBus(@Param("fromDestination")String fromDestination,@Param("toDestination")String toDestination);
	
	@Query(value = "UPDATE bus_details SET seats_available = ?1 WHERE bus_id = ?2", nativeQuery = true)
	@Modifying
	@Transactional
	public void reduceSeats(@Param("seats")String seats,@Param("bus_Id")int bus_Id);
	
	@Query(value = "INSERT INTO user_busdetails(u_id,b_id) values(?1,?2)",nativeQuery = true)
	@Modifying
	@Transactional
	void bookBus(@Param("u_id") int u_id,@Param("b_id") int b_id);
	
	
}
