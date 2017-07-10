package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.GroupEntity;
import com.tr1nks.model.repositories.GroupRepository;
import com.tr1nks.model.services.GroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link GroupService GroupService}
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Resource
    private GroupRepository groupRepository;

    /**
     * {@inheritDoc}
     *
     * @param groupEntity группа
     * @return
     */
    @Override
    public GroupEntity addGroup(GroupEntity groupEntity) {
        return groupRepository.saveAndFlush(groupEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id группы
     */
    @Override
    public void delete(int id) {
        groupRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param cipher шифр
     * @return
     */
    @Override
    public GroupEntity getByCipher(String cipher) {
        return groupRepository.findByChiper(cipher);
    }

    /**
     * {@inheritDoc}
     *
     * @param groupEntity группа с отредактированными данными
     * @return
     */
    @Override
    public GroupEntity editGroup(GroupEntity groupEntity) {
        return groupRepository.saveAndFlush(groupEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<GroupEntity> getAll() {
        return groupRepository.findAll();
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<Integer> getYears() {
        return groupRepository.getYears();
    }

    /**
     * {@inheritDoc}
     *
     * @param id id группы
     * @return
     */
    @Override
    public GroupEntity getById(int id) {
        return groupRepository.getOne(id);
    }
}
