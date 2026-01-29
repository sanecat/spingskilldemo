package com.example.skillsdemo.config;

import com.example.skillsdemo.model.Skill;
import com.example.skillsdemo.model.ProficiencyLevel;
import com.example.skillsdemo.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to populate the database with sample skills
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {
    
    private final SkillRepository skillRepository;
    
    @Override
    public void run(String... args) {
        if (skillRepository.count() == 0) {
            log.info("Initializing database with sample skills...");
            
            skillRepository.save(Skill.builder()
                    .name("Java")
                    .description("面向对象的编程语言，广泛应用于企业级应用开发")
                    .category("编程语言")
                    .proficiencyLevel(ProficiencyLevel.EXPERT)
                    .yearsOfExperience(8)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("Spring Boot")
                    .description("基于Spring的快速应用开发框架")
                    .category("框架")
                    .proficiencyLevel(ProficiencyLevel.ADVANCED)
                    .yearsOfExperience(5)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("Python")
                    .description("简洁优雅的编程语言，适合数据分析和机器学习")
                    .category("编程语言")
                    .proficiencyLevel(ProficiencyLevel.INTERMEDIATE)
                    .yearsOfExperience(3)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("React")
                    .description("用于构建用户界面的JavaScript库")
                    .category("框架")
                    .proficiencyLevel(ProficiencyLevel.INTERMEDIATE)
                    .yearsOfExperience(2)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("Docker")
                    .description("容器化应用部署工具")
                    .category("工具")
                    .proficiencyLevel(ProficiencyLevel.ADVANCED)
                    .yearsOfExperience(4)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("MySQL")
                    .description("开源关系型数据库管理系统")
                    .category("数据库")
                    .proficiencyLevel(ProficiencyLevel.ADVANCED)
                    .yearsOfExperience(6)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("Git")
                    .description("分布式版本控制系统")
                    .category("工具")
                    .proficiencyLevel(ProficiencyLevel.EXPERT)
                    .yearsOfExperience(7)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("沟通协作")
                    .description("团队协作和有效沟通的能力")
                    .category("软技能")
                    .proficiencyLevel(ProficiencyLevel.ADVANCED)
                    .yearsOfExperience(8)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("Kubernetes")
                    .description("容器编排平台")
                    .category("工具")
                    .proficiencyLevel(ProficiencyLevel.BEGINNER)
                    .yearsOfExperience(1)
                    .build());
            
            skillRepository.save(Skill.builder()
                    .name("TypeScript")
                    .description("JavaScript的超集，提供静态类型检查")
                    .category("编程语言")
                    .proficiencyLevel(ProficiencyLevel.INTERMEDIATE)
                    .yearsOfExperience(2)
                    .build());
            
            log.info("Sample skills initialized successfully!");
        } else {
            log.info("Database already contains skills, skipping initialization.");
        }
    }
}
