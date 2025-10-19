package org.jkiss.dbeaver.debug;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBGEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Details is one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBGEvent#DBGEvent(Object, int, int)}
   *   <li>{@link DBGEvent#getDetails()}
   *   <li>{@link DBGEvent#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBGEvent.<init>(Object, int)",
    "void DBGEvent.<init>(Object, int, int)",
    "int DBGEvent.getDetails()",
    "int DBGEvent.getKind()"
  })
  public void testGettersAndSetters_thenReturnDetailsIsOne() {
    // Arrange and Act
    DBGEvent actualDbgEvent = new DBGEvent("Source", 1, 1);
    int actualDetails = actualDbgEvent.getDetails();
    int actualKind = actualDbgEvent.getKind();

    // Assert
    assertEquals("Source", actualDbgEvent.getSource());
    assertEquals(1, actualDetails);
    assertEquals(1, actualKind);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Details is zero.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBGEvent#DBGEvent(Object, int)}
   *   <li>{@link DBGEvent#getDetails()}
   *   <li>{@link DBGEvent#getKind()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBGEvent.<init>(Object, int)",
    "void DBGEvent.<init>(Object, int, int)",
    "int DBGEvent.getDetails()",
    "int DBGEvent.getKind()"
  })
  public void testGettersAndSetters_thenReturnDetailsIsZero() {
    // Arrange and Act
    DBGEvent actualDbgEvent = new DBGEvent("Source", 1);
    int actualDetails = actualDbgEvent.getDetails();
    int actualKind = actualDbgEvent.getKind();

    // Assert
    assertEquals("Source", actualDbgEvent.getSource());
    assertEquals(0, actualDetails);
    assertEquals(1, actualKind);
  }
}
