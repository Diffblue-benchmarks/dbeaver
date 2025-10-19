package org.jkiss.dbeaver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBExceptionDiffblueTest {
  /**
   * Test {@link DBException#DBException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBException#DBException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBException.<init>(String)",
    "void DBException.<init>(String, Throwable)"
  })
  public void testNewDBException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    DBException actualDbException = new DBException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualDbException.getMessage());
    assertNull(actualDbException.getCause());
    assertEquals(0, actualDbException.getSuppressed().length);
  }

  /**
   * Test {@link DBException#DBException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Cause is {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link DBException#DBException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBException.<init>(String)",
    "void DBException.<init>(String, Throwable)"
  })
  public void testNewDBException_whenThrowable_thenReturnCauseIsThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    DBException actualDbException = new DBException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualDbException.getMessage());
    assertEquals(0, actualDbException.getSuppressed().length);
    assertSame(cause, actualDbException.getCause());
  }

  /**
   * Test {@link DBException#equals(Object)}, and {@link DBException#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBException dbException = new DBException("An error occurred");
    DBException dbException2 = new DBException("An error occurred");

    // Act and Assert
    assertEquals(dbException, dbException2);
    assertNotEquals(dbException.hashCode(), dbException2.hashCode());
  }

  /**
   * Test {@link DBException#equals(Object)}, and {@link DBException#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    DBDatabaseException dbDatabaseException =
        new DBDatabaseException("An error occurred", new Throwable());
    DBDatabaseException dbDatabaseException2 =
        new DBDatabaseException("An error occurred", new Throwable());

    // Act and Assert
    assertEquals(dbDatabaseException, dbDatabaseException2);
    assertNotEquals(dbDatabaseException.hashCode(), dbDatabaseException2.hashCode());
  }

  /**
   * Test {@link DBException#equals(Object)}, and {@link DBException#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBException dbException = new DBException("An error occurred");

    // Act and Assert
    assertEquals(dbException, dbException);
    int expectedHashCodeResult = dbException.hashCode();
    assertEquals(expectedHashCodeResult, dbException.hashCode());
  }

  /**
   * Test {@link DBException#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBException dbException = new DBException("Not all who wander are lost");

    // Act and Assert
    assertNotEquals(dbException, new DBException("An error occurred"));
  }

  /**
   * Test {@link DBException#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBDatabaseException dbDatabaseException =
        new DBDatabaseException("An error occurred", new Throwable());

    // Act and Assert
    assertNotEquals(dbDatabaseException, new DBException("An error occurred"));
  }

  /**
   * Test {@link DBException#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBException dbException = new DBException("An error occurred");
    DBDatabaseException dbDatabaseException =
        new DBDatabaseException("An error occurred", new Throwable());

    // Act and Assert
    assertNotEquals(dbException, dbDatabaseException);
  }

  /**
   * Test {@link DBException#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBException("An error occurred"), null);
  }

  /**
   * Test {@link DBException#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBException#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBException.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBException("An error occurred"), "Different type to DBException");
  }
}
