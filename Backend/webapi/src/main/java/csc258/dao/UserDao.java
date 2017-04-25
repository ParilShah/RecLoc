package csc258.dao;

import csc258.dao.repositories.jpa.interfaces.IUserRepository;
import csc258.domain.db.user.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by desair4 on 4/16/2017.
 */
@Component
@Transactional
public class UserDao {
    private IUserRepository userRepository;

    public UserDomain saveUser(UserDomain userDomain) {
        return userRepository.save(userDomain);
    }

    public Iterable<UserDomain> findAllUsers() {
        return userRepository.findAll();
    }

    public UserDomain findUserById(UserDomain userDomain) {
        return userRepository.findByDeviceId(userDomain.getDeviceId());
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    void afterPropertiesSet() {
        Assert.notNull(userRepository, "userRepository cannot be null");
    }
}
