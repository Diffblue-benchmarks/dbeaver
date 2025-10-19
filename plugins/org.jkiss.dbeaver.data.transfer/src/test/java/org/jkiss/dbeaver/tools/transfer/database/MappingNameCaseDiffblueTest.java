package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPIdentifierCase;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MappingNameCaseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MappingNameCase#getIdentifierCase()}
   *   <li>{@link MappingNameCase#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPIdentifierCase MappingNameCase.getIdentifierCase()",
    "java.lang.String MappingNameCase.getName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MappingNameCase valueOfResult = MappingNameCase.valueOf("DEFAULT");

    // Act
    DBPIdentifierCase actualIdentifierCase = valueOfResult.getIdentifierCase();

    // Assert
    assertEquals("Default", valueOfResult.getName());
    assertNull(actualIdentifierCase);
  }

  /**
   * Test {@link MappingNameCase#getCaseBySelectionId(int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code UPPER}.
   * </ul>
   *
   * <p>Method under test: {@link MappingNameCase#getCaseBySelectionId(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MappingNameCase MappingNameCase.getCaseBySelectionId(int)"})
  public void testGetCaseBySelectionId_whenOne_thenReturnUpper() {
    // Arrange, Act and Assert
    assertEquals(MappingNameCase.UPPER, MappingNameCase.getCaseBySelectionId(1));
  }

  /**
   * Test {@link MappingNameCase#getCaseBySelectionId(int)}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link MappingNameCase#getCaseBySelectionId(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MappingNameCase MappingNameCase.getCaseBySelectionId(int)"})
  public void testGetCaseBySelectionId_whenThree_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(MappingNameCase.DEFAULT, MappingNameCase.getCaseBySelectionId(3));
  }
}
