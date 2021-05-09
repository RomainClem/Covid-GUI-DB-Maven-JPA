package model.java;

import javax.persistence.*;
import java.util.List;

public class ContactDao implements Dao<Contact> {
    private final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("FinalProjectMavenJPA");

    @Override
    public Contact get(int id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT c FROM Contact c WHERE c.id = :contact_id";

        TypedQuery<Contact> tq = em.createQuery(query, Contact.class);
        tq.setParameter("contact_id", id);
        Contact contact = null;
        try {
            contact = tq.getSingleResult();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return contact;
    }

    @Override
    public List<Contact> getAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String strQuery = "SELECT c FROM Contact c WHERE c.id IS NOT NULL";
        TypedQuery<Contact> tq =  em.createQuery(strQuery, Contact.class);
        List<Contact> contacts = null;
        try {
            contacts = tq.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        finally {
            em.close();
        }
        return contacts;
    }

    @Override
    public void save(Contact contact) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            em.persist(contact);
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
    public void update(Contact newContact) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Contact contact = em.find(Contact.class, newContact.getId());
            contact.setPersonId1(newContact.getPersonId1());
            contact.setPersonId2(newContact.getPersonId2());
            contact.setDateContact(newContact.getDateContact());
            em.persist(contact);
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
            Contact contact = em.find(Contact.class, id);
            em.remove(contact);
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
