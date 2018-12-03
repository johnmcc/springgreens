package helpers;

import com.sun.jndi.toolkit.url.Uri;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.Optional;

public class ObjectBuilder {

//    @Autowired
//    static
//    EntityManager entityManager;
//
//    @Transactional
//    public static Object build(String url, Class klass){
//        Long id = findIdFromString(url);
//        Session session = entityManager.unwrap(Session.class);
//        Object result = null;
//
//        try {
//            Criteria cr = session.createCriteria(klass);
//            cr.add(Restrictions.eq("id", id));
//
//            result = cr.uniqueResult();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        return result;
//    }

//    public static <T> T build(String url, JpaRepository repo){
//        Long id = findIdFromString(url);
//        Optional foundObject = repo.findById(id);
//        T object = null;
//        if(foundObject.isPresent()){
//            object = (T) foundObject.get();
//            return object;
//        }
//
//        return null;
//    }

    public static Long findIdFromString(String uriString) {
        Uri objUri = null;
        try {
            objUri = new Uri(uriString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String objectPath = objUri.getPath();
        String objectStrId = objectPath.substring(objectPath.lastIndexOf('/') + 1);
        Long objectId = Long.parseLong(objectStrId);
        return objectId;
    }
}
