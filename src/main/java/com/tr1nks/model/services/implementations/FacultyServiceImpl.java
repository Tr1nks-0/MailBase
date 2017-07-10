package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.FacultyEntity;
import com.tr1nks.model.repositories.FacultyRepository;
import com.tr1nks.model.services.FacultyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link FacultyService FacultyService}
 */
@Service
public class FacultyServiceImpl implements FacultyService {
    /**
     * {@inheritDoc}
     */
    @Resource
    private FacultyRepository facultyRepository;

    /**
     * {@inheritDoc}
     *
     * @param facultyEntity факультет
     * @return
     */
    @Override
    public FacultyEntity addFaculty(FacultyEntity facultyEntity) {
        return facultyRepository.saveAndFlush(facultyEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id факультета
     */
    @Override
    public void delete(int id) {
        facultyRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id факультета
     * @return
     */
    @Override
    public FacultyEntity getById(int id) {
        return facultyRepository.getOne(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param name имя факультета
     * @return
     */
    @Override
    public FacultyEntity getByName(String name) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param abbr аббревиатура
     * @return
     */
    @Override
    public FacultyEntity getByAbbr(String abbr) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param facultyEntity факультет с редактированными данными
     * @return
     */
    @Override
    public FacultyEntity editFaculty(FacultyEntity facultyEntity) {
        return facultyRepository.saveAndFlush(facultyEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<FacultyEntity> getAll() {
        return facultyRepository.findAll();
    }
}
