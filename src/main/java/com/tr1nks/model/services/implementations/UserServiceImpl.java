package com.tr1nks.model.services.implementations;

import com.tr1nks.model.entities.UserEntity;
import com.tr1nks.model.repositories.UserRepository;
import com.tr1nks.model.services.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * реализация для {@link UserService UserService}
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * {@inheritDoc}
     *
     * @param userEntity пользователь
     * @return
     */
    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @param id id удаляемого пользователя
     */
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     *
     * @param login логин
     * @return
     */
    @Override
    public UserEntity getByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    /**
     * {@inheritDoc}
     *
     * @param userEntity пользователь с обновленными данными
     * @return
     */
    @Override
    public UserEntity editUser(UserEntity userEntity) {
        return userRepository.saveAndFlush(userEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    /**
     * {@inheritDoc}
     *
     * @param id id пользователя
     * @return
     */
    @Override
    public UserEntity getById(int id) {
        return userRepository.getOne(id);
    }
}
