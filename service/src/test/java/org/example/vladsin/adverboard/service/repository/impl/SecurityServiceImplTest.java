package org.example.vladsin.adverboard.service.repository.impl;

import org.example.vladsin.adverboard.dao.repository.AuthUserDao;
import org.example.vladsin.adverboard.dao.repository.SecurityDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

    @Mock
    SecurityDao dao;

    @InjectMocks
    SecurityServiceImpl service;

    @Test
    void login() {

    }

    @Test
    void updatePassword() {
    }
}