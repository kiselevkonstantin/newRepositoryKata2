package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getFactory().getCurrentSession();
        User user = new User(name, lastName, age);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
//
//    Hibernate открывает отличные возможности по управлению БД без скриптов:
//        Добавление пользователя в БД: session.save(user);
//        Получение пользователя по id, чтобы потом удалить: User user = (User) session.get(User.class, id);
//        Получение списка всех пользователей: List<User> users = session.createCriteria(User.class).list();
//        Всё это быстро гуглится, лучше ещё дополнительно почитайте доки по всем незнакомым методам в интернете.
