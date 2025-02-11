package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.entities.UserEntity;
import com.app.entities.Wishlist;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

	Wishlist findByUser(UserEntity user);
}
