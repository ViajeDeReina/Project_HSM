package com.vitec.vhsm.repository;

import com.vitec.vhsm.domain.Hsmlog;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class JpaHsmRepository implements HsmRepository {

    private final EntityManager em;

    @Autowired
    public JpaHsmRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Hsmlog> findHsmlog() {
        //List<CmdProcessingRateItem> items = new ArrayList<>();
        return em.createQuery("select a from Hsmlog as a", Hsmlog.class).getResultList();
    }

    @Override
    public List<Hsmlog> findOrderedByIdDESC(int limit) {
        return em.createQuery("select b from Hsmlog b order by b.id DESC", Hsmlog.class).setMaxResults(limit).getResultList();
    }

    // LATEST TEMPER STATE, We'll derive Temper message & date.
    @Override
    public List<Hsmlog> latestTemper() {
        List<Hsmlog> lastTemp = em.createQuery("select t from Hsmlog t where t.temper<>'Ok' order by t.id DESC", Hsmlog.class)
                .setMaxResults(1).getResultList();
        return lastTemp;
    }
}
