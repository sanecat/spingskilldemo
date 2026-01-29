package com.example.skillsdemo.service;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Skill operations
 */
public interface SkillService {
    
    /**
     * Get all skills with pagination
     */
    Page<Skill> getAllSkills(Pageable pageable);
    
    /**
     * Get skill by ID
     */
    Optional<Skill> getSkillById(Long id);
    
    /**
     * Create a new skill
     */
    Skill createSkill(Skill skill);
    
    /**
     * Update an existing skill
     */
    Skill updateSkill(Long id, Skill skill);
    
    /**
     * Delete a skill
     */
    void deleteSkill(Long id);
    
    /**
     * Get skills by category
     */
    Page<Skill> getSkillsByCategory(String category, Pageable pageable);
    
    /**
     * Get skills by proficiency level
     */
    Page<Skill> getSkillsByProficiencyLevel(ProficiencyLevel level, Pageable pageable);
    
    /**
     * Search skills by keyword
     */
    Page<Skill> searchSkills(String keyword, Pageable pageable);
    
    /**
     * Get all categories
     */
    List<String> getAllCategories();
    
    /**
     * Search skills by category and keyword
     */
    Page<Skill> searchSkillsByCategory(String category, String keyword, Pageable pageable);
}
