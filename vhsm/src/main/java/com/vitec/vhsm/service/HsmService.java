package com.vitec.vhsm.service;

import com.vitec.vhsm.domain.Hsmlog;
import com.vitec.vhsm.repository.HsmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class HsmService {
    private final HsmRepository hsmRepository;

    @Autowired
    public HsmService(HsmRepository hsmRepository) {
        this.hsmRepository = hsmRepository;
    }

    public List<Hsmlog> findCmdProcessingRate(){
        return hsmRepository.findHsmlog();
    }
    public List<Hsmlog> findHsmLogLimit(int limit) {return hsmRepository.findOrderedByIdDESC(limit);}

    public List<Hsmlog> lastTemper() {return hsmRepository.latestTemper();}
}
