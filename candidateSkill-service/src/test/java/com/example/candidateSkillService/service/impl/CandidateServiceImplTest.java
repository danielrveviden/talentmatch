package com.example.candidateSkillService.service.impl;

import com.example.candidateSkillService.dto.CandidateDTO;
import com.example.candidateSkillService.dto.CandidateResponseDTO;
import com.example.candidateSkillService.dto.CandidateSkillDTO;
import com.example.candidateSkillService.entity.Candidate;
import com.example.candidateSkillService.repository.CandidateRepository;
import com.example.candidateSkillService.repository.SkillRepository;
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

class CandidateServiceImplTest {

    @Mock
    private SkillRepository skillRepository;

    @Mock
    private CandidateRepository candidateRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    private CandidateSkillServiceImpl candidateSkillService;

    private Candidate candidate;
    private CandidateDTO candidateDTO;
    private CandidateSkillDTO candidateSkillDTO;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        candidate = new Candidate();
        candidate.setId(1L);
        candidate.setCandidateName("Candidate 1");
        candidate.setEmail("candidate1@email.com");
        candidate.setDasId("123456789");

        candidateDTO = new CandidateDTO();
        candidateDTO.setId(1L);
        candidateDTO.setCandidateName("Candidate 1");
        candidateDTO.setEmail("candidate1@email.com");
        candidateDTO.setDasId("123456789");

        candidateSkillDTO = new CandidateSkillDTO();
        candidateSkillDTO.setId(1L);
        candidateSkillDTO.setCandidateId(1L);
        candidateSkillDTO.setSkillId(1L);
        candidateSkillDTO.setYearsOfExperience(5);
        candidateSkillDTO.setProficiency("Java");
        candidateSkillDTO.setLastYearUsed(5);
    }

    @Test
    void getCandidateById() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
        CandidateResponseDTO result = candidateService.getCandidateById(1L);

        assertEquals(candidateDTO.getId(), result.getId());
        assertEquals(candidateDTO.getCandidateName(), result.getCandidateName());
        assertEquals(candidateDTO.getEmail(), result.getEmail());
        assertEquals(candidateDTO.getDasId(), result.getDasId());
    }

    @Test
    void createCandidate() {
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);
        CandidateDTO result = candidateService.createCandidate(candidateDTO);
        assertEquals(candidateDTO.getId(), result.getId());
        assertEquals(candidateDTO.getCandidateName(), result.getCandidateName());
        assertEquals(candidateDTO.getEmail(), result.getEmail());
        assertEquals(candidateDTO.getDasId(), result.getDasId());
    }

    @Test
    void getAllCandidates() {
        when(candidateRepository.findAll()).thenReturn(Arrays.asList(candidate));
        assertEquals(1, candidateService.getAllCandidates().size());
        assertEquals(candidateDTO.getId(), candidateService.getAllCandidates().get(0).getId());
        assertEquals(candidateDTO.getCandidateName(), candidateService.getAllCandidates().get(0).getCandidateName());
        assertEquals(candidateDTO.getEmail(), candidateService.getAllCandidates().get(0).getEmail());
        assertEquals(candidateDTO.getDasId(), candidateService.getAllCandidates().get(0).getDasId());
    }

    @Test
    void deleteCandidate() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(candidate));
        candidateService.deleteCandidate(1L);
    }

    @Test
    void updateCandidate() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        CandidateDTO result = candidateService.updateCandidate(1L, candidateDTO);
        assertEquals(candidateDTO.getId(), result.getId());
        assertEquals(candidateDTO.getCandidateName(), result.getCandidateName());
        assertEquals(candidateDTO.getEmail(), result.getEmail());
        assertEquals(candidateDTO.getDasId(), result.getDasId());
    }

    @Test
    void getCandidateCS() {
        when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
        CandidateDTO result = candidateService.getCandidateCS(1L);

        assertEquals(candidateDTO.getId(), result.getId());
        assertEquals(candidateDTO.getCandidateName(), result.getCandidateName());
        assertEquals(candidateDTO.getEmail(), result.getEmail());
        assertEquals(candidateDTO.getDasId(), result.getDasId());
    }
}