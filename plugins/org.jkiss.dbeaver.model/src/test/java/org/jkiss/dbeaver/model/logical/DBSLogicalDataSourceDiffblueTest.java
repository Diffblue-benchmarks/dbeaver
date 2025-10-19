package org.jkiss.dbeaver.model.logical;

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
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSLogicalDataSourceDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSLogicalDataSource#DBSLogicalDataSource(DBPDataSourceContainer)}
   *   <li>{@link DBSLogicalDataSource#setCatalogs(List)}
   *   <li>{@link DBSLogicalDataSource#setCurrentCatalog(String)}
   *   <li>{@link DBSLogicalDataSource#setCurrentSchema(String)}
   *   <li>{@link DBSLogicalDataSource#setDescription(String)}
   *   <li>{@link DBSLogicalDataSource#setName(String)}
   *   <li>{@link DBSLogicalDataSource#getCatalogs()}
   *   <li>{@link DBSLogicalDataSource#getCurrentCatalog()}
   *   <li>{@link DBSLogicalDataSource#getCurrentSchema()}
   *   <li>{@link DBSLogicalDataSource#getDataSourceContainer()}
   *   <li>{@link DBSLogicalDataSource#getDescription()}
   *   <li>{@link DBSLogicalDataSource#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSLogicalDataSource.<init>(DBPDataSourceContainer)",
    "void DBSLogicalDataSource.<init>(DBPDataSourceContainer, String, String)",
    "List DBSLogicalDataSource.getCatalogs()",
    "String DBSLogicalDataSource.getCurrentCatalog()",
    "String DBSLogicalDataSource.getCurrentSchema()",
    "DBPDataSourceContainer DBSLogicalDataSource.getDataSourceContainer()",
    "String DBSLogicalDataSource.getDescription()",
    "String DBSLogicalDataSource.getName()",
    "void DBSLogicalDataSource.setCatalogs(List)",
    "void DBSLogicalDataSource.setCurrentCatalog(String)",
    "void DBSLogicalDataSource.setCurrentSchema(String)",
    "void DBSLogicalDataSource.setDescription(String)",
    "void DBSLogicalDataSource.setName(String)"
  })
  public void testGettersAndSetters_whenDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    // Act
    DBSLogicalDataSource actualDbsLogicalDataSource = new DBSLogicalDataSource(dataSourceContainer);
    ArrayList<DBSLogicalCatalog> catalogs = new ArrayList<>();
    actualDbsLogicalDataSource.setCatalogs(catalogs);
    actualDbsLogicalDataSource.setCurrentCatalog("Current Catalog");
    actualDbsLogicalDataSource.setCurrentSchema("Current Schema");
    actualDbsLogicalDataSource.setDescription("The characteristics of someone or something");
    actualDbsLogicalDataSource.setName("Name");
    List<DBSLogicalCatalog> actualCatalogs = actualDbsLogicalDataSource.getCatalogs();
    String actualCurrentCatalog = actualDbsLogicalDataSource.getCurrentCatalog();
    String actualCurrentSchema = actualDbsLogicalDataSource.getCurrentSchema();
    DBPDataSourceContainer actualDataSourceContainer =
        actualDbsLogicalDataSource.getDataSourceContainer();
    String actualDescription = actualDbsLogicalDataSource.getDescription();

    // Assert
    assertEquals("Current Catalog", actualCurrentCatalog);
    assertEquals("Current Schema", actualCurrentSchema);
    assertEquals("Name", actualDbsLogicalDataSource.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualCatalogs.isEmpty());
    assertSame(catalogs, actualCatalogs);
    assertSame(dataSourceContainer, actualDataSourceContainer);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSLogicalDataSource#DBSLogicalDataSource(DBPDataSourceContainer, String, String)}
   *   <li>{@link DBSLogicalDataSource#setCatalogs(List)}
   *   <li>{@link DBSLogicalDataSource#setCurrentCatalog(String)}
   *   <li>{@link DBSLogicalDataSource#setCurrentSchema(String)}
   *   <li>{@link DBSLogicalDataSource#setDescription(String)}
   *   <li>{@link DBSLogicalDataSource#setName(String)}
   *   <li>{@link DBSLogicalDataSource#getCatalogs()}
   *   <li>{@link DBSLogicalDataSource#getCurrentCatalog()}
   *   <li>{@link DBSLogicalDataSource#getCurrentSchema()}
   *   <li>{@link DBSLogicalDataSource#getDataSourceContainer()}
   *   <li>{@link DBSLogicalDataSource#getDescription()}
   *   <li>{@link DBSLogicalDataSource#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSLogicalDataSource.<init>(DBPDataSourceContainer)",
    "void DBSLogicalDataSource.<init>(DBPDataSourceContainer, String, String)",
    "List DBSLogicalDataSource.getCatalogs()",
    "String DBSLogicalDataSource.getCurrentCatalog()",
    "String DBSLogicalDataSource.getCurrentSchema()",
    "DBPDataSourceContainer DBSLogicalDataSource.getDataSourceContainer()",
    "String DBSLogicalDataSource.getDescription()",
    "String DBSLogicalDataSource.getName()",
    "void DBSLogicalDataSource.setCatalogs(List)",
    "void DBSLogicalDataSource.setCurrentCatalog(String)",
    "void DBSLogicalDataSource.setCurrentSchema(String)",
    "void DBSLogicalDataSource.setDescription(String)",
    "void DBSLogicalDataSource.setName(String)"
  })
  public void testGettersAndSetters_whenName() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    // Act
    DBSLogicalDataSource actualDbsLogicalDataSource =
        new DBSLogicalDataSource(
            dataSourceContainer, "Name", "The characteristics of someone or something");
    ArrayList<DBSLogicalCatalog> catalogs = new ArrayList<>();
    actualDbsLogicalDataSource.setCatalogs(catalogs);
    actualDbsLogicalDataSource.setCurrentCatalog("Current Catalog");
    actualDbsLogicalDataSource.setCurrentSchema("Current Schema");
    actualDbsLogicalDataSource.setDescription("The characteristics of someone or something");
    actualDbsLogicalDataSource.setName("Name");
    List<DBSLogicalCatalog> actualCatalogs = actualDbsLogicalDataSource.getCatalogs();
    String actualCurrentCatalog = actualDbsLogicalDataSource.getCurrentCatalog();
    String actualCurrentSchema = actualDbsLogicalDataSource.getCurrentSchema();
    DBPDataSourceContainer actualDataSourceContainer =
        actualDbsLogicalDataSource.getDataSourceContainer();
    String actualDescription = actualDbsLogicalDataSource.getDescription();

    // Assert
    assertEquals("Current Catalog", actualCurrentCatalog);
    assertEquals("Current Schema", actualCurrentSchema);
    assertEquals("Name", actualDbsLogicalDataSource.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertTrue(actualCatalogs.isEmpty());
    assertSame(catalogs, actualCatalogs);
    assertSame(dataSourceContainer, actualDataSourceContainer);
  }

  /**
   * Test {@link DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer,
   * DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSLogicalDataSource DBSLogicalDataSource.createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)"
  })
  public void testCreateLogicalDataSource_givenNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(null);

    // Act
    DBSLogicalDataSource actualCreateLogicalDataSourceResult =
        DBSLogicalDataSource.createLogicalDataSource(dataSourceContainer, executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    assertEquals("AI logical wrapper", actualCreateLogicalDataSourceResult.getName());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentCatalog());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentSchema());
    assertNull(actualCreateLogicalDataSourceResult.getDescription());
    assertNull(actualCreateLogicalDataSourceResult.getCatalogs());
    assertSame(dataSourceContainer, actualCreateLogicalDataSourceResult.getDataSourceContainer());
  }

  /**
   * Test {@link DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer,
   * DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link VoidExecutionContextDefaults} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link
   * DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSLogicalDataSource DBSLogicalDataSource.createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)"
  })
  public void testCreateLogicalDataSource_givenVoidExecutionContextDefaults() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());

    // Act
    DBSLogicalDataSource actualCreateLogicalDataSourceResult =
        DBSLogicalDataSource.createLogicalDataSource(dataSourceContainer, executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    assertEquals("AI logical wrapper", actualCreateLogicalDataSourceResult.getName());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentCatalog());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentSchema());
    assertNull(actualCreateLogicalDataSourceResult.getDescription());
    assertNull(actualCreateLogicalDataSourceResult.getCatalogs());
    assertSame(dataSourceContainer, actualCreateLogicalDataSourceResult.getDataSourceContainer());
  }

  /**
   * Test {@link DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer,
   * DBCExecutionContext)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBSLogicalDataSource#createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSLogicalDataSource DBSLogicalDataSource.createLogicalDataSource(DBPDataSourceContainer, DBCExecutionContext)"
  })
  public void testCreateLogicalDataSource_whenNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);

    // Act
    DBSLogicalDataSource actualCreateLogicalDataSourceResult =
        DBSLogicalDataSource.createLogicalDataSource(dataSourceContainer, null);

    // Assert
    assertEquals("AI logical wrapper", actualCreateLogicalDataSourceResult.getName());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentCatalog());
    assertNull(actualCreateLogicalDataSourceResult.getCurrentSchema());
    assertNull(actualCreateLogicalDataSourceResult.getDescription());
    assertNull(actualCreateLogicalDataSourceResult.getCatalogs());
    assertSame(dataSourceContainer, actualCreateLogicalDataSourceResult.getDataSourceContainer());
  }
}
