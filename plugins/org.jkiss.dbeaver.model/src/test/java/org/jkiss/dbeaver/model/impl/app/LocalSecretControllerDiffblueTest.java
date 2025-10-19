package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.DBCFeatureNotSupportedException;
import org.jkiss.dbeaver.model.secret.DBSSecretObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalSecretControllerDiffblueTest {
  /**
   * Test {@link LocalSecretController#discoverCurrentUserSecrets(DBSSecretObject)}.
   *
   * <p>Method under test: {@link LocalSecretController#discoverCurrentUserSecrets(DBSSecretObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.List LocalSecretController.discoverCurrentUserSecrets(DBSSecretObject)"
  })
  public void testDiscoverCurrentUserSecrets() throws DBException {
    // Arrange, Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            LocalSecretController.INSTANCE.discoverCurrentUserSecrets(mock(DBSSecretObject.class)));
  }

  /**
   * Test new {@link LocalSecretController} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link LocalSecretController}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LocalSecretController.<init>()"})
  public void testNewLocalSecretController() throws DBException {
    // Arrange, Act and Assert
    assertEquals(15L, new LocalSecretController().getSupportedFeatures());
  }
}
