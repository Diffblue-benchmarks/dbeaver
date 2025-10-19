package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TaskRunImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TaskRunImpl#TaskRunImpl(String, Date, String, String, String, String)}
   *   <li>{@link TaskRunImpl#setErrorMessage(String)}
   *   <li>{@link TaskRunImpl#setErrorStackTrace(String)}
   *   <li>{@link TaskRunImpl#setExtraMessage(String)}
   *   <li>{@link TaskRunImpl#setRunDuration(long)}
   *   <li>{@link TaskRunImpl#getErrorMessage()}
   *   <li>{@link TaskRunImpl#getErrorStackTrace()}
   *   <li>{@link TaskRunImpl#getExtraMessage()}
   *   <li>{@link TaskRunImpl#getId()}
   *   <li>{@link TaskRunImpl#getRunDuration()}
   *   <li>{@link TaskRunImpl#getStartTime()}
   *   <li>{@link TaskRunImpl#getStartUser()}
   *   <li>{@link TaskRunImpl#getStartedBy()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TaskRunImpl.<init>(String, Date, String, String, String, String)",
    "String TaskRunImpl.getErrorMessage()",
    "String TaskRunImpl.getErrorStackTrace()",
    "String TaskRunImpl.getExtraMessage()",
    "String TaskRunImpl.getId()",
    "long TaskRunImpl.getRunDuration()",
    "Date TaskRunImpl.getStartTime()",
    "String TaskRunImpl.getStartUser()",
    "String TaskRunImpl.getStartedBy()",
    "void TaskRunImpl.setErrorMessage(String)",
    "void TaskRunImpl.setErrorStackTrace(String)",
    "void TaskRunImpl.setExtraMessage(String)",
    "void TaskRunImpl.setRunDuration(long)"
  })
  public void testGettersAndSetters() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act
    TaskRunImpl actualTaskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");
    actualTaskRunImpl.setErrorMessage("An error occurred");
    actualTaskRunImpl.setErrorStackTrace("An error occurred");
    actualTaskRunImpl.setExtraMessage("Not all who wander are lost");
    actualTaskRunImpl.setRunDuration(1L);
    String actualErrorMessage = actualTaskRunImpl.getErrorMessage();
    String actualErrorStackTrace = actualTaskRunImpl.getErrorStackTrace();
    String actualExtraMessage = actualTaskRunImpl.getExtraMessage();
    String actualId = actualTaskRunImpl.getId();
    long actualRunDuration = actualTaskRunImpl.getRunDuration();
    Date actualStartTime = actualTaskRunImpl.getStartTime();
    String actualStartUser = actualTaskRunImpl.getStartUser();

    // Assert
    assertEquals("42", actualId);
    assertEquals("An error occurred", actualErrorMessage);
    assertEquals("An error occurred", actualErrorStackTrace);
    assertEquals("Not all who wander are lost", actualExtraMessage);
    assertEquals("Start User", actualStartUser);
    assertEquals("Started By", actualTaskRunImpl.getStartedBy());
    assertEquals(1L, actualRunDuration);
    assertSame(startTime, actualStartTime);
  }

  /**
   * Test {@link TaskRunImpl#isRunSuccess()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#isRunSuccess()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.isRunSuccess()"})
  public void testIsRunSuccess_thenReturnFalse() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");

    // Act and Assert
    assertFalse(taskRunImpl.isRunSuccess());
  }

  /**
   * Test {@link TaskRunImpl#isRunSuccess()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#isRunSuccess()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.isRunSuccess()"})
  public void testIsRunSuccess_thenReturnTrue() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl("42", startTime, "Start User", "Started By", null, "An error occurred");

    // Act and Assert
    assertTrue(taskRunImpl.isRunSuccess());
  }

  /**
   * Test {@link TaskRunImpl#isFinished()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#isFinished()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.isFinished()"})
  public void testIsFinished_thenReturnFalse() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");

    // Act and Assert
    assertFalse(taskRunImpl.isFinished());
  }

  /**
   * Test {@link TaskRunImpl#isFinished()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#isFinished()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.isFinished()"})
  public void testIsFinished_thenReturnTrue() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");
    taskRunImpl.setRunDuration(0L);

    // Act and Assert
    assertTrue(taskRunImpl.isFinished());
  }

  /**
   * Test {@link TaskRunImpl#toString()}.
   *
   * <ul>
   *   <li>Then return {@code 42; Start User; Started By; An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TaskRunImpl.toString()"})
  public void testToString_thenReturn42StartUserStartedByAnErrorOccurred() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");

    // Act and Assert
    assertEquals("42; Start User; Started By; An error occurred", taskRunImpl.toString());
  }

  /**
   * Test {@link TaskRunImpl#toString()}.
   *
   * <ul>
   *   <li>Then return {@code 42; Start User; Started By; Success}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TaskRunImpl.toString()"})
  public void testToString_thenReturn42StartUserStartedBySuccess() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl("42", startTime, "Start User", "Started By", null, "An error occurred");

    // Act and Assert
    assertEquals("42; Start User; Started By; Success", taskRunImpl.toString());
  }

  /**
   * Test {@link TaskRunImpl#equals(Object)}, and {@link TaskRunImpl#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TaskRunImpl#equals(Object)}
   *   <li>{@link TaskRunImpl#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.equals(Object)", "int TaskRunImpl.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");
    Date startTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl2 =
        new TaskRunImpl(
            "42", startTime2, "Start User", "Started By", "An error occurred", "An error occurred");

    // Act and Assert
    assertEquals(taskRunImpl, taskRunImpl2);
    assertEquals(taskRunImpl.hashCode(), taskRunImpl2.hashCode());
  }

  /**
   * Test {@link TaskRunImpl#equals(Object)}, and {@link TaskRunImpl#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TaskRunImpl#equals(Object)}
   *   <li>{@link TaskRunImpl#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.equals(Object)", "int TaskRunImpl.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred");

    // Act and Assert
    assertEquals(taskRunImpl, taskRunImpl);
    int expectedHashCodeResult = taskRunImpl.hashCode();
    assertEquals(expectedHashCodeResult, taskRunImpl.hashCode());
  }

  /**
   * Test {@link TaskRunImpl#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.equals(Object)", "int TaskRunImpl.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    TaskRunImpl taskRunImpl =
        new TaskRunImpl(
            "Id", startTime, "Start User", "Started By", "An error occurred", "An error occurred");
    Date startTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        taskRunImpl,
        new TaskRunImpl(
            "42",
            startTime2,
            "Start User",
            "Started By",
            "An error occurred",
            "An error occurred"));
  }

  /**
   * Test {@link TaskRunImpl#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.equals(Object)", "int TaskRunImpl.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred"),
        null);
  }

  /**
   * Test {@link TaskRunImpl#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunImpl#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskRunImpl.equals(Object)", "int TaskRunImpl.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Date startTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    // Act and Assert
    assertNotEquals(
        new TaskRunImpl(
            "42", startTime, "Start User", "Started By", "An error occurred", "An error occurred"),
        "Different type to TaskRunImpl");
  }
}
