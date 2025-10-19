package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSActionTimingDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSActionTiming#DBSActionTiming(String)}
   *   <li>{@link DBSActionTiming#toString()}
   *   <li>{@link DBSActionTiming#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSActionTiming.<init>(String)",
    "String DBSActionTiming.getName()",
    "String DBSActionTiming.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSActionTiming actualDbsActionTiming = new DBSActionTiming("Name");
    String actualToStringResult = actualDbsActionTiming.toString();

    // Assert
    assertEquals("Name", actualDbsActionTiming.getName());
    assertEquals("Name", actualToStringResult);
  }

  /**
   * Test {@link DBSActionTiming#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code AFTER}.
   *   <li>Then return Name is {@code AFTER}.
   * </ul>
   *
   * <p>Method under test: {@link DBSActionTiming#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSActionTiming DBSActionTiming.getByName(String)"})
  public void testGetByName_whenAfter_thenReturnNameIsAfter() {
    // Arrange, Act and Assert
    assertEquals("AFTER", DBSActionTiming.getByName("AFTER").getName());
  }

  /**
   * Test {@link DBSActionTiming#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code BEFORE}.
   *   <li>Then return Name is {@code BEFORE}.
   * </ul>
   *
   * <p>Method under test: {@link DBSActionTiming#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSActionTiming DBSActionTiming.getByName(String)"})
  public void testGetByName_whenBefore_thenReturnNameIsBefore() {
    // Arrange, Act and Assert
    assertEquals("BEFORE", DBSActionTiming.getByName("BEFORE").getName());
  }

  /**
   * Test {@link DBSActionTiming#getByName(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return Name is {@code UNKNOWN}.
   * </ul>
   *
   * <p>Method under test: {@link DBSActionTiming#getByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSActionTiming DBSActionTiming.getByName(String)"})
  public void testGetByName_whenName_thenReturnNameIsUnknown() {
    // Arrange, Act and Assert
    assertEquals("UNKNOWN", DBSActionTiming.getByName("Name").getName());
  }
}
