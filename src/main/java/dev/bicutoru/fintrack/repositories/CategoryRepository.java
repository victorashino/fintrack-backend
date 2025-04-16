package dev.bicutoru.fintrack.repositories;

import dev.bicutoru.fintrack.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> { }
