package org.jkiss.dbeaver.model.struct.rdb;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSForeignKeyModifyRuleDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSForeignKeyModifyRule#DBSForeignKeyModifyRule(String, String, String)}
   *   <li>{@link DBSForeignKeyModifyRule#getClause()}
   *   <li>{@link DBSForeignKeyModifyRule#getId()}
   *   <li>{@link DBSForeignKeyModifyRule#getName()}
   *   <li>{@link DBSForeignKeyModifyRule#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSForeignKeyModifyRule.<init>(String, String, String)",
    "String DBSForeignKeyModifyRule.getClause()",
    "String DBSForeignKeyModifyRule.getId()",
    "String DBSForeignKeyModifyRule.getName()",
    "String DBSForeignKeyModifyRule.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSForeignKeyModifyRule actualDbsForeignKeyModifyRule =
        new DBSForeignKeyModifyRule("42", "Name", "Clause");
    String actualClause = actualDbsForeignKeyModifyRule.getClause();
    String actualId = actualDbsForeignKeyModifyRule.getId();
    String actualName = actualDbsForeignKeyModifyRule.getName();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualDbsForeignKeyModifyRule.toString());
    assertEquals("Clause", actualClause);
    assertEquals("Name", actualName);
  }
}
