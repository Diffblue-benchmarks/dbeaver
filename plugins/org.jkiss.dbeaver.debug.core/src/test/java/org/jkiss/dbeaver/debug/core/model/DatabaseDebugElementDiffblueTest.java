package org.jkiss.dbeaver.debug.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.debug.DBGController;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseDebugElementDiffblueTest {
  /**
   * Test {@link DatabaseDebugElement#getDatabaseDebugTarget()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseDebugElement#getDatabaseDebugTarget()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.debug.core.model.IDatabaseDebugTarget DatabaseDebugElement.getDatabaseDebugTarget()"
  })
  public void testGetDatabaseDebugTarget_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DatabaseDebugElement(null).getDatabaseDebugTarget());
  }

  /**
   * Test {@link DatabaseDebugElement#getController()}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseDebugTarget#getController()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseDebugElement#getController()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBGController DatabaseDebugElement.getController()"})
  public void testGetController_thenCallsGetController() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.getController()).thenReturn(mock(DBGController.class));

    // Act
    new DatabaseDebugElement(target).getController();

    // Assert
    verify(target).getController();
  }

  /**
   * Test {@link DatabaseDebugElement#getModelIdentifier()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseDebugElement#getModelIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseDebugElement.getModelIdentifier()"})
  public void testGetModelIdentifier_thenReturn42() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.getModelIdentifier()).thenReturn("42");

    // Act
    String actualModelIdentifier = new DatabaseDebugElement(target).getModelIdentifier();

    // Assert
    verify(target).getModelIdentifier();
    assertEquals("42", actualModelIdentifier);
  }
}
