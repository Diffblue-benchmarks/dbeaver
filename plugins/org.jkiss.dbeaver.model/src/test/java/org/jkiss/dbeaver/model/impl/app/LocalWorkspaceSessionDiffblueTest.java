package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.auth.SMSessionPrincipal;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.secret.DBSSecretController;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalWorkspaceSessionDiffblueTest {
  /**
   * Test {@link LocalWorkspaceSession#LocalWorkspaceSession(DBPWorkspace)}.
   *
   * <p>Method under test: {@link LocalWorkspaceSession#LocalWorkspaceSession(DBPWorkspace)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalWorkspaceSession.<init>(DBPWorkspace)"})
  public void testNewLocalWorkspaceSession() throws DBException {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);

    // Act
    LocalWorkspaceSession actualLocalWorkspaceSession = new LocalWorkspaceSession(workspace);

    // Assert
    DBSSecretController secretController = actualLocalWorkspaceSession.getSecretController();
    assertTrue(secretController instanceof LocalSecretController);
    assertEquals("local", actualLocalWorkspaceSession.getUserDomain());
    assertNull(actualLocalWorkspaceSession.getSessionId());
    assertNull(actualLocalWorkspaceSession.getSessionContext());
    assertEquals(15L, secretController.getSupportedFeatures());
    assertTrue(actualLocalWorkspaceSession.getAttributes().isEmpty());
    assertEquals(System.getProperty("user.name"), actualLocalWorkspaceSession.getUserName());
    assertSame(workspace, actualLocalWorkspaceSession.getSessionSpace());
  }

  /**
   * Test {@link LocalWorkspaceSession#getSessionContext()}.
   *
   * <p>Method under test: {@link LocalWorkspaceSession#getSessionContext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSessionContext LocalWorkspaceSession.getSessionContext()"})
  public void testGetSessionContext() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContextImpl = new SessionContextImpl(mock(SMSessionContext.class));
    when(workspace.getAuthContext()).thenReturn(sessionContextImpl);

    // Act
    SMSessionContext actualSessionContext =
        new LocalWorkspaceSession(workspace).getSessionContext();

    // Assert
    verify(workspace).getAuthContext();
    assertTrue(actualSessionContext instanceof SessionContextImpl);
    assertNull(actualSessionContext.getPrimaryAuthSpace());
    assertSame(sessionContextImpl, actualSessionContext);
  }

  /**
   * Test {@link LocalWorkspaceSession#getSessionPrincipal()}.
   *
   * <p>Method under test: {@link LocalWorkspaceSession#getSessionPrincipal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMSessionPrincipal LocalWorkspaceSession.getSessionPrincipal()"})
  public void testGetSessionPrincipal() {
    // Arrange
    LocalWorkspaceSession localWorkspaceSession =
        new LocalWorkspaceSession(mock(DBPWorkspace.class));

    // Act
    SMSessionPrincipal actualSessionPrincipal = localWorkspaceSession.getSessionPrincipal();

    // Assert
    assertSame(localWorkspaceSession, actualSessionPrincipal);
  }

  /**
   * Test {@link LocalWorkspaceSession#getSessionId()}.
   *
   * <p>Method under test: {@link LocalWorkspaceSession#getSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String LocalWorkspaceSession.getSessionId()"})
  public void testGetSessionId() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    when(workspace.getWorkspaceId()).thenReturn("42");

    // Act
    String actualSessionId = new LocalWorkspaceSession(workspace).getSessionId();

    // Assert
    verify(workspace).getWorkspaceId();
    assertEquals("42", actualSessionId);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalWorkspaceSession#getSecretController()}
   *   <li>{@link LocalWorkspaceSession#getSessionSpace()}
   *   <li>{@link LocalWorkspaceSession#getSessionStart()}
   *   <li>{@link LocalWorkspaceSession#getUserDomain()}
   *   <li>{@link LocalWorkspaceSession#getUserName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController LocalWorkspaceSession.getSecretController()",
    "org.jkiss.dbeaver.model.auth.SMAuthSpace LocalWorkspaceSession.getSessionSpace()",
    "java.time.LocalDateTime LocalWorkspaceSession.getSessionStart()",
    "String LocalWorkspaceSession.getUserDomain()",
    "String LocalWorkspaceSession.getUserName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalWorkspaceSession localWorkspaceSession =
        new LocalWorkspaceSession(mock(DBPWorkspace.class));

    // Act
    DBSSecretController actualSecretController = localWorkspaceSession.getSecretController();
    localWorkspaceSession.getSessionSpace();
    localWorkspaceSession.getSessionStart();
    String actualUserDomain = localWorkspaceSession.getUserDomain();

    // Assert
    assertTrue(actualSecretController instanceof LocalSecretController);
    assertEquals("local", actualUserDomain);
    assertEquals(System.getProperty("user.name"), localWorkspaceSession.getUserName());
  }
}
