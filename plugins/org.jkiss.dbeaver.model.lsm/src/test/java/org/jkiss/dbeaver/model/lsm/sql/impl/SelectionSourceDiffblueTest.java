package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.lsm.sql.impl.SelectionSource.CrossJoin;
import org.jkiss.dbeaver.model.lsm.sql.impl.SelectionSource.NaturalJoin;
import org.jkiss.dbeaver.model.lsm.sql.impl.SelectionSource.Subquery;
import org.jkiss.dbeaver.model.lsm.sql.impl.SelectionSource.Table;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SelectionSourceDiffblueTest {
  /**
   * Test CrossJoin new {@link CrossJoin} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link CrossJoin}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CrossJoin.<init>()"})
  public void testCrossJoinNewCrossJoin() {
    // Arrange, Act and Assert
    assertNull(new CrossJoin().table);
  }

  /**
   * Test NaturalJoin new {@link NaturalJoin} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link NaturalJoin}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NaturalJoin.<init>()"})
  public void testNaturalJoinNewNaturalJoin() {
    // Arrange and Act
    NaturalJoin actualNaturalJoin = new NaturalJoin();

    // Assert
    assertNull(actualNaturalJoin.columnNames);
    assertNull(actualNaturalJoin.kind);
    assertNull(actualNaturalJoin.table);
    assertNull(actualNaturalJoin.condition);
  }

  /**
   * Test Subquery new {@link Subquery} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Subquery}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Subquery.<init>()"})
  public void testSubqueryNewSubquery() {
    // Arrange and Act
    Subquery actualSubquery = new Subquery();

    // Assert
    assertNull(actualSubquery.alias);
    assertNull(actualSubquery.selectionQuery);
  }

  /**
   * Test Table new {@link Table} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Table}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Table.<init>()"})
  public void testTableNewTable() {
    // Arrange and Act
    Table actualTable = new Table();

    // Assert
    assertNull(actualTable.alias);
    assertNull(actualTable.catalogName);
    assertNull(actualTable.schemaName);
    assertNull(actualTable.tableName);
    assertNull(actualTable.columnNames);
  }
}
