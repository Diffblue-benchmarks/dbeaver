package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataTransferStateDiffblueTest {
  /**
   * Test {@link DataTransferState#addError(Throwable)}.
   *
   * <p>Method under test: {@link DataTransferState#addError(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferState.addError(Throwable)"})
  public void testAddError() {
    // Arrange
    DataTransferState dataTransferState = new DataTransferState();
    Throwable error = new Throwable();

    // Act
    dataTransferState.addError(error);

    // Assert
    List<Throwable> loadErrors = dataTransferState.getLoadErrors();
    assertEquals(1, loadErrors.size());
    assertTrue(dataTransferState.hasErrors());
    assertSame(error, loadErrors.get(0));
  }

  /**
   * Test {@link DataTransferState#hasErrors()}.
   *
   * <ul>
   *   <li>Given {@link DataTransferState} (default constructor) addError {@link
   *       Throwable#Throwable()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferState#hasErrors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferState.hasErrors()"})
  public void testHasErrors_givenDataTransferStateAddErrorThrowable_thenReturnTrue() {
    // Arrange
    DataTransferState dataTransferState = new DataTransferState();
    dataTransferState.addError(new Throwable());

    // Act and Assert
    assertTrue(dataTransferState.hasErrors());
  }

  /**
   * Test {@link DataTransferState#hasErrors()}.
   *
   * <ul>
   *   <li>Given {@link DataTransferState} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferState#hasErrors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferState.hasErrors()"})
  public void testHasErrors_givenDataTransferState_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DataTransferState().hasErrors());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DataTransferState}
   *   <li>{@link DataTransferState#getLoadErrors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferState.<init>()", "List DataTransferState.getLoadErrors()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new DataTransferState().getLoadErrors().isEmpty());
  }
}
