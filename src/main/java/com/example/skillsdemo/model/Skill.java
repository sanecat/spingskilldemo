package com.example.skillsdemo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Skill entity representing a technical or soft skill
 */
@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "技能名称不能为空")
    @Size(min = 2, max = 100, message = "技能名称长度必须在2-100个字符之间")
    @Column(nullable = false, length = 100)
    private String name;
    
    @Size(max = 500, message = "描述长度不能超过500个字符")
    @Column(length = 500)
    private String description;
    
    @NotBlank(message = "分类不能为空")
    @Column(nullable = false, length = 50)
    private String category;
    
    @NotNull(message = "熟练度等级不能为空")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProficiencyLevel proficiencyLevel;
    
    @Min(value = 0, message = "经验年限不能为负数")
    @Max(value = 50, message = "经验年限不能超过50年")
    @Column(nullable = false)
    private Integer yearsOfExperience;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
