package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.ai.AISchemaGenerationOptions;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityAttribute;
import org.jkiss.dbeaver.model.struct.rdb.DBSTable;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AISchemaGeneratorImplDiffblueTest {
  /**
   * Test {@link AISchemaGeneratorImpl#generateSchema(DBRProgressMonitor, DBSEntity,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#generateSchema(DBRProgressMonitor,
   * DBSEntity, DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.generateSchema(DBRProgressMonitor, DBSEntity, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testGenerateSchema() throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act and Assert
    assertEquals("", aiSchemaGeneratorImpl.generateSchema(monitor, entity, ctx, options, true));
  }

  /**
   * Test {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code CREATE TABLE Dr Jane Doe);}.
   * </ul>
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.describeTable(DBRProgressMonitor, DBSTable, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testDescribeTable_givenArrayList_thenReturnCreateTableDrJaneDoe() throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTable table = mock(DBSTable.class);
    when(table.isView()).thenReturn(false);
    when(table.getDescription()).thenReturn(null);
    when(table.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    Mockito.<List<? extends DBSEntityAttribute>>when(
            table.getAttributes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());

    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    when(ctx.getDataSource()).thenReturn(mock(DBPDataSource.class));
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act
    String actualDescribeTableResult =
        aiSchemaGeneratorImpl.describeTable(monitor, table, ctx, options, true);

    // Assert
    verify(table).getDescription();
    verify(table).getFullyQualifiedName(DBPEvaluationContext.DDL);
    verify(ctx).getDataSource();
    verify(table).getAttributes(isA(DBRProgressMonitor.class));
    verify(table).isView();
    assertEquals("CREATE TABLE Dr Jane Doe);", actualDescribeTableResult);
  }

  /**
   * Test {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <ul>
   *   <li>Given space.
   *   <li>When {@link DBSTable} {@link DBSTable#getDescription()} return space.
   * </ul>
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.describeTable(DBRProgressMonitor, DBSTable, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testDescribeTable_givenSpace_whenDBSTableGetDescriptionReturnSpace()
      throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTable table = mock(DBSTable.class);
    when(table.isView()).thenReturn(false);
    when(table.getDescription()).thenReturn(" ");
    when(table.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    Mockito.<List<? extends DBSEntityAttribute>>when(
            table.getAttributes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());

    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    when(ctx.getDataSource()).thenReturn(mock(DBPDataSource.class));
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act
    String actualDescribeTableResult =
        aiSchemaGeneratorImpl.describeTable(monitor, table, ctx, options, true);

    // Assert
    verify(table).getDescription();
    verify(table).getFullyQualifiedName(DBPEvaluationContext.DDL);
    verify(ctx).getDataSource();
    verify(table).getAttributes(isA(DBRProgressMonitor.class));
    verify(table).isView();
    assertEquals("CREATE TABLE Dr Jane Doe);", actualDescribeTableResult);
  }

  /**
   * Test {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link DBSTable} {@link DBSTable#isView()} return {@code true}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.describeTable(DBRProgressMonitor, DBSTable, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testDescribeTable_givenTrue_whenDBSTableIsViewReturnTrue_thenThrowDBException()
      throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBSTable table = mock(DBSTable.class);
    when(table.isView()).thenReturn(true);
    when(table.getDescription()).thenReturn("The characteristics of someone or something");
    when(table.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    Mockito.<List<? extends DBSEntityAttribute>>when(
            table.getAttributes(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    when(ctx.getDataSource()).thenReturn(mock(DBPDataSource.class));
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> aiSchemaGeneratorImpl.describeTable(monitor, table, ctx, options, true));
    verify(table).getDescription();
    verify(table).getFullyQualifiedName(DBPEvaluationContext.DDL);
    verify(ctx).getDataSource();
    verify(table).getAttributes(isA(DBRProgressMonitor.class));
    verify(table).isView();
  }

  /**
   * Test {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code CREATE TABLE Dr Jane Doe (42 null)}.
   * </ul>
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.describeTable(DBRProgressMonitor, DBSTable, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testDescribeTable_thenReturnCreateTableDrJaneDoe42Null() throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "CREATE TABLE ");
    DBVEntity entity = new DBVEntity(container, "CREATE TABLE ", "CREATE TABLE ");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "CREATE TABLE ");

    ArrayList<DBSEntityAttribute> dbsEntityAttributeList = new ArrayList<>();
    dbsEntityAttributeList.add(dbvEntityAttribute);

    DBSTable table = mock(DBSTable.class);
    when(table.isView()).thenReturn(false);
    when(table.getDescription()).thenReturn(null);
    when(table.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    Mockito.<List<? extends DBSEntityAttribute>>when(
            table.getAttributes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsEntityAttributeList);

    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    when(ctx.getDataSource()).thenReturn(mock(DBPDataSource.class));
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act
    String actualDescribeTableResult =
        aiSchemaGeneratorImpl.describeTable(monitor, table, ctx, options, true);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(table).getDescription();
    verify(table).getFullyQualifiedName(DBPEvaluationContext.DDL);
    verify(ctx).getDataSource();
    verify(sqlDialect).getQuotedIdentifier("CREATE TABLE ", true, false);
    verify(table).getAttributes(isA(DBRProgressMonitor.class));
    verify(table).isView();
    verify(parent).getDataSource();
    assertEquals("CREATE TABLE Dr Jane Doe (42 null) ", actualDescribeTableResult);
  }

  /**
   * Test {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code CREATE TABLE Dr Jane Doe (42 null,42 null)}.
   * </ul>
   *
   * <p>Method under test: {@link AISchemaGeneratorImpl#describeTable(DBRProgressMonitor, DBSTable,
   * DBCExecutionContext, AISchemaGenerationOptions, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String AISchemaGeneratorImpl.describeTable(DBRProgressMonitor, DBSTable, DBCExecutionContext, AISchemaGenerationOptions, boolean)"
  })
  public void testDescribeTable_thenReturnCreateTableDrJaneDoe42Null42Null() throws DBException {
    // Arrange
    AISchemaGeneratorImpl aiSchemaGeneratorImpl = new AISchemaGeneratorImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "CREATE TABLE ");
    DBVEntity entity = new DBVEntity(container, "CREATE TABLE ", "CREATE TABLE ");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "CREATE TABLE ");

    SQLDialect sqlDialect2 = mock(SQLDialect.class);
    when(sqlDialect2.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(sqlDialect2);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "CREATE TABLE ");
    DBVEntity entity2 = new DBVEntity(container2, "CREATE TABLE ", "CREATE TABLE ");
    DBVEntityAttribute dbvEntityAttribute2 =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "CREATE TABLE ");

    ArrayList<DBSEntityAttribute> dbsEntityAttributeList = new ArrayList<>();
    dbsEntityAttributeList.add(dbvEntityAttribute2);
    dbsEntityAttributeList.add(dbvEntityAttribute);

    DBSTable table = mock(DBSTable.class);
    when(table.isView()).thenReturn(false);
    when(table.getDescription()).thenReturn(null);
    when(table.getFullyQualifiedName(Mockito.<DBPEvaluationContext>any()))
        .thenReturn("Dr Jane Doe");
    Mockito.<List<? extends DBSEntityAttribute>>when(
            table.getAttributes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsEntityAttributeList);

    DBCExecutionContext ctx = mock(DBCExecutionContext.class);
    when(ctx.getDataSource()).thenReturn(mock(DBPDataSource.class));
    AISchemaGenerationOptions options = new AISchemaGenerationOptions(3, true, true, true, true);

    // Act
    String actualDescribeTableResult =
        aiSchemaGeneratorImpl.describeTable(monitor, table, ctx, options, true);

    // Assert
    verify(dbpDataSource2).getSQLDialect();
    verify(dbpDataSource).getSQLDialect();
    verify(table).getDescription();
    verify(table).getFullyQualifiedName(DBPEvaluationContext.DDL);
    verify(ctx).getDataSource();
    verify(sqlDialect2).getQuotedIdentifier("CREATE TABLE ", true, false);
    verify(sqlDialect).getQuotedIdentifier("CREATE TABLE ", true, false);
    verify(table).getAttributes(isA(DBRProgressMonitor.class));
    verify(table).isView();
    verify(parent2).getDataSource();
    verify(parent).getDataSource();
    assertEquals("CREATE TABLE Dr Jane Doe (42 null,42 null) ", actualDescribeTableResult);
  }
}
