package manager;

import manager.hbm.GroupRecord;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase{

    private SessionFactory sessionFactory;
    public HibernateHelper(ApplicationManager manager){
        super(manager);
        SessionFactory sessionFactory =
                new Configuration()
//                        .addAnnotatedClass(Book.class)
                       .addAnnotatedClass(GroupRecord.class)
                        // PostgreSQL
                        .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
                        // Credentials
                        .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
                        .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                        .buildSessionFactory();
    }

    static List<GroupData> converList(List<GroupRecord> records){
        List<GroupData> result = new ArrayList<>();
        for (var record : records){
            result.add(convert(record));
        }
        return result;
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    public List<GroupData> getGroupList(){
        return converList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list();
        }));
    }
}
