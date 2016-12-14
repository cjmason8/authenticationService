package au.com.mason.authservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import au.com.mason.authservice.domain.UserApplicationRole;

@Repository
@Transactional
public class UserApplicationRoleDao {
  
  /**
   * Save the userApplication in the database.
   */
  public void create(UserApplicationRole userApplicationRole) {
    entityManager.persist(userApplicationRole);
    return;
  }
  
  // Private fields
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
}
