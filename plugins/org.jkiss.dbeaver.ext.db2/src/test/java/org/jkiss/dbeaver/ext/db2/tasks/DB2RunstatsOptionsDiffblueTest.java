package org.jkiss.dbeaver.ext.db2.tasks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2RunstatsOptionsDiffblueTest {
  /**
   * Test {@link DB2RunstatsOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code All with Distribution}.
   *   <li>Then return {@code colsAllAndDistribution}.
   * </ul>
   *
   * <p>Method under test: {@link DB2RunstatsOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2RunstatsOptions DB2RunstatsOptions.getOption(String)"})
  public void testGetOption_whenAllWithDistribution_thenReturnColsAllAndDistribution() {
    // Arrange, Act and Assert
    assertEquals(
        DB2RunstatsOptions.colsAllAndDistribution,
        DB2RunstatsOptions.getOption("All with Distribution"));
  }

  /**
   * Test {@link DB2RunstatsOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2RunstatsOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2RunstatsOptions DB2RunstatsOptions.getOption(String)"})
  public void testGetOption_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DB2RunstatsOptions.getOption(null));
  }

  /**
   * Test {@link DB2RunstatsOptions#getOption(String)}.
   *
   * <ul>
   *   <li>When {@code The characteristics of someone or something}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DB2RunstatsOptions#getOption(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DB2RunstatsOptions DB2RunstatsOptions.getOption(String)"})
  public void testGetOption_whenTheCharacteristicsOfSomeoneOrSomething_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DB2RunstatsOptions.getOption("The characteristics of someone or something"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2RunstatsOptions#getDdlString()}
   *   <li>{@link DB2RunstatsOptions#getDesc()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DB2RunstatsOptions.getDdlString()",
    "String DB2RunstatsOptions.getDesc()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DB2RunstatsOptions valueOfResult = DB2RunstatsOptions.valueOf("colsAllAndDistribution");

    // Act
    String actualDdlString = valueOfResult.getDdlString();

    // Assert
    assertEquals("All with Distribution", valueOfResult.getDesc());
    assertEquals("ON ALL COLUMNS WITH DISTRIBUTION ON ALL COLUMNS", actualDdlString);
  }
}
