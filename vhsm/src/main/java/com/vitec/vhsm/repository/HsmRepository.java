package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Hsmlog;

import java.util.List;

public interface HsmRepository {
    List<Hsmlog> findHsmlog();
    List<Hsmlog> findOrderedByIdDESC(int limit);
    List<Hsmlog> latestTemper();
}
