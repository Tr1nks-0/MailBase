package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.PersonEntity;
import com.tr1nks.model.repositories.PersonRepository;
import com.tr1nks.model.services.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link PersonService PersonService}
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Resource
    private PersonRepository personRepository;

    /**
     * {@inheritDoc}
     *
     * @param personEntity персона
     * @return
     */
    @Override
    public PersonEntity addPerson(PersonEntity personEntity) {
        return personRepository.saveAndFlush(personEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id персоны
     */
    @Override
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param fio ФИО
     * @return
     */
    @Override
    public PersonEntity getByFio(String fio) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param code код
     * @return
     */
    @Override
    public PersonEntity getByCode(String code) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param login логин почты
     * @return
     */
    @Override
    public PersonEntity getByLogin(String login) {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @param personEntity порсона с редактированными данными
     * @return
     */
    @Override
    public PersonEntity editPerson(PersonEntity personEntity) {
        return personRepository.saveAndFlush(personEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<PersonEntity> getAll() {
        return null;
    }
}
