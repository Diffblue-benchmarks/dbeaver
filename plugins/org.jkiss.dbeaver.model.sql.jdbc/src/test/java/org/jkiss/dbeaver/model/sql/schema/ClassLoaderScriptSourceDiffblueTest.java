package org.jkiss.dbeaver.model.sql.schema;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import javax.management.loading.MLet;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ClassLoaderScriptSourceDiffblueTest {
  /**
   * Test {@link ClassLoaderScriptSource#openSchemaCreateScript(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassLoaderScriptSource#openSchemaCreateScript(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.io.Reader ClassLoaderScriptSource.openSchemaCreateScript(DBRProgressMonitor, String)"
  })
  public void testOpenSchemaCreateScript_thenThrowIOException() throws IOException, DBException {
    // Arrange
    ClassLoaderScriptSource classLoaderScriptSource =
        new ClassLoaderScriptSource(new MLet(), "Create Script Path", "2020-03-01");

    // Act and Assert
    assertThrows(
        IOException.class,
        () ->
            classLoaderScriptSource.openSchemaCreateScript(
                new LoggingProgressMonitor(), "Specific Prefix"));
  }

  /**
   * Test {@link ClassLoaderScriptSource#openSchemaCreateScript(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link ClassLoaderScriptSource#openSchemaCreateScript(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.io.Reader ClassLoaderScriptSource.openSchemaCreateScript(DBRProgressMonitor, String)"
  })
  public void testOpenSchemaCreateScript_whenNull_thenThrowIOException()
      throws IOException, DBException {
    // Arrange
    ClassLoaderScriptSource classLoaderScriptSource =
        new ClassLoaderScriptSource(new MLet(), "Create Script Path", "2020-03-01");

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> classLoaderScriptSource.openSchemaCreateScript(new LoggingProgressMonitor(), null));
  }

  /**
   * Test {@link ClassLoaderScriptSource#openSchemaUpdateScript(DBRProgressMonitor, int, String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassLoaderScriptSource#openSchemaUpdateScript(DBRProgressMonitor,
   * int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.io.Reader ClassLoaderScriptSource.openSchemaUpdateScript(DBRProgressMonitor, int, String)"
  })
  public void testOpenSchemaUpdateScript_thenReturnNull() throws IOException, DBException {
    // Arrange
    ClassLoaderScriptSource classLoaderScriptSource =
        new ClassLoaderScriptSource(new MLet(), "Create Script Path", "2020-03-01");

    // Act and Assert
    assertNull(
        classLoaderScriptSource.openSchemaUpdateScript(
            new LoggingProgressMonitor(), 10, "Specific Prefix"));
  }

  /**
   * Test {@link ClassLoaderScriptSource#openSchemaUpdateScript(DBRProgressMonitor, int, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassLoaderScriptSource#openSchemaUpdateScript(DBRProgressMonitor,
   * int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.io.Reader ClassLoaderScriptSource.openSchemaUpdateScript(DBRProgressMonitor, int, String)"
  })
  public void testOpenSchemaUpdateScript_whenNull_thenReturnNull() throws IOException, DBException {
    // Arrange
    ClassLoaderScriptSource classLoaderScriptSource =
        new ClassLoaderScriptSource(new MLet(), "Create Script Path", "2020-03-01");

    // Act and Assert
    assertNull(
        classLoaderScriptSource.openSchemaUpdateScript(new LoggingProgressMonitor(), 10, null));
  }

  /**
   * Test {@link ClassLoaderScriptSource#findScript(String[])}.
   *
   * <ul>
   *   <li>When {@code Paths}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ClassLoaderScriptSource#findScript(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.Reader ClassLoaderScriptSource.findScript(String[])"})
  public void testFindScript_whenPaths_thenReturnNull() {
    // Arrange
    ClassLoaderScriptSource classLoaderScriptSource =
        new ClassLoaderScriptSource(new MLet(), "Create Script Path", "2020-03-01");

    // Act and Assert
    assertNull(classLoaderScriptSource.findScript("Paths"));
  }
}
