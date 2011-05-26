package obecnosci.ob.service;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import obecnosci.ob.domain.Admin;



@Stateless
public class AdminManager {
@PersistenceContext
	EntityManager em;
	
	public Admin zaloguj(String login, String haslo){
		Admin admin = (Admin) em.createQuery("select s from Admin s where s.login = :login and s.haslo = :haslo").setParameter("login", login).setParameter("haslo", haslo).getSingleResult();
		return admin;
	}
}