package au.com.mason.expenseManager.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;

import au.com.mason.expenseManager.domain.SessionToken;

@Repository
@Transactional
public class SessionTokenDao {
  
  /**
   * Save the sessionToken in the database.
   */
  public void create(SessionToken sessionToken) {
    entityManager.persist(sessionToken);
    return;
  }
  
  /**
   * Delete the sessionToken from the database.
   */
  public void delete(SessionToken sessionToken) {
    if (entityManager.contains(sessionToken))
      entityManager.remove(sessionToken);
    else
      entityManager.remove(entityManager.merge(sessionToken));
    return;
  }
  
  /**
   * Return all the sessionTokens stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List getAll() {
    return entityManager.createQuery("from SessionToken").getResultList();
  }
  
  /**
   * Return the user having the passed email.
   */
  public boolean validateSessionToken(String token) {
    List results = entityManager.createQuery(
        "from SessionToke where token = :token")
        .setParameter("token", token)
        .getResultList();
    
    if (results.size() > 0) {
    	SessionToken sessionToken = (SessionToken) results.get(0);
    	if (LocalDateTime.now().isBefore(sessionToken.getExpiryDateTime())) {
    		return true;
    	}
    }
    
    return false;
  }

  // Private fields
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
  
}
