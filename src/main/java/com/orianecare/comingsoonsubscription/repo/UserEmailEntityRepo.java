/**
 * 
 */
package com.orianecare.comingsoonsubscription.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.orianecare.comingsoonsubscription.entity.UserEmailEntity;

/**
 * @author vennelakanti
 *
 */
@Repository
public interface UserEmailEntityRepo extends JpaRepository<UserEmailEntity, Integer> {

}
