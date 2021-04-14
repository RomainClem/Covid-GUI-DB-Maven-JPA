import javax.persistence.*;
import java.util.List;

public class Main {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    public static void main(String[] args) {
        addName(1, "Jean", "Aldfred", "Touret");
        addName(2, "toto", "lol", "alberto");
        addName(3, "neaj", "kek", "rpoger");
        addName(4, "Rom", "wouf", "chien");

        getName(1);
        getNames();
        changeFName(4, "Mark");

        deleteName(4);


        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addName(int id, String fName, String lName, String mName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Name name = new Name();
            name.setId(id);
            name.setFirstName(fName);
            name.setMiddleName(mName);
            name.setLastName(lName);
            em.persist(name);
            et.commit();
        }
        catch(Exception ex){
            if(et != null) et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void getName(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT n FROM Name n WHERE n.id = :ID";

        TypedQuery<Name> tq = em.createQuery(query, Name.class);
        tq.setParameter("ID", id);
        Name name;
        try {
            name = tq.getSingleResult();
            System.out.println(name.getFirstName() + " " + name.getLastName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }

    }

    public static void getNames(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT n FROM Name n WHERE n.id IS NOT NULL";
        TypedQuery<Name> tq =  em.createQuery(strQuery, Name.class);
        List<Name> names;
        try {
            names = tq.getResultList();
            names.forEach(name -> System.out.println(name.getFirstName() + " " + name.getLastName()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void changeFName(int id, String fName){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Name name = null;
        try {
            et = em.getTransaction();
            et.begin();
            name = em.find(Name.class, id);
            name.setFirstName(fName);

            em.persist(name);
            et.commit();
        }
        catch(Exception ex){
            if(et != null) et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    public static void deleteName(int id){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        Name name = null;

        try {
            et = em.getTransaction();
            et.begin();
            name = em.find(Name.class, id);
            em.remove(name);
            em.persist(name);
            et.commit();
        }
        catch(Exception ex){
            if(et != null) et.rollback();
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
    }
}
