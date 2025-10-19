package org.jkiss.dbeaver.ext.oracle.model;

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
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.rdb.DBSTable;
import org.jkiss.dbeaver.model.struct.rdb.DBSTableConstraint;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class OracleInsertReplaceMethodIgnoreDiffblueTest {
  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>When {@link DBSTable} {@link DBSTable#getConstraints(DBRProgressMonitor)} return {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_givenArrayList_whenDBSTableGetConstraintsReturnArrayList()
      throws DBException {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(new ArrayList<>());

    // Act
    String actualOpeningClause =
        oracleInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor());

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("INSERT INTO", actualOpeningClause);
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBException#DBException(String)} with message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_givenDBExceptionWithMessageIsAnErrorOccurred()
      throws DBException {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    // Act
    String actualOpeningClause =
        oracleInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor());

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("INSERT INTO", actualOpeningClause);
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBSTable} {@link DBSTable#getConstraints(DBRProgressMonitor)} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_givenNull_whenDBSTableGetConstraintsReturnNull()
      throws DBException {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(null);

    // Act
    String actualOpeningClause =
        oracleInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor());

    // Assert
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("INSERT INTO", actualOpeningClause);
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSTableConstraint#getConstraintType()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_thenCallsGetConstraintType() throws DBException {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType())
        .thenReturn(OracleConstants.CONSTRAINT_HASH_EXPRESSION);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);

    // Act
    String actualOpeningClause =
        oracleInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor());

    // Assert
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("INSERT INTO", actualOpeningClause);
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSTableConstraint#getConstraintType()}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_thenCallsGetConstraintType2() throws DBException {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    DBSTableConstraint dbsTableConstraint = mock(DBSTableConstraint.class);
    when(dbsTableConstraint.getConstraintType())
        .thenReturn(OracleConstants.CONSTRAINT_HASH_EXPRESSION);

    DBSTableConstraint dbsTableConstraint2 = mock(DBSTableConstraint.class);
    when(dbsTableConstraint2.getConstraintType())
        .thenReturn(OracleConstants.CONSTRAINT_HASH_EXPRESSION);

    ArrayList<DBSTableConstraint> dbsTableConstraintList = new ArrayList<>();
    dbsTableConstraintList.add(dbsTableConstraint2);
    dbsTableConstraintList.add(dbsTableConstraint);

    DBSTable table = mock(DBSTable.class);
    Mockito.<Collection<? extends DBSTableConstraint>>when(
            table.getConstraints(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbsTableConstraintList);

    // Act
    String actualOpeningClause =
        oracleInsertReplaceMethodIgnore.getOpeningClause(table, new LoggingProgressMonitor());

    // Assert
    verify(dbsTableConstraint2).getConstraintType();
    verify(dbsTableConstraint).getConstraintType();
    verify(table).getConstraints(isA(DBRProgressMonitor.class));
    assertEquals("INSERT INTO", actualOpeningClause);
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getOpeningClause(DBSTable,
   * DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getOpeningClause(DBSTable, DBRProgressMonitor)"
  })
  public void testGetOpeningClause_whenNull() {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();

    // Act and Assert
    assertEquals(
        "INSERT INTO",
        oracleInsertReplaceMethodIgnore.getOpeningClause(null, new LoggingProgressMonitor()));
  }

  /**
   * Test {@link OracleInsertReplaceMethodIgnore#getTrailingClause(DBSTable, DBRProgressMonitor,
   * DBSAttributeBase[])}.
   *
   * <ul>
   *   <li>When array of {@link DBSAttributeBase} with {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OracleInsertReplaceMethodIgnore#getTrailingClause(DBSTable,
   * DBRProgressMonitor, DBSAttributeBase[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String OracleInsertReplaceMethodIgnore.getTrailingClause(DBSTable, DBRProgressMonitor, DBSAttributeBase[])"
  })
  public void testGetTrailingClause_whenArrayOfDBSAttributeBaseWithNull_thenReturnNull() {
    // Arrange
    OracleInsertReplaceMethodIgnore oracleInsertReplaceMethodIgnore =
        new OracleInsertReplaceMethodIgnore();
    DBSTable table = mock(DBSTable.class);

    // Act and Assert
    assertNull(
        oracleInsertReplaceMethodIgnore.getTrailingClause(
            table, new LoggingProgressMonitor(), new DBSAttributeBase[] {null}));
  }
}
