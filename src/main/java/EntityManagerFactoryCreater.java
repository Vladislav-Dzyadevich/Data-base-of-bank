import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryCreater {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory entityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("JPATest");
        }
        return emf;
    }
}
