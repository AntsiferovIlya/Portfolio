import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metadata = new MetadataSources(registry)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

                                  Создание таблицы LinkedPurchaseList
        String create = "CREATE TABLE IF NOT EXISTS linkedpurchaselist " +
                "(student_id INT UNSIGNED NOT NULL," +
                "course_id INT UNSIGNED NOT NULL)";
        Query query = session.createSQLQuery(create).addEntity(LinkedPurchaseList.class);
        query.executeUpdate();


                              Заполнение таблицы LinkedPurchaseList
        String insertFromStudent = "insert into linkedpurchaselist (student_id, course_id) " +
                "select (select id from students where students.name = purchaselist.student_name) as student_id," +
                "(select id from courses where courses.name = purchaselist.course_name) as course_id " +
                "from purchaselist";
        Query query = session.createSQLQuery(insertFromStudent);
        query.executeUpdate();

        String hql = "From " + LinkedPurchaseList.class.getSimpleName();
        List<LinkedPurchaseList> linkedPurchaseLists =  session.createQuery(hql).getResultList();
         for(LinkedPurchaseList lpl : linkedPurchaseLists) {
             System.out.println(lpl.getId().getStudentId() + " - " + lpl.getId().getCourseId());
         }


        transaction.commit();
        sessionFactory.close();
    }

}

