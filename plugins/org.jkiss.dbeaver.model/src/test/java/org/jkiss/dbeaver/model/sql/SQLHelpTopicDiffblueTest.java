package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLHelpTopicDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLHelpTopic}
   *   <li>{@link SQLHelpTopic#setContents(String)}
   *   <li>{@link SQLHelpTopic#setExample(String)}
   *   <li>{@link SQLHelpTopic#setUrl(String)}
   *   <li>{@link SQLHelpTopic#getContents()}
   *   <li>{@link SQLHelpTopic#getExample()}
   *   <li>{@link SQLHelpTopic#getUrl()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLHelpTopic.<init>()",
    "String SQLHelpTopic.getContents()",
    "String SQLHelpTopic.getExample()",
    "String SQLHelpTopic.getUrl()",
    "void SQLHelpTopic.setContents(String)",
    "void SQLHelpTopic.setExample(String)",
    "void SQLHelpTopic.setUrl(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLHelpTopic actualSqlHelpTopic = new SQLHelpTopic();
    actualSqlHelpTopic.setContents("Not all who wander are lost");
    actualSqlHelpTopic.setExample("Example");
    actualSqlHelpTopic.setUrl("https://example.org/example");
    String actualContents = actualSqlHelpTopic.getContents();
    String actualExample = actualSqlHelpTopic.getExample();

    // Assert
    assertEquals("Example", actualExample);
    assertEquals("Not all who wander are lost", actualContents);
    assertEquals("https://example.org/example", actualSqlHelpTopic.getUrl());
  }
}
