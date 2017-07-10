package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.CathedraEntity;
import com.tr1nks.model.repositories.CathedraRepository;
import com.tr1nks.model.services.CathedraService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link CathedraService CathedraService}
 */
@Service
public class CathedraServiceImpl implements CathedraService {
    @Resource
    private CathedraRepository cathedraRepository;

    /**
     * {@inheritDoc}
     *
     * @param cathedraEntity кафедра
     * @return
     */
    @Override
    public CathedraEntity addCathedra(CathedraEntity cathedraEntity) {
        return cathedraRepository.saveAndFlush(cathedraEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id кафедры
     */
    @Override
    public void delete(int id) {
        cathedraRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param name название кафедры
     * @return
     */
    @Override
    public CathedraEntity getByName(String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param abbr аббревиатура кафедры
     * @return
     */
    @Override
    public CathedraEntity getByAbbr(String abbr) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param cathedraEntity кафедра с отредактированными данными
     * @return
     */
    @Override
    public CathedraEntity editCathedra(CathedraEntity cathedraEntity) {
        return cathedraRepository.saveAndFlush(cathedraEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<CathedraEntity> getAll() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param id id кафедры
     * @return
     */
    @Override
    public CathedraEntity getById(int id) {
        return cathedraRepository.getOne(id);
    }
}
