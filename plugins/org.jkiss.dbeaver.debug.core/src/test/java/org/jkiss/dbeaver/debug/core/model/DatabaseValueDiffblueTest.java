package org.jkiss.dbeaver.debug.core.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.debug.core.DebugException;
import org.jkiss.dbeaver.debug.DBGVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseValueDiffblueTest {
  /**
   * Test {@link DatabaseValue#getValueString()}.
   *
   * <ul>
   *   <li>Given {@link DBGVariable} {@link DBGVariable#getVal()} return {@code Val}.
   *   <li>Then return {@code Val}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseValue#getValueString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseValue.getValueString()"})
  public void testGetValueString_givenDBGVariableGetValReturnVal_thenReturnVal()
      throws DebugException {
    // Arrange
    DBGVariable<Object> dbgObject = mock(DBGVariable.class);
    when(dbgObject.getVal()).thenReturn("Val");
    DatabaseValue databaseValue = new DatabaseValue(null, dbgObject);

    // Act
    String actualValueString = databaseValue.getValueString();

    // Assert
    verify(dbgObject).getVal();
    assertEquals("Val", actualValueString);
  }
}
