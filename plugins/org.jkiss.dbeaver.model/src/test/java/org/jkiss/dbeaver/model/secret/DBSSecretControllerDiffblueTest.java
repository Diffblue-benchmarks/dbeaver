package org.jkiss.dbeaver.model.secret;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSession;
import org.jkiss.dbeaver.model.impl.app.LocalSecretController;
import org.jkiss.dbeaver.model.impl.app.LocalWorkspaceSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSSecretControllerDiffblueTest {
  /**
   * Test {@link DBSSecretController#getSupportedFeatures()}.
   *
   * <p>Method under test: {@link DBSSecretController#getSupportedFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long DBSSecretController.getSupportedFeatures()"})
  public void testGetSupportedFeatures() throws DBException {
    // Arrange, Act and Assert
    assertEquals(15L, LocalSecretController.INSTANCE.getSupportedFeatures());
  }

  /**
   * Test {@link DBSSecretController#listAllSharedSecrets(DBSSecretObject)}.
   *
   * <p>Method under test: {@link DBSSecretController#listAllSharedSecrets(DBSSecretObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.List DBSSecretController.listAllSharedSecrets(DBSSecretObject)"})
  public void testListAllSharedSecrets() throws DBException {
    // Arrange, Act and Assert
    assertTrue(
        LocalSecretController.INSTANCE.listAllSharedSecrets(mock(DBSSecretObject.class)).isEmpty());
  }

  /**
   * Test {@link DBSSecretController#getProjectSecretController(DBPProject)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getProjectSecretController(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getProjectSecretController(DBPProject)"
  })
  public void testGetProjectSecretController_givenNull_thenThrowDBException() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspaceSession()).thenReturn(null);

    // Act and Assert
    assertThrows(DBException.class, () -> DBSSecretController.getProjectSecretController(project));
    verify(project).getWorkspaceSession();
  }

  /**
   * Test {@link DBSSecretController#getProjectSecretController(DBPProject)}.
   *
   * <ul>
   *   <li>Then return {@link LocalSecretController}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getProjectSecretController(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getProjectSecretController(DBPProject)"
  })
  public void testGetProjectSecretController_thenReturnLocalSecretController() throws DBException {
    // Arrange
    DBPProject project = mock(DBPProject.class);
    when(project.getWorkspaceSession())
        .thenReturn(new LocalWorkspaceSession(mock(DBPWorkspace.class)));

    // Act
    DBSSecretController actualProjectSecretController =
        DBSSecretController.getProjectSecretController(project);

    // Assert
    verify(project).getWorkspaceSession();
    assertTrue(actualProjectSecretController instanceof LocalSecretController);
    assertEquals(15L, actualProjectSecretController.getSupportedFeatures());
  }

  /**
   * Test {@link DBSSecretController#getSessionSecretController(SMSession)}.
   *
   * <ul>
   *   <li>Then return {@link LocalSecretController}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getSessionSecretController(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getSessionSecretController(SMSession)"
  })
  public void testGetSessionSecretController_thenReturnLocalSecretController() throws DBException {
    // Arrange
    LocalWorkspaceSession spaceSession = new LocalWorkspaceSession(mock(DBPWorkspace.class));

    // Act
    DBSSecretController actualSessionSecretController =
        DBSSecretController.getSessionSecretController(spaceSession);

    // Assert
    assertTrue(actualSessionSecretController instanceof LocalSecretController);
    assertEquals(15L, actualSessionSecretController.getSupportedFeatures());
    assertSame(
        ((LocalSecretController) actualSessionSecretController).INSTANCE,
        spaceSession.getSecretController());
  }

  /**
   * Test {@link DBSSecretController#getSessionSecretController(SMSession)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getSessionSecretController(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getSessionSecretController(SMSession)"
  })
  public void testGetSessionSecretController_whenNull_thenThrowDBException() throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> DBSSecretController.getSessionSecretController(null));
  }

  /**
   * Test {@link DBSSecretController#getSessionSecretControllerOrNull(SMSession)}.
   *
   * <ul>
   *   <li>Then return {@link LocalSecretController}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getSessionSecretControllerOrNull(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getSessionSecretControllerOrNull(SMSession)"
  })
  public void testGetSessionSecretControllerOrNull_thenReturnLocalSecretController()
      throws DBException {
    // Arrange
    LocalWorkspaceSession spaceSession = new LocalWorkspaceSession(mock(DBPWorkspace.class));

    // Act
    DBSSecretController actualSessionSecretControllerOrNull =
        DBSSecretController.getSessionSecretControllerOrNull(spaceSession);

    // Assert
    assertTrue(actualSessionSecretControllerOrNull instanceof LocalSecretController);
    assertEquals(15L, actualSessionSecretControllerOrNull.getSupportedFeatures());
    assertSame(
        ((LocalSecretController) actualSessionSecretControllerOrNull).INSTANCE,
        spaceSession.getSecretController());
  }

  /**
   * Test {@link DBSSecretController#getSessionSecretControllerOrNull(SMSession)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBSSecretController#getSessionSecretControllerOrNull(SMSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSSecretController DBSSecretController.getSessionSecretControllerOrNull(SMSession)"
  })
  public void testGetSessionSecretControllerOrNull_whenNull_thenReturnNull() throws DBException {
    // Arrange, Act and Assert
    assertNull(DBSSecretController.getSessionSecretControllerOrNull(null));
  }
}
