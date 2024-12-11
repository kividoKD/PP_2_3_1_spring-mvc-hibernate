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
   public void addUser(User user) {
      entityManager.persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query= (TypedQuery<User>) entityManager.createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User showUser(Long id) {
      return entityManager.find(User.class, id);
   }

   @Override
   public void updateUser(Long id, User user) {

      User userToBeUpdated = entityManager.find(User.class, id);

      userToBeUpdated.setFirstName(user.getFirstName());
      userToBeUpdated.setLastName(user.getLastName());
      userToBeUpdated.setEmail(user.getEmail());
   }

   @Override
   public void deleteUser(Long id) {
      User userToDelete = entityManager.find(User.class, id);
      entityManager.remove(userToDelete);
   }
}
