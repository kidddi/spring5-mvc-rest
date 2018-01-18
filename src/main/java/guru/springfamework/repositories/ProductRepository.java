package guru.springfamework.repositories;

import org.springframework.data.repository.CrudRepository;
import guru.springfamework.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
