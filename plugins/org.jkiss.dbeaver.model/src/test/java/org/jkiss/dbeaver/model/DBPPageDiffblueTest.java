package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPPageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPPage#DBPPage(int, int)}
   *   <li>{@link DBPPage#setLimit(int)}
   *   <li>{@link DBPPage#setOffset(int)}
   *   <li>{@link DBPPage#getLimit()}
   *   <li>{@link DBPPage#getOffset()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPPage.<init>(int, int)",
    "int DBPPage.getLimit()",
    "int DBPPage.getOffset()",
    "void DBPPage.setLimit(int)",
    "void DBPPage.setOffset(int)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPPage actualDbpPage = new DBPPage(2, 1);
    actualDbpPage.setLimit(1);
    actualDbpPage.setOffset(2);
    int actualLimit = actualDbpPage.getLimit();

    // Assert
    assertEquals(1, actualLimit);
    assertEquals(2, actualDbpPage.getOffset());
  }
}
