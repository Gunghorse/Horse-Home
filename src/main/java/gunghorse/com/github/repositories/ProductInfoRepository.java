package gunghorse.com.github.repositories;

import gunghorse.com.github.model.ProductInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@EnableScan
public interface ProductInfoRepository extends CrudRepository<ProductInfo, String> {
    Optional<ProductInfo> findById(String id);
    List<ProductInfo> findByMsrp(String msrp);
}
