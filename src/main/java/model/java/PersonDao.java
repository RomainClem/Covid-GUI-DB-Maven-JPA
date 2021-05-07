package model.java;

import javax.persistence.*;
import java.util.List;


public class PersonDao implements Dao<Person> {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    @Override
    public Person get(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.id = :ID";

        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        tq.setParameter("ID", id);
        Person person = null;
        try {
            person = tq.getSingleResult();
            NameDao nameDao = new NameDao();
            Name name = nameDao.get(person.getId());
            person.setName(name);
            System.out.println(person);
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT p FROM Person p WHERE p.id IS NOT NULL";
        TypedQuery<Person> tq =  em.createQuery(strQuery, Person.class);
        List<Person> persons = null;

        try {
            persons = tq.getResultList();
            persons.forEach(person -> {
                NameDao nameDao = new NameDao();
                Name name = nameDao.get(person.getId());
                person.setName(name);
                System.out.println(person);
            });
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return persons;
    }

    @Override
    public void save(Person person) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(person);
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
    public void update(Person person) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            person = em.find(Person.class, person.getId());
            em.persist(person);
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
    public void delete(Person person) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            person = em.find(Person.class, person.getId());
            em.remove(person);
            em.persist(person);
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