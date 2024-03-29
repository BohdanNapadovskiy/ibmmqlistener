package com.example.ibmmqlistener.service.repository;


import com.example.ibmmqlistener.domain.OrderMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderMessage, Long> {
}
