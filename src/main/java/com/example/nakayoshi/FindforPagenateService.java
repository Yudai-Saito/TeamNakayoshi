package com.example.nakayoshi;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.*;;
@Repository
public class FindforPagenateService {
    
    @PersistenceContext
    private EntityManager em;

    // これと同じことをMySQL環境でやりたい
    public int findAllCnt() {
        return ((Number) em.createQuery("select count(*) from Board")
                    .getSingleResult()).intValue();
    }

    public List<CustomerBean> findListPaging(int startIndex, int pageSize) {
        return em.createQuery("select b from Board b", CustomerBean.class)
                    .setFirstResult(startIndex)
                    .setMaxResults(pageSize)
                    .getResultList();
    }
}
