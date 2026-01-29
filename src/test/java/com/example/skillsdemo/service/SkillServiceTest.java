package com.example.skillsdemo.service;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import com.example.skillsdemo.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    private Skill testSkill;

    @BeforeEach
    void setUp() {
        testSkill = Skill.builder()
                .id(1L)
                .name("Java")
                .description("Programming language")
                .category("编程语言")
                .proficiencyLevel(ProficiencyLevel.EXPERT)
                .yearsOfExperience(5)
                .build();
    }

    @Test
    void testGetAllSkills() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Skill> skills = Arrays.asList(testSkill);
        Page<Skill> skillPage = new PageImpl<>(skills);

        when(skillRepository.findAll(pageable)).thenReturn(skillPage);

        Page<Skill> result = skillService.getAllSkills(pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(skillRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetSkillById() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(testSkill));

        Optional<Skill> result = skillService.getSkillById(1L);

        assertTrue(result.isPresent());
        assertEquals("Java", result.get().getName());
        verify(skillRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateSkill() {
        when(skillRepository.save(any(Skill.class))).thenReturn(testSkill);

        Skill result = skillService.createSkill(testSkill);

        assertNotNull(result);
        assertEquals("Java", result.getName());
        verify(skillRepository, times(1)).save(testSkill);
    }

    @Test
    void testUpdateSkill() {
        Skill updatedSkill = Skill.builder()
                .name("Java Updated")
                .description("Updated description")
                .category("编程语言")
                .proficiencyLevel(ProficiencyLevel.EXPERT)
                .yearsOfExperience(6)
                .build();

        when(skillRepository.findById(1L)).thenReturn(Optional.of(testSkill));
        when(skillRepository.save(any(Skill.class))).thenReturn(testSkill);

        Skill result = skillService.updateSkill(1L, updatedSkill);

        assertNotNull(result);
        verify(skillRepository, times(1)).findById(1L);
        verify(skillRepository, times(1)).save(any(Skill.class));
    }

    @Test
    void testDeleteSkill() {
        doNothing().when(skillRepository).deleteById(1L);

        skillService.deleteSkill(1L);

        verify(skillRepository, times(1)).deleteById(1L);
    }

    @Test
    void testSearchSkills() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Skill> skills = Arrays.asList(testSkill);
        Page<Skill> skillPage = new PageImpl<>(skills);

        when(skillRepository.searchSkills("Java", pageable)).thenReturn(skillPage);

        Page<Skill> result = skillService.searchSkills("Java", pageable);

        assertNotNull(result);
        assertEquals(1, result.getContent().size());
        verify(skillRepository, times(1)).searchSkills("Java", pageable);
    }

    @Test
    void testGetAllCategories() {
        List<String> categories = Arrays.asList("编程语言", "框架", "工具");

        when(skillRepository.findAllCategories()).thenReturn(categories);

        List<String> result = skillService.getAllCategories();

        assertNotNull(result);
        assertEquals(3, result.size());
        verify(skillRepository, times(1)).findAllCategories();
    }
}
