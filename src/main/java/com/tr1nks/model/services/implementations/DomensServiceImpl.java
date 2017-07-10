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

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getEmailDomen() {
//        return domensRepository.getFirst().get;
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getImagineDomen() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public String getOfficeDomen() {
        return null;
    }
}
