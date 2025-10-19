package org.jkiss.dbeaver.registry.storage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import org.jkiss.dbeaver.model.connection.InternalDatabaseConfig;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProxyProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.registry.task.TaskLoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class H2MigratorDiffblueTest {
  /**
   * Test {@link H2Migrator#migrateDatabaseIfNeeded(String, String)}.
   *
   * <p>Method under test: {@link H2Migrator#migrateDatabaseIfNeeded(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void H2Migrator.migrateDatabaseIfNeeded(String, String)"})
  public void testMigrateDatabaseIfNeeded() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      InternalDatabaseConfig databaseConfiguration = mock(InternalDatabaseConfig.class);
      when(databaseConfiguration.getUrl()).thenReturn("https://example.org/example");
      doNothing().when(databaseConfiguration).setDriver(Mockito.<String>any());
      doNothing().when(databaseConfiguration).setUrl(Mockito.<String>any());
      when(databaseConfiguration.getDriver()).thenReturn(H2Migrator.V1_DRIVER_NAME);
      when(databaseConfiguration.getResolvedUrl()).thenReturn("https://example.org/example");
      TaskLoggingProgressMonitor original =
          new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
      ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);

      H2Migrator h2Migrator =
          new H2Migrator(monitor, null, databaseConfiguration, new Properties());

      // Act
      h2Migrator.migrateDatabaseIfNeeded("", "Db Name V2");

      // Assert
      mockFiles.verify(
          () -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
      verify(databaseConfiguration, atLeast(1)).getDriver();
      verify(databaseConfiguration).getResolvedUrl();
      verify(databaseConfiguration).getUrl();
      verify(databaseConfiguration).setDriver("h2_embedded");
      verify(databaseConfiguration).setUrl("https://example.org/example");
    }
  }

  /**
   * Test {@link H2Migrator#isH2Database(InternalDatabaseConfig)}.
   *
   * <ul>
   *   <li>Given {@code https://example.org/example}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link H2Migrator#isH2Database(InternalDatabaseConfig)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean H2Migrator.isH2Database(InternalDatabaseConfig)"})
  public void testIsH2Database_givenHttpsExampleOrgExample_thenReturnFalse() {
    // Arrange
    InternalDatabaseConfig databaseConfiguration = mock(InternalDatabaseConfig.class);
    when(databaseConfiguration.getUrl()).thenReturn("https://example.org/example");

    // Act
    boolean actualIsH2DatabaseResult = H2Migrator.isH2Database(databaseConfiguration);

    // Assert
    verify(databaseConfiguration).getUrl();
    assertFalse(actualIsH2DatabaseResult);
  }

  /**
   * Test {@link H2Migrator#isH2Database(InternalDatabaseConfig)}.
   *
   * <ul>
   *   <li>Given {@code jdbc:h2}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link H2Migrator#isH2Database(InternalDatabaseConfig)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean H2Migrator.isH2Database(InternalDatabaseConfig)"})
  public void testIsH2Database_givenJdbcH2_thenReturnTrue() {
    // Arrange
    InternalDatabaseConfig databaseConfiguration = mock(InternalDatabaseConfig.class);
    when(databaseConfiguration.getUrl()).thenReturn("jdbc:h2");

    // Act
    boolean actualIsH2DatabaseResult = H2Migrator.isH2Database(databaseConfiguration);

    // Assert
    verify(databaseConfiguration).getUrl();
    assertTrue(actualIsH2DatabaseResult);
  }
}
