package com.tr1nks.model.services.implementations;

import com.tr1nks.model.repositories.DomenRepository;
import com.tr1nks.model.services.DomensService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * реализация для {@link DomensService DomensService}
 */
@Service
public class DomensServiceImpl implements DomensService {
    @Resource
    private DomenRepository domensRepository;
    private static String emailDomen;
    private static String imagineDomen;
    private static String officeDomen;

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getEmailDomen() {
        if (null == emailDomen) {
            emailDomen = domensRepository.findFirstById(1).getEmailDomen();
        }
        return emailDomen;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getImagineDomen() {
        if (null == imagineDomen) {
            imagineDomen = domensRepository.findFirstById(1).getImagineDomen();
        }
        return imagineDomen;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getOfficeDomen() {
        if (null == officeDomen) {
            officeDomen = domensRepository.findFirstById(1).getOfficeDomen();
        }
        return officeDomen;
    }
}
