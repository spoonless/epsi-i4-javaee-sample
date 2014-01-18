package epsi.i4.serie;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SerieStatelessEjb {
	
	@PersistenceContext(unitName="serie-persistence-unit")
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction userTransaction;
	
	public void create(Serie serie) throws Exception {
		userTransaction.begin();
		entityManager.persist(serie);
		userTransaction.commit();
	}
	
	public void update(Serie serie) throws Exception {
		userTransaction.begin();
		entityManager.merge(serie);
		userTransaction.commit();
	}

	public void delete(Serie serie) throws Exception {
		userTransaction.begin();
		entityManager.createQuery("delete from Serie s where s.id = :id")
		             .setParameter("id", serie.getId())
		             .executeUpdate();
		userTransaction.commit();
	}
	
	public Serie get(long id) {
		return entityManager.find(Serie.class, id);
	}
}
