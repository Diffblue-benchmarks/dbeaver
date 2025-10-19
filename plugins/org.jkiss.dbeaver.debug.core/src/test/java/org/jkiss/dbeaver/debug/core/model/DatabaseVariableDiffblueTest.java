package org.jkiss.dbeaver.debug.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IValue;
import org.jkiss.dbeaver.debug.DBGVariable;
import org.jkiss.dbeaver.debug.DBGVariableType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseVariableDiffblueTest {
  /**
   * Test {@link DatabaseVariable#supportsValueModification()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseVariable#supportsValueModification()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseVariable.supportsValueModification()"})
  public void testSupportsValueModification_thenReturnFalse() {
    // Arrange
    DatabaseVariable databaseVariable = new DatabaseVariable(null, mock(DBGVariable.class));

    // Act and Assert
    assertFalse(databaseVariable.supportsValueModification());
  }

  /**
   * Test {@link DatabaseVariable#verifyValue(String)} with {@code expression}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseVariable#verifyValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseVariable.verifyValue(String)"})
  public void testVerifyValueWithExpression_thenReturnFalse() throws DebugException {
    // Arrange
    DatabaseVariable databaseVariable = new DatabaseVariable(null, mock(DBGVariable.class));

    // Act and Assert
    assertFalse(databaseVariable.verifyValue("Expression"));
  }

  /**
   * Test {@link DatabaseVariable#getValue()}.
   *
   * <ul>
   *   <li>Then return {@link DatabaseValue}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseVariable#getValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IValue DatabaseVariable.getValue()"})
  public void testGetValue_thenReturnDatabaseValue() throws DebugException {
    // Arrange
    DatabaseVariable databaseVariable = new DatabaseVariable(null, mock(DBGVariable.class));

    // Act
    IValue actualValue = databaseVariable.getValue();

    // Assert
    assertTrue(actualValue instanceof DatabaseValue);
    assertEquals("null", actualValue.getValueString());
    assertNull(actualValue.getReferenceTypeName());
    assertNull(actualValue.getDebugTarget());
    assertNull(actualValue.getVariables());
    assertNull(((DatabaseValue) actualValue).getDatabaseDebugTarget());
    assertFalse(actualValue.hasVariables());
    assertFalse(actualValue.isAllocated());
  }

  /**
   * Test {@link DatabaseVariable#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBGVariable} {@link DBGVariable#getName()} return {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseVariable#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseVariable.getName()"})
  public void testGetName_givenDBGVariableGetNameReturnName_thenReturnName() throws DebugException {
    // Arrange
    DBGVariable<Object> variable = mock(DBGVariable.class);
    when(variable.getName()).thenReturn("Name");
    DatabaseVariable databaseVariable = new DatabaseVariable(null, variable);

    // Act
    String actualName = databaseVariable.getName();

    // Assert
    verify(variable).getName();
    assertEquals("Name", actualName);
  }

  /**
   * Test {@link DatabaseVariable#getReferenceTypeName()}.
   *
   * <ul>
   *   <li>Given {@link DBGVariable} {@link DBGVariable#getType()} return {@code NUMBER}.
   *   <li>Then return {@code NUMBER}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseVariable#getReferenceTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseVariable.getReferenceTypeName()"})
  public void testGetReferenceTypeName_givenDBGVariableGetTypeReturnNumber_thenReturnNumber()
      throws DebugException {
    // Arrange
    DBGVariable<Object> variable = mock(DBGVariable.class);
    when(variable.getType()).thenReturn(DBGVariableType.NUMBER);
    DatabaseVariable databaseVariable = new DatabaseVariable(null, variable);

    // Act
    String actualReferenceTypeName = databaseVariable.getReferenceTypeName();

    // Assert
    verify(variable).getType();
    assertEquals("NUMBER", actualReferenceTypeName);
  }
}
