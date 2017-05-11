package csc258.dao.repositories.jpa.interfaces;

import csc258.domain.db.user.UserDomain;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paril on 4/16/2017.
 */
public interface IUserRepository extends CrudRepository<UserDomain, String> {
    UserDomain findByDeviceId(String deviceId);
}