package addressbook.tests;

import addressbook.model.AddressData;
import addressbook.model.GroupData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class HibernateConnectionTest {
    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @Test
    public void hbConnectionGroupData() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list(); //language OQL
        for (GroupData groupData : result) {
            System.out.println(groupData);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void hbConnectionContactData() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<AddressData> result = session.createQuery("from AddressData where deprecated = '0000-00-00'").list(); //language OQL
        session.getTransaction().commit();
        session.close();

        for (AddressData addressData : result) {
            System.out.println(addressData);
            System.out.println(addressData.getGroups());
        }
    }

}
