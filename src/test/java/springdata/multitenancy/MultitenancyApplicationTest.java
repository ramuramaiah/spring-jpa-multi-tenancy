package springdata.multitenancy;

import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import springdata.multitenancy.config.TestHibernateConfig;
import springdata.multitenancy.entity.Note;
import springdata.multitenancy.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = {
        MultiTenancyApplication.class,
        TestHibernateConfig.class
})
@ActiveProfiles("test")
public class MultitenancyApplicationTest {

    private static RestTemplate restTemplate;

    @BeforeClass
    public static void runBeforeAllTestMethods() throws JSONException {

        restTemplate = new RestTemplate();
    }

    @Test
    public void testNote() {
        {
            User jane = new User();
            jane.setUsername("jane");
            jane.setPassword("qwerty123");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> request = new HttpEntity<>(jane, headers);

            final String createUserUrl = "http://localhost:8080/api/default_tenant/users";

            User created = restTemplate.postForObject(createUserUrl, request, User.class);
            assertNotNull(created);
            assertEquals("jane", created.getUsername());
        }

        {
            Note note = new Note();
            note.setText("Hello from Jane");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Note> request = new HttpEntity<>(note, headers);

            final String createNoteUrl = "http://localhost:8080/api/jane/notes";

            Note created = restTemplate.postForObject(createNoteUrl, request, Note.class);
            assertNotNull(created);
            assertEquals("Hello from Jane", created.getText());
        }
    }

}
