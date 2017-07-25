package com.krista.auth.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.krista.auth.models.User_Role;

@Repository
public interface User_RoleRepository extends CrudRepository<User_Role, Long> {

}
