package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public void add(User user) {
      entityManager.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query= (TypedQuery<User>) entityManager.createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User show(Long id) {
      return entityManager.find(User.class, id);
   }

   @Transactional
   @Override
   public void update(Long id, User user) {

      User userToBeUpdated = entityManager.find(User.class, id);

      userToBeUpdated.setFirstName(user.getFirstName());
      userToBeUpdated.setLastName(user.getLastName());
      userToBeUpdated.setEmail(user.getEmail());

      entityManager.merge(userToBeUpdated);

   }

   @Override
   public void delete(Long id) {
      User userToDelete = entityManager.find(User.class, id);
      entityManager.remove(userToDelete);
   }
}
