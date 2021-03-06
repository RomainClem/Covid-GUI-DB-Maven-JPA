package model.java;

import javax.persistence.*;
import java.util.List;


public class NameDao implements Dao<Name> {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    @Override
    public Name get(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT n FROM Name n WHERE n.id = :name_id";

        TypedQuery<Name> tq = em.createQuery(query, Name.class);
        tq.setParameter("name_id", id);
        Name name = null;
        try {
            name = tq.getSingleResult();
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
    public void update(Name newName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Name name = em.find(Name.class, newName.getId());
            name.setFirstName(newName.getFirstName());
            name.setMiddleName(newName.getMiddleName());
            name.setLastName(newName.getLastName());
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
    public void delete(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Name name = em.find(Name.class, id);
            em.remove(name);
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
