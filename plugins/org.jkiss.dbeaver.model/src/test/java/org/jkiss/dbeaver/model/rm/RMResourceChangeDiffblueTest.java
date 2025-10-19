package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMResourceChangeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResourceChange#RMResourceChange()}
   *   <li>{@link RMResourceChange#setChangeId(String)}
   *   <li>{@link RMResourceChange#setChangeTime(Date)}
   *   <li>{@link RMResourceChange#setChangeUser(Date)}
   *   <li>{@link RMResourceChange#getChangeId()}
   *   <li>{@link RMResourceChange#getChangeTime()}
   *   <li>{@link RMResourceChange#getChangeUser()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResourceChange.<init>()",
    "void RMResourceChange.<init>(String, Date, Date)",
    "String RMResourceChange.getChangeId()",
    "Date RMResourceChange.getChangeTime()",
    "Date RMResourceChange.getChangeUser()",
    "void RMResourceChange.setChangeId(String)",
    "void RMResourceChange.setChangeTime(Date)",
    "void RMResourceChange.setChangeUser(Date)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RMResourceChange actualRmResourceChange = new RMResourceChange();
    actualRmResourceChange.setChangeId("42");
    Date changeTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualRmResourceChange.setChangeTime(changeTime);
    Date changeUser =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualRmResourceChange.setChangeUser(changeUser);
    String actualChangeId = actualRmResourceChange.getChangeId();
    Date actualChangeTime = actualRmResourceChange.getChangeTime();

    // Assert
    assertEquals("42", actualChangeId);
    assertSame(changeTime, actualChangeTime);
    assertSame(changeUser, actualRmResourceChange.getChangeUser());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMResourceChange#RMResourceChange(String, Date, Date)}
   *   <li>{@link RMResourceChange#setChangeId(String)}
   *   <li>{@link RMResourceChange#setChangeTime(Date)}
   *   <li>{@link RMResourceChange#setChangeUser(Date)}
   *   <li>{@link RMResourceChange#getChangeId()}
   *   <li>{@link RMResourceChange#getChangeTime()}
   *   <li>{@link RMResourceChange#getChangeUser()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMResourceChange.<init>()",
    "void RMResourceChange.<init>(String, Date, Date)",
    "String RMResourceChange.getChangeId()",
    "Date RMResourceChange.getChangeTime()",
    "Date RMResourceChange.getChangeUser()",
    "void RMResourceChange.setChangeId(String)",
    "void RMResourceChange.setChangeTime(Date)",
    "void RMResourceChange.setChangeUser(Date)"
  })
  public void testGettersAndSetters_when42() {
    // Arrange
    Date changeTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date changeUser =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    RMResourceChange actualRmResourceChange = new RMResourceChange("42", changeTime, changeUser);
    actualRmResourceChange.setChangeId("42");
    Date changeTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualRmResourceChange.setChangeTime(changeTime2);
    Date changeUser2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    actualRmResourceChange.setChangeUser(changeUser2);
    String actualChangeId = actualRmResourceChange.getChangeId();
    Date actualChangeTime = actualRmResourceChange.getChangeTime();

    // Assert
    assertEquals("42", actualChangeId);
    assertSame(changeTime2, actualChangeTime);
    assertSame(changeUser2, actualRmResourceChange.getChangeUser());
  }
}
