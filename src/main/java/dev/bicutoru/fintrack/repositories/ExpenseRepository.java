package dev.bicutoru.fintrack.repositories;

import dev.bicutoru.fintrack.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> { }