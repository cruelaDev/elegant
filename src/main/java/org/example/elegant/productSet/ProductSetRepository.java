package org.example.elegant.productSet;



import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.productSet.entity.ProductSet;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductSetRepository extends CommonRepository<ProductSet, UUID> {
}
