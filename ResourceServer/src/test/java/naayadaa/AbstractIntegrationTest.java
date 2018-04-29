package naayadaa;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AnastasiiaDepenchuk on 29-Apr-18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public abstract class AbstractIntegrationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @After
    public void after() throws SQLException {

        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        String resetSqlTemplate = "ALTER TABLE %s ALTER COLUMN id RESTART WITH 1";
        try (Connection dbConnection = dataSource.getConnection()) {
            //Create SQL statements that reset the auto increment columns and invoke
            //the created SQL statements.
            try (Statement statement = dbConnection.createStatement()) {
                String resetSql = String.format(resetSqlTemplate, "client");
                statement.execute(resetSql);
            }

        }
    }

    protected RequestPostProcessor addBearerToken(final String username, String... authorities) {
        return mockRequest -> {
            Set<String> scope = new HashSet<>();
            scope.add("read");
            scope.add("write");
            // Create OAuth2 token
            OAuth2Request oauth2Request = new OAuth2Request(null, "clientJournalResource", null, true, scope, null, null, null, null);
            Authentication userauth = new TestingAuthenticationToken(username, null, authorities);
            OAuth2Authentication oauth2auth = new OAuth2Authentication(oauth2Request, userauth);
            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oauth2auth);

            // Set Authorization header to use Bearer
            mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
            return mockRequest;
        };
    }


}