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
import org.web.service.DocumentaryManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class })
public class TestPersistence {

	private static final String DOCUMENTARY_NAME = "Sunset Over Selungo";
	private static final String DOCUMENTARY_CATEGORY = "Society";

	@Autowired
	private DocumentaryManager documentaryManager;

	@Test
	public void testPersistance() {

		Documentary doc = new Documentary();
		doc.setName(DOCUMENTARY_NAME);
		doc.setCategory(DOCUMENTARY_CATEGORY);

		documentaryManager.saveOrUpdate(doc);

		List<Documentary> documentaryList = documentaryManager
				.getAllDocumentaries();

		assertThat(documentaryList.size(), is(greaterThanOrEqualTo(1)));
		assertThat(documentaryList.contains(doc), is(true));

		for (Documentary d : documentaryList) {
			if (d.getName().equals(doc.getName())
					&& d.getCategory().equals(doc.getCategory())) {
				documentaryManager.delete(d.getId());
			}
		}
		documentaryList = documentaryManager.getAllDocumentaries();
		assertThat(documentaryList.contains(doc), is(false));
	}

}
