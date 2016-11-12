package au.com.mason.authservice.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import au.com.mason.authservice.domain.User;

@Repository
@Transactional
public class UserDao {
  
  /**
   * Save the user in the database.
   */
  public void create(User user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List getAll() {
    return entityManager.createQuery("from User").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public User validateUser(User user) {
    List<User> users = (List<User>) entityManager.createQuery(
        "from User where userName = :userName and password = :password")
        .setParameter("userName", user.getUserName())
        .setParameter("password", user.getPassword())
        .getResultList();
    
    if (users.size() > 0) {
    	return users.get(0);
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
