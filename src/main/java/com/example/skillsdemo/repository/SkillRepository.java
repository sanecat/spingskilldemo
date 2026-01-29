package com.example.skillsdemo.repository;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Skill entity
 */
@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {
    
    /**
     * Find skills by category
     */
    Page<Skill> findByCategory(String category, Pageable pageable);
    
    /**
     * Find skills by proficiency level
     */
    Page<Skill> findByProficiencyLevel(ProficiencyLevel level, Pageable pageable);
    
    /**
     * Search skills by name or description
     */
    @Query("SELECT s FROM Skill s WHERE " +
           "LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Skill> searchSkills(@Param("keyword") String keyword, Pageable pageable);
    
    /**
     * Find all distinct categories
     */
    @Query("SELECT DISTINCT s.category FROM Skill s ORDER BY s.category")
    List<String> findAllCategories();
    
    /**
     * Search skills by category and keyword
     */
    @Query("SELECT s FROM Skill s WHERE s.category = :category AND " +
           "(LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(s.description) LIKE LOWER(CONCAT('%', :keyword, '%')))")
    Page<Skill> searchSkillsByCategory(@Param("category") String category, 
                                       @Param("keyword") String keyword, 
                                       Pageable pageable);
}
