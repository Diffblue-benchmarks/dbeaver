package org.jkiss.dbeaver.model.ai.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.ai.AIDatabaseScope;
import org.jkiss.dbeaver.model.ai.engine.AIDatabaseContext.Builder;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.logical.DBSLogicalDataSource;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.rdb.DBSCatalog;
import org.jkiss.dbeaver.model.struct.rdb.DBSSchema;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIDatabaseContextDiffblueTest {
  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Builder#build()}
   *   <li>{@link Builder#Builder(DBSLogicalDataSource)}
   *   <li>{@link Builder#setCustomEntities(List)}
   *   <li>{@link Builder#setExecutionContext(DBCExecutionContext)}
   *   <li>{@link Builder#setScope(AIDatabaseScope)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Builder.<init>(DBSLogicalDataSource)",
    "AIDatabaseContext Builder.build()",
    "Builder Builder.setCustomEntities(List)",
    "Builder Builder.setExecutionContext(DBCExecutionContext)",
    "Builder Builder.setScope(AIDatabaseScope)"
  })
  public void testBuilderBuild() throws DBException {
    // Arrange
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    // Act
    Builder actualBuilder = new Builder(dataSource);
    ArrayList<DBSObject> customEntities = new ArrayList<>();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    AIDatabaseContext actualAiDatabaseContext =
        actualBuilder
            .setCustomEntities(customEntities)
            .setExecutionContext(executionContext)
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    // Assert
    assertNull(actualAiDatabaseContext.getScopeObject());
    assertEquals(AIDatabaseScope.CURRENT_SCHEMA, actualAiDatabaseContext.getScope());
    List<DBSObject> customEntities2 = actualAiDatabaseContext.getCustomEntities();
    assertTrue(customEntities2.isEmpty());
    assertSame(customEntities, customEntities2);
    assertSame(dataSource, actualAiDatabaseContext.getDataSource());
    assertSame(executionContext, actualAiDatabaseContext.getExecutionContext());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AIDatabaseContext#getCustomEntities()}
   *   <li>{@link AIDatabaseContext#getDataSource()}
   *   <li>{@link AIDatabaseContext#getExecutionContext()}
   *   <li>{@link AIDatabaseContext#getScope()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List AIDatabaseContext.getCustomEntities()",
    "DBSLogicalDataSource AIDatabaseContext.getDataSource()",
    "DBCExecutionContext AIDatabaseContext.getExecutionContext()",
    "AIDatabaseScope AIDatabaseContext.getScope()"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);
    ArrayList<DBSObject> customEntities = new ArrayList<>();
    AIDatabaseContext aiDatabaseContext =
        builder
            .setCustomEntities(customEntities)
            .setExecutionContext(mock(DBCExecutionContext.class))
            .setScope(AIDatabaseScope.CURRENT_SCHEMA)
            .build();

    // Act
    List<DBSObject> actualCustomEntities = aiDatabaseContext.getCustomEntities();
    DBSLogicalDataSource actualDataSource = aiDatabaseContext.getDataSource();
    aiDatabaseContext.getExecutionContext();

    // Assert
    assertEquals(AIDatabaseScope.CURRENT_SCHEMA, aiDatabaseContext.getScope());
    assertTrue(actualCustomEntities.isEmpty());
    assertSame(customEntities, actualCustomEntities);
    assertSame(dataSource, actualDataSource);
  }

  /**
   * Test {@link AIDatabaseContext#getScopeObject()}.
   *
   * <p>Method under test: {@link AIDatabaseContext#getScopeObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer AIDatabaseContext.getScopeObject()"})
  public void testGetScopeObject() throws DBException {
    // Arrange
    DBSCatalog dbsCatalog = mock(DBSCatalog.class);
    when(dbsCatalog.getName()).thenReturn("Name");

    DBSSchema dbsSchema = mock(DBSSchema.class);
    when(dbsSchema.getName()).thenReturn("Name");

    VoidExecutionContextDefaults voidExecutionContextDefaults =
        mock(VoidExecutionContextDefaults.class);
    when(voidExecutionContextDefaults.getDefaultCatalog()).thenReturn(dbsCatalog);
    when(voidExecutionContextDefaults.getDefaultSchema()).thenReturn(dbsSchema);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(voidExecutionContextDefaults);
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);

    // Act
    builder
        .setCustomEntities(new ArrayList<>())
        .setExecutionContext(executionContext)
        .setScope(AIDatabaseScope.CURRENT_SCHEMA)
        .build()
        .getScopeObject();

    // Assert
    verify(dbsCatalog).getName();
    verify(dbsSchema).getName();
    verify(executionContext, atLeast(1)).getContextDefaults();
    verify(voidExecutionContextDefaults).getDefaultCatalog();
    verify(voidExecutionContextDefaults, atLeast(1)).getDefaultSchema();
  }

  /**
   * Test {@link AIDatabaseContext#getScopeObject()}.
   *
   * <p>Method under test: {@link AIDatabaseContext#getScopeObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer AIDatabaseContext.getScopeObject()"})
  public void testGetScopeObject2() throws DBException {
    // Arrange
    DBSCatalog dbsCatalog = mock(DBSCatalog.class);
    when(dbsCatalog.getName()).thenReturn("Name");

    DBSSchema dbsSchema = mock(DBSSchema.class);
    when(dbsSchema.getName()).thenReturn("Name");

    VoidExecutionContextDefaults voidExecutionContextDefaults =
        mock(VoidExecutionContextDefaults.class);
    when(voidExecutionContextDefaults.getDefaultCatalog()).thenReturn(dbsCatalog);
    when(voidExecutionContextDefaults.getDefaultSchema()).thenReturn(dbsSchema);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getContextDefaults()).thenReturn(voidExecutionContextDefaults);
    DBSLogicalDataSource dataSource = new DBSLogicalDataSource(mock(DBPDataSourceContainer.class));

    Builder builder = new Builder(dataSource);

    // Act
    builder
        .setCustomEntities(new ArrayList<>())
        .setExecutionContext(executionContext)
        .setScope(AIDatabaseScope.CURRENT_DATABASE)
        .build()
        .getScopeObject();

    // Assert
    verify(dbsCatalog).getName();
    verify(dbsSchema).getName();
    verify(executionContext, atLeast(1)).getContextDefaults();
    verify(voidExecutionContextDefaults, atLeast(1)).getDefaultCatalog();
    verify(voidExecutionContextDefaults).getDefaultSchema();
  }
}
