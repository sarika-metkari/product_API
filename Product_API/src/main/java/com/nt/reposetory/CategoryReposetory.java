package com.nt.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.Category;

@Repository
public interface CategoryReposetory  extends JpaRepository<Category, Long>{

	Category save(Category category);

}
