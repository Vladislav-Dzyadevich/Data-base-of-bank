import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class Repository {

    static EntityManagerFactory emf = EntityManagerFactoryCreater.entityManagerFactory();
    static EntityManager em;


    public void transfer(String acFrom, String currency, long sum, String acTo) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Query checkCurrencyFrom = em.createQuery("SELECT coefficient FROM Course c WHERE c.from = :currency and c.to = (SELECT currency from Account a WHERE a.card = :acFrom)", Double.class);
        checkCurrencyFrom.setParameter("currency", Currency.valueOf(currency));
        checkCurrencyFrom.setParameter("acFrom", acFrom);
        double coefficientFrom = (double) checkCurrencyFrom.getSingleResult();
        double sumFrom = coefficientFrom * sum;

        Query checkCurrencyTo = em.createQuery("SELECT coefficient FROM Course c WHERE c.from = :currency and c.to = (SELECT currency FROM Account a WHERE a.card = :acTo)", Double.class);
        checkCurrencyTo.setParameter("currency", Currency.valueOf(currency));
        checkCurrencyTo.setParameter("acTo", acTo);
        double coefficientTo = (double) checkCurrencyTo.getSingleResult();
        double sumTo = coefficientTo * sum;

        Query transferFrom = em.createQuery("UPDATE Account a SET a.sum = a.sum - :sumFrom WHERE a.card = :acFrom");
        transferFrom.setParameter("acFrom", acFrom);
        transferFrom.setParameter("sumFrom", sumFrom);
        transferFrom.executeUpdate();

        Query transferTo = em.createQuery("UPDATE Account a SET a.sum = a.sum + :sumTo WHERE a.card = :acTo");
        transferTo.setParameter("acTo", acTo);
        transferTo.setParameter("sumTo", sumTo);
        transferTo.executeUpdate();

        em.getTransaction().commit();
    }

    public void moneyTransfer(String ac, String currency, long sum) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("SELECT coefficient FROM Course c WHERE c.from = :currency and c.to = (SELECT currency FROM Account a WHERE a.card = :ac)", Double.class);
        query.setParameter("currency", Currency.valueOf(currency));
        query.setParameter("ac", ac);
        double coefficient = (double) query.getSingleResult();
        double cof = coefficient * sum;

        Query queryTransfer = em.createQuery("UPDATE Account a SET a.sum = a.sum + :cof WHERE a.card = :ac");
        queryTransfer.setParameter("ac", ac);
        queryTransfer.setParameter("cof", cof);
        queryTransfer.executeUpdate();
        em.getTransaction().commit();
    }

    public void conversion(String ac, String currency) {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        Query queryCoefficient = em.createQuery("SELECT coefficient FROM Course c WHERE c.from = (SELECT  currency FROM Account a WHERE a.card = :ac) and c.to = :currency", Double.class);
        queryCoefficient.setParameter("currency", Currency.valueOf(currency));
        queryCoefficient.setParameter("ac", ac);
        double coefficient = (double) queryCoefficient.getSingleResult();

        Query queryConversionCurrency = em.createQuery("UPDATE Account a SET a.currency = :currency WHERE a.card = :ac");
        queryConversionCurrency.setParameter("currency", Currency.valueOf(currency));
        queryConversionCurrency.setParameter("ac", ac);
        queryConversionCurrency.executeUpdate();

        Query queryConversionMoney = em.createQuery("UPDATE Account a SET a.sum = a.sum * :coefficient WHERE a.card = :ac");
        queryConversionMoney.setParameter("ac", ac);
        queryConversionMoney.setParameter("coefficient", coefficient);
        queryConversionMoney.executeUpdate();
        em.getTransaction().commit();

    }

    public void getMoneyUan(String ac) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Query queryResultSum = em.createQuery("SELECT a.sum, a.currency FROM Account a WHERE a.card = :ac");
        queryResultSum.setParameter("ac", ac);
        List<Object[]> list = queryResultSum.getResultList();
        Object[] results = list.get(0);
        double sum = (double) results[0];
        Currency currency = (Currency) results[1];

        Query queryResultCof = em.createQuery("SELECT c.coefficient FROM Course c WHERE c.from = :currency and c.to = 'UAN'");
        queryResultCof.setParameter("currency", currency);
        double coefficient = (double) queryResultCof.getSingleResult();
        System.out.println(coefficient * sum);
    }

}
