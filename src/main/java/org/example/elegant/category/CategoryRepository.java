package org.example.elegant.category;

import org.example.elegant.category.entity.Category;
import org.example.elegant.common.repository.CommonRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends CommonRepository<Category,String> {
    Set<Category> findAllByNameIn(Set<String> names);
}
