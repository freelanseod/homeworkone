package addressbook.appmanager;

import addressbook.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list(); //language OQL
        session.getTransaction().commit();
        session.close();

        return new Groups(result);
    }

    public Addresses addresses() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<AddressData> result = session.createQuery("from AddressData where deprecated = '0000-00-00'").list(); //language OQL
        session.getTransaction().commit();
        session.close();

        return new Addresses(result);
    }
    
}
