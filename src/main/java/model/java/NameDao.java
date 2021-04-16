package model.java;

import javax.persistence.*;
import java.util.List;


public class NameDao implements Dao<Name> {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    @Override
    public Name get(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT n FROM Name n WHERE n.id = :ID";

        TypedQuery<Name> tq = em.createQuery(query, Name.class);
        tq.setParameter("ID", id);
        Name name = null;
        try {
            name = tq.getSingleResult();
            System.out.println(name.getFirstName() + " " + name.getLastName());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return name;
    }

    @Override
    public List<Name> getAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT n FROM Name n WHERE n.id IS NOT NULL";
        TypedQuery<Name> tq =  em.createQuery(strQuery, Name.class);
        List<Name> names = null;
        try {
            names = tq.getResultList();
            names.forEach(name -> System.out.println(name.getFirstName() + " " + name.getLastName()));
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return names;
    }

    @Override
    public void save(Name name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
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

    @Override
    public void update(Name name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            name = em.find(Name.class, name.getId());
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

    @Override
    public void delete(Name name) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            name = em.find(Name.class, name.getId());
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
