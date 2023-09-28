package com.ravi.formValidation.Repository;

import org.springframework.data.repository.CrudRepository;

import com.ravi.formValidation.Entities.User;

public interface UserRepo extends CrudRepository<User,Long>{
	
}
