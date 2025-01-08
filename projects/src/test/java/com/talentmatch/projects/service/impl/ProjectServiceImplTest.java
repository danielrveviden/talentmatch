package com.talentmatch.projects.service.impl;

import com.talentmatch.projects.entity.Project;
import com.talentmatch.projects.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    private Project project;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        project = new Project();
        project.setId(1L);
        project.setProjectName("Project 1");
        project.setSkillIds(List.of(1L, 2L));
        project.setCandidateIds(List.of(3L, 4L));


    }

    @Test
    void getAllProjects() {
        when(projectRepository.findAll()).thenReturn(Arrays.asList(project));
        assertEquals(1, projectService.getAllProjects().size());
        assertEquals(project.getId(), projectService.getAllProjects().get(0).getId());
    }

    @Test
    void getProjectById() {
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        assertEquals(project, projectService.getProjectById(1L));
    }

    @Test
    void createProject() {
        when(projectRepository.save(project)).thenReturn(project);
        assertEquals(project, projectService.createProject(project));
    }

    @Test
    void deleteProject() {
        // Mock the behavior of the void method
        doNothing().when(projectRepository).deleteById(1L);

        // Call the method under test
        projectService.deleteProject(1L);

        // Optionally, verify that the method was called
        verify(projectRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateProject() {
        when(projectRepository.save(project)).thenReturn(project);
        assertEquals(project, projectService.updateProject(1L, project));
    }
}