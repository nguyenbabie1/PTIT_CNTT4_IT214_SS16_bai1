package ra.flashsalecache.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.flashsalecache.enity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}