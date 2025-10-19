package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Properties;
import javax.management.loading.MLet;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.connection.DBPDriverLoader;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCConnectionOpenerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCConnectionOpener#JDBCConnectionOpener(JDBCDataSource, DBPDriver, Driver,
   *       String, Properties, Object)}
   *   <li>{@link JDBCConnectionOpener#getConnection()}
   *   <li>{@link JDBCConnectionOpener#getError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCConnectionOpener.<init>(JDBCDataSource, DBPDriver, Driver, String, Properties, Object)",
    "Connection JDBCConnectionOpener.getConnection()",
    "Throwable JDBCConnectionOpener.getError()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPDriver driver = mock(DBPDriver.class);

    // Act
    JDBCConnectionOpener actualJdbcConnectionOpener =
        new JDBCConnectionOpener(
            null, driver, null, "https://example.org/example", new Properties(), "Auth Result");
    Connection actualConnection = actualJdbcConnectionOpener.getConnection();

    // Assert
    assertNull(actualJdbcConnectionOpener.getError());
    assertNull(actualConnection);
  }

  /**
   * Test {@link JDBCConnectionOpener#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link JDBCConnectionOpener#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCConnectionOpener.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    JDBCDataSource dataSource = mock(JDBCDataSource.class);
    when(dataSource.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    DBPDriver driver = mock(DBPDriver.class);

    DBPDriverLoader dbpDriverLoader = mock(DBPDriverLoader.class);
    when(dbpDriverLoader.getClassLoader()).thenReturn(new MLet());
    when(driver.getDriverLoader(Mockito.<DBPDataSourceContainer>any())).thenReturn(dbpDriverLoader);

    JDBCConnectionOpener jdbcConnectionOpener =
        new JDBCConnectionOpener(
            dataSource,
            driver,
            null,
            "https://example.org/example",
            new Properties(),
            "Auth Result");

    // Act
    jdbcConnectionOpener.run(new LoggingProgressMonitor());

    // Assert
    verify(dataSource).getContainer();
    verify(driver).getDriverLoader(isA(DBPDataSourceContainer.class));
    verify(dbpDriverLoader).getClassLoader();
    Throwable error = jdbcConnectionOpener.getError();
    assertTrue(error instanceof SQLException);
    assertNull(error.getCause());
    assertEquals(0, ((SQLException) error).getErrorCode());
    assertEquals(
        "No suitable driver found for https://example.org/example", error.getLocalizedMessage());
    assertEquals("No suitable driver found for https://example.org/example", error.getMessage());
    assertNull(((SQLException) error).getNextException());
    assertEquals("08001", ((SQLException) error).getSQLState());
    assertEquals(0, error.getSuppressed().length);
    Iterator<Throwable> iteratorResult = ((SQLException) error).iterator();
    Throwable actualNextResult = iteratorResult.next();
    assertFalse(iteratorResult.hasNext());
    assertSame(error, actualNextResult);
  }
}
