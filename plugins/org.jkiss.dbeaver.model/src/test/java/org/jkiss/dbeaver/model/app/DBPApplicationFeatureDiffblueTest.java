package org.jkiss.dbeaver.model.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPApplicationFeatureDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPApplicationFeature#DBPApplicationFeature(DBPApplicationFeature, String, String,
   *       String)}
   *   <li>{@link DBPApplicationFeature#getDescription()}
   *   <li>{@link DBPApplicationFeature#getId()}
   *   <li>{@link DBPApplicationFeature#getLabel()}
   *   <li>{@link DBPApplicationFeature#getParent()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPApplicationFeature.<init>(DBPApplicationFeature, String, String, String)",
    "String DBPApplicationFeature.getDescription()",
    "String DBPApplicationFeature.getId()",
    "String DBPApplicationFeature.getLabel()",
    "DBPApplicationFeature DBPApplicationFeature.getParent()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPApplicationFeature actualDbpApplicationFeature =
        new DBPApplicationFeature(
            null, "42", "Label", "The characteristics of someone or something");
    String actualDescription = actualDbpApplicationFeature.getDescription();
    String actualId = actualDbpApplicationFeature.getId();
    String actualLabel = actualDbpApplicationFeature.getLabel();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Label", actualLabel);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualDbpApplicationFeature.getParent());
  }
}
