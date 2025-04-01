package dev.bicutoru.fintrack.repositories;

import dev.bicutoru.fintrack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> { }
