package org.jkiss.dbeaver.registry.project;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalProjectImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalProjectImpl#ensureOpen()}
   *   <li>{@link LocalProjectImpl#getAbsolutePath()}
   *   <li>{@link LocalProjectImpl#isOpen()}
   *   <li>{@link LocalProjectImpl#isUseSecretStorage()}
   *   <li>{@link LocalProjectImpl#isVirtual()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalProjectImpl.ensureOpen()",
    "Path LocalProjectImpl.getAbsolutePath()",
    "boolean LocalProjectImpl.isOpen()",
    "boolean LocalProjectImpl.isUseSecretStorage()",
    "boolean LocalProjectImpl.isVirtual()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl localProjectImpl =
        new LocalProjectImpl(workspace, new SessionContextImpl(null), projectPath);

    // Act
    localProjectImpl.ensureOpen();
    Path actualAbsolutePath = localProjectImpl.getAbsolutePath();
    boolean actualIsOpenResult = localProjectImpl.isOpen();
    boolean actualIsUseSecretStorageResult = localProjectImpl.isUseSecretStorage();

    // Assert
    assertFalse(actualIsUseSecretStorageResult);
    assertFalse(localProjectImpl.isVirtual());
    assertTrue(actualIsOpenResult);
    assertSame(projectPath, actualAbsolutePath);
  }
}
