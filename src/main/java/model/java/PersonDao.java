package model.java;

import javax.persistence.*;
import java.util.List;


public class PersonDao implements Dao<Person> {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    @Override
    public Person get(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT p FROM Person p WHERE p.id = :person_id";

        TypedQuery<Person> tq = em.createQuery(query, Person.class);
        tq.setParameter("person_id", id);
        Person person = null;
        try {
            person = tq.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println(ex.getMessage());
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
    public void update(Person newPerson) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Person person = em.find(Person.class, newPerson.getId());
            person.setEmail(newPerson.getEmail());
            person.setPhone(newPerson.getPhone());
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
    public void delete(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Person person = em.find(Person.class, id);
            em.remove(person);
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