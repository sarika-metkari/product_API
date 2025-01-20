package com.nt.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Product;

@Repository
public interface ProductReposetory extends JpaRepository<Product, Long>{

	void save(long id);

}
