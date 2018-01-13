package guru.springfamework.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springfamework.domain.OrderObj;

public interface OrderRepository extends JpaRepository<OrderObj, Long>{

}
