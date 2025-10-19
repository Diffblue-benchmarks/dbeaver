package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.postgresql.PostgreConstants;
import org.jkiss.dbeaver.model.impl.data.AttributeMetaDataProxy;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.rdb.DBSTable;
import org.jkiss.dbeaver.model.struct.rdb.DBSTableConstraint;
import org.jkiss.dbeaver.model.struct.rdb.DBSTableIndex;
import org.jkiss.dbeaver.model.struct.rdb.DBSTableIndexColumn;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PostgreInsertReplaceMethodDiffblueTest {
  /**
   * Test {@link PostgreInsertReplaceMethod#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause() {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertEquals(
        "INSERT INTO",
        postgreInsertReplaceMethod.getOpeningClause(table, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause() throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    DBSTableConstraint dbsTableConstraint2 = mock(DBSTableConstraint.class);
    when(dbsTableConstraint2.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint2);
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTableIndex dbsTableIndex = mock(DBSTableIndex.class);
    Mockito.<List<? extends DBSTableIndexColumn>>when(
            dbsTableIndex.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    when(dbsTableIndex.isUnique()).thenReturn(true);

    ArrayList<DBSTableIndex> dbsTableIndexList = new ArrayList<>();
    dbsTableIndexList.add(dbsTableIndex);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableIndexList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(dbsTableConstraint2).getConstraintType();
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    verify(dbsTableIndex).getAttributeReferences(isA(DBRProgressMonitor.class));
    verify(dbsTableIndex).isUnique();
    assertEquals("", actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DBSTableConstraint#getConstraintType()}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenArrayList_thenCallsGetConstraintType() throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    assertNull(actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then calls {@link DBSTableConstraint#getConstraintType()}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenArrayList_thenCallsGetConstraintType2()
      throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    DBSTableConstraint dbsTableConstraint2 = mock(DBSTableConstraint.class);
    when(dbsTableConstraint2.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint2);
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(dbsTableConstraint2).getConstraintType();
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    assertNull(actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenArrayList_thenReturnNull() throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    assertNull(actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link DBException#DBException(String)} with message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenDBExceptionWithMessageIsAnErrorOccurred()
      throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("", actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link DBSTableIndex} {@link
   *       DBSTableIndex#getAttributeReferences(DBRProgressMonitor)} return {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenDBSTableIndexGetAttributeReferencesReturnArrayList()
      throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    DBSTableConstraint dbsTableConstraint2 = mock(DBSTableConstraint.class);
    when(dbsTableConstraint2.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint2);
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTableIndex dbsTableIndex = mock(DBSTableIndex.class);
    Mockito.<List<? extends DBSTableIndexColumn>>when(
            dbsTableIndex.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());
    when(dbsTableIndex.isUnique()).thenReturn(true);

    ArrayList<DBSTableIndex> dbsTableIndexList = new ArrayList<>();
    dbsTableIndexList.add(dbsTableIndex);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableIndexList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(dbsTableConstraint2).getConstraintType();
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    verify(dbsTableIndex).getAttributeReferences(isA(DBRProgressMonitor.class));
    verify(dbsTableIndex).isUnique();
    assertNull(actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@link DBSTableIndex} {@link DBSTableIndex#isUnique()} return {@code false}.
   *   <li>Then calls {@link DBSTableIndex#isUnique()}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenDBSTableIndexIsUniqueReturnFalse_thenCallsIsUnique()
      throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    DBSTableConstraint dbsTableConstraint2 = mock(DBSTableConstraint.class);
    when(dbsTableConstraint2.getConstraintType()).thenReturn(PostgreConstants.CONSTRAINT_EXCLUSIVE);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint2);
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTableIndex dbsTableIndex = mock(DBSTableIndex.class);
    when(dbsTableIndex.isUnique()).thenReturn(false);

    ArrayList<DBSTableIndex> dbsTableIndexList = new ArrayList<>();
    dbsTableIndexList.add(dbsTableIndex);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableIndexList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(dbsTableConstraint2).getConstraintType();
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    verify(dbsTableIndex).isUnique();
    assertNull(actualTrailingClause);
  }

  /**
   * Test {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBSTable} {@link DBSTable#getConstraints(DBRProgressMonitor)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link PostgreInsertReplaceMethod#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String PostgreInsertReplaceMethod.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_givenNull_whenDBSTableGetConstraintsReturnNull()
      throws DBException {
    // Arrange
    PostgreInsertReplaceMethod postgreInsertReplaceMethod = new PostgreInsertReplaceMethod();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(null);
    Mockito.<Collection<? extends DBSTableIndex>>when(
            table.getIndexes(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    String actualTrailingClause =
        postgreInsertReplaceMethod.getTrailingClause(
            table, monitor, new DBSAttributeBase[] {new AttributeMetaDataProxy(null)});

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    verify(table).getIndexes(isA(DBRProgressMonitor.class));
    assertNull(actualTrailingClause);
  }
}
