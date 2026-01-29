package com.example.skillsdemo.service;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import com.example.skillsdemo.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of SkillService
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SkillServiceImpl implements SkillService {
    
    private final SkillRepository skillRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Page<Skill> getAllSkills(Pageable pageable) {
        log.debug("Fetching all skills with pagination: {}", pageable);
        return skillRepository.findAll(pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> getSkillById(Long id) {
        log.debug("Fetching skill with id: {}", id);
        return skillRepository.findById(id);
    }
    
    @Override
    public Skill createSkill(Skill skill) {
        log.info("Creating new skill: {}", skill.getName());
        return skillRepository.save(skill);
    }
    
    @Override
    public Skill updateSkill(Long id, Skill skill) {
        log.info("Updating skill with id: {}", id);
        return skillRepository.findById(id)
                .map(existingSkill -> {
                    existingSkill.setName(skill.getName());
                    existingSkill.setDescription(skill.getDescription());
                    existingSkill.setCategory(skill.getCategory());
                    existingSkill.setProficiencyLevel(skill.getProficiencyLevel());
                    existingSkill.setYearsOfExperience(skill.getYearsOfExperience());
                    return skillRepository.save(existingSkill);
                })
                .orElseThrow(() -> new RuntimeException("Skill not found with id: " + id));
    }
    
    @Override
    public void deleteSkill(Long id) {
        log.info("Deleting skill with id: {}", id);
        skillRepository.deleteById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Skill> getSkillsByCategory(String category, Pageable pageable) {
        log.debug("Fetching skills by category: {}", category);
        return skillRepository.findByCategory(category, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Skill> getSkillsByProficiencyLevel(ProficiencyLevel level, Pageable pageable) {
        log.debug("Fetching skills by proficiency level: {}", level);
        return skillRepository.findByProficiencyLevel(level, pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Skill> searchSkills(String keyword, Pageable pageable) {
        log.debug("Searching skills with keyword: {}", keyword);
        if (keyword == null || keyword.trim().isEmpty()) {
            return getAllSkills(pageable);
        }
        return skillRepository.searchSkills(keyword.trim(), pageable);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<String> getAllCategories() {
        log.debug("Fetching all categories");
        return skillRepository.findAllCategories();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Skill> searchSkillsByCategory(String category, String keyword, Pageable pageable) {
        log.debug("Searching skills by category: {} and keyword: {}", category, keyword);
        if (keyword == null || keyword.trim().isEmpty()) {
            return getSkillsByCategory(category, pageable);
        }
        return skillRepository.searchSkillsByCategory(category, keyword.trim(), pageable);
    }
}
