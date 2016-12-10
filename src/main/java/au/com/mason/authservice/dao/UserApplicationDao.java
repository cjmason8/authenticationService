package au.com.mason.authservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import au.com.mason.authservice.domain.User;
import au.com.mason.authservice.domain.UserApplication;
import au.com.mason.authservice.dto.LoginInput;

@Repository
@Transactional
public class UserApplicationDao {
  
  /**
   * Save the userApplication in the database.
   */
  public void create(UserApplication userApplication) {
    entityManager.persist(userApplication);
    return;
  }
  
  /**
   * Delete the userApplication from the database.
   */
  public void delete(UserApplication userApplication) {
    if (entityManager.contains(userApplication))
      entityManager.remove(userApplication);
    else
      entityManager.remove(entityManager.merge(userApplication));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List getAll() {
    return entityManager.createQuery("from UserApplication").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public UserApplication validateUser(LoginInput loginInput) {
    List<UserApplication> userApplications = (List<UserApplication>) entityManager.createQuery(
        "from UserApplication where user.userName = :userName and user.password = :password and applicationType = :applicationType")
        .setParameter("userName", loginInput.getUserName())
        .setParameter("password", loginInput.getPassword())
        .setParameter("applicationType", loginInput.getApplicationType())
        .getResultList();
    
    if (userApplications.size() > 0) {
    	return userApplications.get(0);
    }
    
    return null;
  }
  
  /**
   * Return the user having the passed email.
   */
  public UserApplication findByUserName(LoginInput loginInput) {
    List<UserApplication> userApplications = (List<UserApplication>) entityManager.createQuery(
        "from UserApplication where user.userName = :userName and applicationType = :applicationType")
        .setParameter("userName", loginInput.getUserName())
        .setParameter("applicationType", loginInput.getApplicationType())
        .getResultList();
    
    if (userApplications.size() > 0) {
    	return userApplications.get(0);
    }
    
    return null;
  }  

  /**
   * Return the user having the passed id.
   */
  public User getById(long id) {
    return entityManager.find(User.class, id);
  }

  /**
   * Update the passed user in the database.
   */
  public void update(User user) {
    entityManager.merge(user);
    return;
  }

  // Private fields
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
}
