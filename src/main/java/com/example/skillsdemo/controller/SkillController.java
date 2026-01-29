package com.example.skillsdemo.controller;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import com.example.skillsdemo.service.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handling Skill CRUD operations
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class SkillController {
    
    private final SkillService skillService;
    
    /**
     * Home page
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    /**
     * List all skills with pagination, search and filter
     */
    @GetMapping("/skills")
    public String listSkills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            Model model) {
        
        log.debug("Listing skills - page: {}, size: {}, keyword: {}, category: {}", 
                  page, size, keyword, category);
        
        Sort sort = sortDir.equalsIgnoreCase("asc") ? 
                    Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        
        Page<Skill> skillsPage;
        
        if (category != null && !category.isEmpty() && keyword != null && !keyword.isEmpty()) {
            skillsPage = skillService.searchSkillsByCategory(category, keyword, pageable);
        } else if (category != null && !category.isEmpty()) {
            skillsPage = skillService.getSkillsByCategory(category, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            skillsPage = skillService.searchSkills(keyword, pageable);
        } else {
            skillsPage = skillService.getAllSkills(pageable);
        }
        
        model.addAttribute("skills", skillsPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", skillsPage.getTotalPages());
        model.addAttribute("totalItems", skillsPage.getTotalElements());
        model.addAttribute("pageSize", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);
        model.addAttribute("selectedCategory", category);
        model.addAttribute("categories", skillService.getAllCategories());
        
        return "skills/list";
    }
    
    /**
     * Show create skill form
     */
    @GetMapping("/skills/new")
    public String showCreateForm(Model model) {
        model.addAttribute("skill", new Skill());
        model.addAttribute("proficiencyLevels", ProficiencyLevel.values());
        model.addAttribute("categories", skillService.getAllCategories());
        return "skills/create";
    }
    
    /**
     * Create a new skill
     */
    @PostMapping("/skills")
    public String createSkill(@Valid @ModelAttribute("skill") Skill skill,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        if (result.hasErrors()) {
            log.warn("Validation errors when creating skill: {}", result.getAllErrors());
            model.addAttribute("proficiencyLevels", ProficiencyLevel.values());
            model.addAttribute("categories", skillService.getAllCategories());
            return "skills/create";
        }
        
        try {
            Skill savedSkill = skillService.createSkill(skill);
            log.info("Successfully created skill with id: {}", savedSkill.getId());
            redirectAttributes.addFlashAttribute("successMessage", 
                "技能创建成功！");
            return "redirect:/skills/" + savedSkill.getId();
        } catch (Exception e) {
            log.error("Error creating skill", e);
            redirectAttributes.addFlashAttribute("errorMessage", 
                "创建技能时发生错误：" + e.getMessage());
            return "redirect:/skills/new";
        }
    }
    
    /**
     * View skill details
     */
    @GetMapping("/skills/{id}")
    public String viewSkill(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return skillService.getSkillById(id)
                .map(skill -> {
                    model.addAttribute("skill", skill);
                    return "skills/view";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", 
                        "找不到ID为 " + id + " 的技能");
                    return "redirect:/skills";
                });
    }
    
    /**
     * Show edit skill form
     */
    @GetMapping("/skills/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        return skillService.getSkillById(id)
                .map(skill -> {
                    model.addAttribute("skill", skill);
                    model.addAttribute("proficiencyLevels", ProficiencyLevel.values());
                    model.addAttribute("categories", skillService.getAllCategories());
                    return "skills/edit";
                })
                .orElseGet(() -> {
                    redirectAttributes.addFlashAttribute("errorMessage", 
                        "找不到ID为 " + id + " 的技能");
                    return "redirect:/skills";
                });
    }
    
    /**
     * Update skill
     */
    @PostMapping("/skills/{id}")
    public String updateSkill(@PathVariable Long id,
                              @Valid @ModelAttribute("skill") Skill skill,
                              BindingResult result,
                              RedirectAttributes redirectAttributes,
                              Model model) {
        
        if (result.hasErrors()) {
            log.warn("Validation errors when updating skill: {}", result.getAllErrors());
            model.addAttribute("proficiencyLevels", ProficiencyLevel.values());
            model.addAttribute("categories", skillService.getAllCategories());
            return "skills/edit";
        }
        
        try {
            skillService.updateSkill(id, skill);
            log.info("Successfully updated skill with id: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", 
                "技能更新成功！");
            return "redirect:/skills/" + id;
        } catch (Exception e) {
            log.error("Error updating skill", e);
            redirectAttributes.addFlashAttribute("errorMessage", 
                "更新技能时发生错误：" + e.getMessage());
            return "redirect:/skills/" + id + "/edit";
        }
    }
    
    /**
     * Delete skill
     */
    @PostMapping("/skills/{id}/delete")
    public String deleteSkill(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            skillService.deleteSkill(id);
            log.info("Successfully deleted skill with id: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", 
                "技能删除成功！");
        } catch (Exception e) {
            log.error("Error deleting skill", e);
            redirectAttributes.addFlashAttribute("errorMessage", 
                "删除技能时发生错误：" + e.getMessage());
        }
        return "redirect:/skills";
    }
}
