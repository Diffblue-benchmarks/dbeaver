package org.jkiss.dbeaver.ext.db2.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2TableTruncateOptionsDiffblueTest {
  /**
   * Test {@link DB2TableTruncateOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code Drop}.
   *   <li>Then return {@code dropStorage}.
   * </ul>
   *
   * <p>Method under test: {@link DB2TableTruncateOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2TableTruncateOptions DB2TableTruncateOptions.getOption(String)"})
  public void testGetOption_whenDrop_thenReturnDropStorage() {
    // Arrange, Act and Assert
    assertEquals(DB2TableTruncateOptions.dropStorage, DB2TableTruncateOptions.getOption("Drop"));
  }

  /**
   * Test {@link DB2TableTruncateOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2TableTruncateOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2TableTruncateOptions DB2TableTruncateOptions.getOption(String)"})
  public void testGetOption_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DB2TableTruncateOptions.getOption(null));
  }

  /**
   * Test {@link DB2TableTruncateOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code The characteristics of someone or something}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2TableTruncateOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2TableTruncateOptions DB2TableTruncateOptions.getOption(String)"})
  public void testGetOption_whenTheCharacteristicsOfSomeoneOrSomething_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DB2TableTruncateOptions.getOption("The characteristics of someone or something"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2TableTruncateOptions#getDdlString()}
   *   <li>{@link DB2TableTruncateOptions#getDesc()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DB2TableTruncateOptions.getDdlString()",
    "String DB2TableTruncateOptions.getDesc()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DB2TableTruncateOptions valueOfResult = DB2TableTruncateOptions.valueOf("dropStorage");

    // Act
    String actualDdlString = valueOfResult.getDdlString();

    // Assert
    assertEquals("DROP STORAGE", actualDdlString);
    assertEquals("Drop", valueOfResult.getDesc());
  }
}
