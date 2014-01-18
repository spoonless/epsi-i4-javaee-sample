package epsi.i4.serie;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSerie {
	
	private EJBContainer ejbContainer;

	@Before
	public void startEJBContainer() throws Exception {
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("/openejb.properties"));

		ejbContainer = EJBContainer.createEJBContainer(properties);
		ejbContainer.getContext().bind("inject", this);
	}

	@After
	public void closeEJBContainer() {
		ejbContainer.close();
	}
	
	@EJB
	private SerieStatelessEjb serieStatelessEjb;
	
	@Test
	public void canCreateSerie() throws Exception {
		Serie serie = new Serie();
		serie.setName("My Favorite Serie");
		serie.setCreationYear(2010);
		
		serieStatelessEjb.create(serie);
		
		assertNotNull(serie.getId());
		assertNotNull(serieStatelessEjb.get(serie.getId()));
	}

	@Test
	public void canUpdateSerie() throws Exception {
		Serie serie = new Serie();
		serie.setName("My Favorite Serie");
		serie.setCreationYear(2010);
		
		serieStatelessEjb.create(serie);
		serie.setCreationYear(2012);
		serieStatelessEjb.update(serie);
		
		assertNotNull(serie.getId());
		assertEquals(2012, serieStatelessEjb.get(serie.getId()).getCreationYear());
	}

	@Test
	public void canDeleteSerie() throws Exception {
		Serie serie = new Serie();
		serie.setName("My Favorite Serie");
		serie.setCreationYear(2010);
		
		serieStatelessEjb.create(serie);
		serieStatelessEjb.delete(serie);
		
		assertNull(serieStatelessEjb.get(serie.getId()));
	}
}
