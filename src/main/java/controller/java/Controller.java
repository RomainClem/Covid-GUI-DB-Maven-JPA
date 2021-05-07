package controller.java;

import model.java.Name;
import model.java.NameDao;
import model.java.Person;
import model.java.PersonDao;

import java.util.List;

public class Controller {

   public void addPerson(List<Object> personInfo){
       Name name = new Name((int) personInfo.get(3), (String) personInfo.get(0),(String) personInfo.get(1),(String) personInfo.get(2));
       Person person = new Person(name, (String) personInfo.get(4), (String) personInfo.get(5), (int) personInfo.get(3));

       try {
           NameDao nameDao = new NameDao();
           nameDao.save(name);

           PersonDao personDao = new PersonDao();
           personDao.save(person);

       } catch (Exception e){
           e.printStackTrace();
       }




   }

}
