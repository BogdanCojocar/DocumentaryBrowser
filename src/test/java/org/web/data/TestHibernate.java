package org.web.data;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web.config.HibernateConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class })
public class TestHibernate {

	private static final String DOCUMENTARY_NAME = "Sunset Over Selungo";
	private static final String DOCUMENTARY_CATEGORY = "Society";
	
	@Autowired
	private DocumentaryDAO documentaryDAO;
	
	@Test
	public void testHibernate() {
		
		Documentary doc = new Documentary();
		doc.setName(DOCUMENTARY_NAME);
		doc.setCategory(DOCUMENTARY_CATEGORY);
		
		documentaryDAO.saveOrUpdate(doc);
		
		List<Documentary> documentaryList = documentaryDAO.getAllDocumentaries();
		
		assertThat(documentaryList.size(), is(greaterThanOrEqualTo(1)));
		assertThat(documentaryList.contains(doc), is(true));

	}

}
