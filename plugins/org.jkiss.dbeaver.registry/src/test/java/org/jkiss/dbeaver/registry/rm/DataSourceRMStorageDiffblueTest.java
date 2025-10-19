package org.jkiss.dbeaver.registry.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.registry.project.LocalProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceRMStorageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataSourceRMStorage#DataSourceRMStorage(DBPProject)}
   *   <li>{@link DataSourceRMStorage#getStatus()}
   *   <li>{@link DataSourceRMStorage#getStorageSubId()}
   *   <li>{@link DataSourceRMStorage#isDefault()}
   *   <li>{@link DataSourceRMStorage#isValid()}
   *   <li>{@link DataSourceRMStorage#isVirtual()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceRMStorage.<init>(DBPProject)",
    "String DataSourceRMStorage.getStatus()",
    "String DataSourceRMStorage.getStorageSubId()",
    "boolean DataSourceRMStorage.isDefault()",
    "boolean DataSourceRMStorage.isValid()",
    "boolean DataSourceRMStorage.isVirtual()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace, new SessionContextImpl(null), projectPath);

    // Act
    DataSourceRMStorage actualDataSourceRMStorage = new DataSourceRMStorage(project);
    String actualStatus = actualDataSourceRMStorage.getStatus();
    String actualStorageSubId = actualDataSourceRMStorage.getStorageSubId();
    boolean actualIsDefaultResult = actualDataSourceRMStorage.isDefault();
    boolean actualIsValidResult = actualDataSourceRMStorage.isValid();

    // Assert
    assertNull(actualStatus);
    assertNull(actualStorageSubId);
    assertTrue(actualIsDefaultResult);
    assertTrue(actualIsValidResult);
    assertTrue(actualDataSourceRMStorage.isVirtual());
  }

  /**
   * Test {@link DataSourceRMStorage#getStorageId()}.
   *
   * <ul>
   *   <li>Then return {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link DataSourceRMStorage#getStorageId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataSourceRMStorage.getStorageId()"})
  public void testGetStorageId_thenReturnTestTxt() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace, sessionContext, projectPath);
    project.setInMemory(true);

    // Act and Assert
    assertEquals("test.txt", new DataSourceRMStorage(project).getStorageId());
  }
}
