package org.example.elegant.wishlist;

import org.example.elegant.common.repository.CommonRepository;
import org.example.elegant.wishlist.entity.Wishlist;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WishlistRepository extends CommonRepository<Wishlist, UUID> {

}
