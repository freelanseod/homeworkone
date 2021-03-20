package appmanager;

import model.UserData;
import model.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;
    private final ApplicationManager app;

    public DbHelper(ApplicationManager app) {
        this.app = app;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Users enabledUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery("from UserData where username != 'administrator' and enabled = '1'").list();
        session.getTransaction().commit();
        session.close();
        return new Users(result);
    }
}
