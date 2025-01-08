package com.example.candidateSkillService.service.impl;

import com.example.candidateSkillService.dto.SkillDTO;
import com.example.candidateSkillService.entity.Skill;
import com.example.candidateSkillService.repository.SkillRepository;
import com.example.candidateSkillService.utils.ConvertDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SkillServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillServiceImpl skillService;

    private Skill skill;
    private SkillDTO skillDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        skill = new Skill();
        skill.setId(1L);
        skill.setSkillName("Skill 1");

        skillDTO = new SkillDTO();
        skillDTO.setId(1L);
        skillDTO.setSkillName("Skill 1");

    }

    @Test
    void getSkillById() {

        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        SkillDTO result = skillService.getSkillById(1L);

        assertEquals(skillDTO.getId(), result.getId());
        assertEquals(skillDTO.getSkillName(), result.getSkillName());
    }

    @Test
    void createSkill() {
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);
        SkillDTO result = skillService.createSkill(skillDTO);

        assertEquals(skillDTO.getId(), result.getId());
        assertEquals(skillDTO.getSkillName(), result.getSkillName());
    }

    @Test
    void getAllSkills() {
        when(skillRepository.findAll()).thenReturn(Arrays.asList(skill));
        assertEquals(1, skillService.getAllSkills().size());
        assertEquals(skillDTO.getId(), skillService.getAllSkills().get(0).getId());
        assertEquals(skillDTO.getSkillName(), skillService.getAllSkills().get(0).getSkillName());
    }

    @Test
    void deleteSkill() {
        when(skillRepository.findById(1L)).thenReturn(Optional.ofNullable(skill));
        skillService.deleteSkill(1L);

    }

    @Test
    void updateSkill() {
        when(skillRepository.findById(1L)).thenReturn(Optional.of(skill));
        when(skillRepository.save(any(Skill.class))).thenReturn(skill);

        SkillDTO result = skillService.updateSkill(1L, skillDTO);
        assertEquals(skillDTO.getId(), result.getId());
        assertEquals(skillDTO.getSkillName(), result.getSkillName());
    }

    @Test
    void getSkillByName() {
        when(skillRepository.findAll()).thenReturn(Arrays.asList(skill));
        SkillDTO result = skillService.getSkillByName("Skill 1");

        assertEquals(skillDTO.getId(), result.getId());
        assertEquals(skillDTO.getSkillName(), result.getSkillName());
    }
}