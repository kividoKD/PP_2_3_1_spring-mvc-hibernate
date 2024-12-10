package hiber.dao;

import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final EntityManagerFactory entityManagerFactory;

   @Autowired
   public UserDaoImp(EntityManagerFactory entityManagerFactory) {
       this.entityManagerFactory = entityManagerFactory;
   }

   @Override
   public void add(User user) {
      entityManagerFactory.createEntityManager().persist(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query= (TypedQuery<User>) entityManagerFactory.createEntityManager().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User show(Long id) {
      return entityManagerFactory.createEntityManager().find(User.class, id);
   }

   @Override
   public void update(Long id, User user) {
      User userToBeUpdated = entityManagerFactory.createEntityManager().find(User.class, id);
      userToBeUpdated.setFirstName(user.getFirstName());
      userToBeUpdated.setLastName(user.getLastName());
      userToBeUpdated.setEmail(user.getEmail());
      entityManagerFactory.createEntityManager().merge(userToBeUpdated);
   }

   @Override
   public void delete(Long id) {
      entityManagerFactory.createEntityManager().remove(id);
   }
}
