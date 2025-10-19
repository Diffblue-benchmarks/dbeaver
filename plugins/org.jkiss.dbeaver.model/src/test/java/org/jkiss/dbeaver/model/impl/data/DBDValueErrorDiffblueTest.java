package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDValueErrorDiffblueTest {
  /**
   * Test {@link DBDValueError#getErrorTitle()}.
   *
   * <ul>
   *   <li>Given {@link DBDValueError#DBDValueError(Throwable)} with error is {@link
   *       Throwable#Throwable()}.
   *   <li>Then return {@code Throwable:}.
   * </ul>
   *
   * <p>Method under test: {@link DBDValueError#getErrorTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DBDValueError.getErrorTitle()"})
  public void testGetErrorTitle_givenDBDValueErrorWithErrorIsThrowable_thenReturnThrowable() {
    // Arrange, Act and Assert
    assertEquals("Throwable: ", new DBDValueError(new Throwable()).getErrorTitle());
  }

  /**
   * Test {@link DBDValueError#getErrorTitle()}.
   *
   * <ul>
   *   <li>Then return {@code Exception: An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link DBDValueError#getErrorTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String DBDValueError.getErrorTitle()"})
  public void testGetErrorTitle_thenReturnExceptionAnErrorOccurred() {
    // Arrange, Act and Assert
    assertEquals(
        "Exception: An error occurred",
        new DBDValueError(new Exception("An error occurred")).getErrorTitle());
  }
}
