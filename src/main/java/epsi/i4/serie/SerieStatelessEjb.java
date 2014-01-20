package epsi.i4.serie;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class SerieStatelessEjb {
	
	@PersistenceContext(unitName="serie-persistence-unit")
	private EntityManager entityManager;
	
	public void create(Serie serie) {
		entityManager.persist(serie);
	}
	
	public void update(Serie serie) {
		entityManager.merge(serie);
	}

	public void delete(Serie serie) {
		entityManager.createQuery("delete from Serie s where s.id = :id")
		             .setParameter("id", serie.getId())
		             .executeUpdate();
	}
	
	public Serie get(long id) {
		return entityManager.find(Serie.class, id);
	}

	public Serie getByName(String name) {
		try {
			return entityManager.createQuery("select s from Serie s where s.name = :name", Serie.class)
			                    .setParameter("name", name)
			                    .getSingleResult();
		}
		catch (NoResultException e) {
			return null;
		}
	}

	public List<Season> getSeasons(long id) {
		return new ArrayList<Season>(entityManager.getReference(Serie.class, id).getSeasons());
	}
}
