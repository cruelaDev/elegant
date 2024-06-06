package org.example.elegant.order;
import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.order.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CommonRepository<Order, UUID> {
}
