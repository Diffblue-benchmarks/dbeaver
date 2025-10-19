package org.jkiss.dbeaver.model.security.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMUserFilterDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPPage#DBPPage(int, int)} with offset is two and limit is one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUserFilter#SMUserFilter(DBPPage)}
   *   <li>{@link SMUserFilter#setEnabledState(Boolean)}
   *   <li>{@link SMUserFilter#setPage(DBPPage)}
   *   <li>{@link SMUserFilter#setUserIdMask(String)}
   *   <li>{@link SMUserFilter#getEnabledState()}
   *   <li>{@link SMUserFilter#getPage()}
   *   <li>{@link SMUserFilter#getUserIdMask()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUserFilter.<init>(String, Boolean, DBPPage)",
    "void SMUserFilter.<init>(DBPPage)",
    "Boolean SMUserFilter.getEnabledState()",
    "DBPPage SMUserFilter.getPage()",
    "String SMUserFilter.getUserIdMask()",
    "void SMUserFilter.setEnabledState(Boolean)",
    "void SMUserFilter.setPage(DBPPage)",
    "void SMUserFilter.setUserIdMask(String)"
  })
  public void testGettersAndSetters_whenDBPPageWithOffsetIsTwoAndLimitIsOne() {
    // Arrange and Act
    SMUserFilter actualSmUserFilter = new SMUserFilter(new DBPPage(2, 1));
    actualSmUserFilter.setEnabledState(true);
    DBPPage page = new DBPPage(2, 1);
    actualSmUserFilter.setPage(page);
    actualSmUserFilter.setUserIdMask("User Id Mask");
    Boolean actualEnabledState = actualSmUserFilter.getEnabledState();
    DBPPage actualPage = actualSmUserFilter.getPage();

    // Assert
    assertEquals("User Id Mask", actualSmUserFilter.getUserIdMask());
    assertTrue(actualEnabledState);
    assertSame(page, actualPage);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code User Id Mask}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMUserFilter#SMUserFilter(String, Boolean, DBPPage)}
   *   <li>{@link SMUserFilter#setEnabledState(Boolean)}
   *   <li>{@link SMUserFilter#setPage(DBPPage)}
   *   <li>{@link SMUserFilter#setUserIdMask(String)}
   *   <li>{@link SMUserFilter#getEnabledState()}
   *   <li>{@link SMUserFilter#getPage()}
   *   <li>{@link SMUserFilter#getUserIdMask()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMUserFilter.<init>(String, Boolean, DBPPage)",
    "void SMUserFilter.<init>(DBPPage)",
    "Boolean SMUserFilter.getEnabledState()",
    "DBPPage SMUserFilter.getPage()",
    "String SMUserFilter.getUserIdMask()",
    "void SMUserFilter.setEnabledState(Boolean)",
    "void SMUserFilter.setPage(DBPPage)",
    "void SMUserFilter.setUserIdMask(String)"
  })
  public void testGettersAndSetters_whenUserIdMask() {
    // Arrange and Act
    SMUserFilter actualSmUserFilter = new SMUserFilter("User Id Mask", true, new DBPPage(2, 1));
    actualSmUserFilter.setEnabledState(true);
    DBPPage page = new DBPPage(2, 1);
    actualSmUserFilter.setPage(page);
    actualSmUserFilter.setUserIdMask("User Id Mask");
    Boolean actualEnabledState = actualSmUserFilter.getEnabledState();
    DBPPage actualPage = actualSmUserFilter.getPage();

    // Assert
    assertEquals("User Id Mask", actualSmUserFilter.getUserIdMask());
    assertTrue(actualEnabledState);
    assertSame(page, actualPage);
  }
}
