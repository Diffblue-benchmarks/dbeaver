package org.jkiss.dbeaver.tools.transfer.serialize;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SerializerContextDiffblueTest {
  /**
   * Test {@link SerializerContext#addError(Throwable)}.
   *
   * <p>Method under test: {@link SerializerContext#addError(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SerializerContext.addError(Throwable)"})
  public void testAddError() {
    // Arrange
    SerializerContext serializerContext = new SerializerContext();
    Throwable error = new Throwable();

    // Act
    serializerContext.addError(error);

    // Assert
    List<Throwable> errors = serializerContext.getErrors();
    assertEquals(1, errors.size());
    assertSame(error, errors.get(0));
  }

  /**
   * Test {@link SerializerContext#resetErrors()}.
   *
   * <p>Method under test: {@link SerializerContext#resetErrors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SerializerContext.resetErrors()"})
  public void testResetErrors() {
    // Arrange, Act and Assert
    assertTrue(new SerializerContext().resetErrors().isEmpty());
  }

  /**
   * Test {@link SerializerContext#isDataSourceFailed(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SerializerContext#isDataSourceFailed(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SerializerContext.isDataSourceFailed(DBPDataSourceContainer)"})
  public void testIsDataSourceFailed_givenDBPDataSourceContainerGetIdReturn42_thenReturnTrue() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    SerializerContext serializerContext = new SerializerContext();
    serializerContext.addDataSourceFail(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    boolean actualIsDataSourceFailedResult =
        serializerContext.isDataSourceFailed(dataSourceContainer2);

    // Assert
    verify(dataSourceContainer).getId();
    verify(dataSourceContainer2).getId();
    assertTrue(actualIsDataSourceFailedResult);
  }

  /**
   * Test {@link SerializerContext#isDataSourceFailed(DBPDataSourceContainer)}.
   *
   * <ul>
   *   <li>Given {@link SerializerContext} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SerializerContext#isDataSourceFailed(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SerializerContext.isDataSourceFailed(DBPDataSourceContainer)"})
  public void testIsDataSourceFailed_givenSerializerContext_thenReturnFalse() {
    // Arrange
    SerializerContext serializerContext = new SerializerContext();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    boolean actualIsDataSourceFailedResult =
        serializerContext.isDataSourceFailed(dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getId();
    assertFalse(actualIsDataSourceFailedResult);
  }

  /**
   * Test {@link SerializerContext#addDataSourceFail(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link SerializerContext#addDataSourceFail(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SerializerContext.addDataSourceFail(DBPDataSourceContainer)"})
  public void testAddDataSourceFail() {
    // Arrange
    SerializerContext serializerContext = new SerializerContext();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    serializerContext.addDataSourceFail(dataSourceContainer);

    // Assert
    verify(dataSourceContainer).getId();
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SerializerContext}
   *   <li>{@link SerializerContext#getErrors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SerializerContext.<init>()", "List SerializerContext.getErrors()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertTrue(new SerializerContext().getErrors().isEmpty());
  }
}
