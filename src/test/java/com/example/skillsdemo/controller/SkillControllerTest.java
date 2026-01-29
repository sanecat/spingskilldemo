package com.example.skillsdemo.controller;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import com.example.skillsdemo.service.SkillService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SkillController.class)
class SkillControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SkillService skillService;

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
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testListSkills() throws Exception {
        Page<Skill> skillPage = new PageImpl<>(Arrays.asList(testSkill));
        when(skillService.getAllSkills(any(PageRequest.class))).thenReturn(skillPage);
        when(skillService.getAllCategories()).thenReturn(Arrays.asList("编程语言"));

        mockMvc.perform(get("/skills"))
                .andExpect(status().isOk())
                .andExpect(view().name("skills/list"))
                .andExpect(model().attributeExists("skills"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    void testShowCreateForm() throws Exception {
        when(skillService.getAllCategories()).thenReturn(Arrays.asList("编程语言"));

        mockMvc.perform(get("/skills/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("skills/create"))
                .andExpect(model().attributeExists("skill"))
                .andExpect(model().attributeExists("proficiencyLevels"));
    }

    @Test
    void testViewSkill() throws Exception {
        when(skillService.getSkillById(1L)).thenReturn(Optional.of(testSkill));

        mockMvc.perform(get("/skills/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("skills/view"))
                .andExpect(model().attributeExists("skill"));
    }

    @Test
    void testViewSkillNotFound() throws Exception {
        when(skillService.getSkillById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/skills/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/skills"));
    }

    @Test
    void testShowEditForm() throws Exception {
        when(skillService.getSkillById(1L)).thenReturn(Optional.of(testSkill));
        when(skillService.getAllCategories()).thenReturn(Arrays.asList("编程语言"));

        mockMvc.perform(get("/skills/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("skills/edit"))
                .andExpect(model().attributeExists("skill"));
    }

    @Test
    void testDeleteSkill() throws Exception {
        doNothing().when(skillService).deleteSkill(1L);

        mockMvc.perform(post("/skills/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/skills"));

        verify(skillService, times(1)).deleteSkill(1L);
    }
}
