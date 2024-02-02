package com.bank.profile.services.classes;



import com.bank.profile.dto.AuditDto;
import com.bank.profile.mapper.interfaces.AuditMapper;
import com.bank.profile.model.Audit;
import com.bank.profile.repository.AuditRepository;
import com.bank.profile.services.interfase.AuditService;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditServiceImpl extends BaseServiceImpl<
        AuditDto,
        Audit>
        implements AuditService {

    private final AuditRepository repository;
    private final AuditMapper mapper;

    public AuditServiceImpl(AuditRepository repository, AuditMapper mapper) {
        super(repository, mapper, Audit.class);
        this.repository = repository;
        this.mapper = mapper;
    }
}
