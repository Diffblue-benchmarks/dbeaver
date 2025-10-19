package org.jkiss.dbeaver.model.logical;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSLogicalEntityDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBSLogicalEntity}
   *   <li>{@link DBSLogicalEntity#setAssociationPatterns(List)}
   *   <li>{@link DBSLogicalEntity#setAttributePatterns(List)}
   *   <li>{@link DBSLogicalEntity#getAssociationPatterns()}
   *   <li>{@link DBSLogicalEntity#getAttributePatterns()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSLogicalEntity.<init>()",
    "List DBSLogicalEntity.getAssociationPatterns()",
    "List DBSLogicalEntity.getAttributePatterns()",
    "void DBSLogicalEntity.setAssociationPatterns(List)",
    "void DBSLogicalEntity.setAttributePatterns(List)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSLogicalEntity actualDbsLogicalEntity = new DBSLogicalEntity();
    ArrayList<String> associationPatterns = new ArrayList<>();
    actualDbsLogicalEntity.setAssociationPatterns(associationPatterns);
    ArrayList<String> attributePatterns = new ArrayList<>();
    actualDbsLogicalEntity.setAttributePatterns(attributePatterns);
    List<String> actualAssociationPatterns = actualDbsLogicalEntity.getAssociationPatterns();
    List<String> actualAttributePatterns = actualDbsLogicalEntity.getAttributePatterns();

    // Assert
    assertTrue(actualAssociationPatterns.isEmpty());
    assertTrue(actualAttributePatterns.isEmpty());
    assertSame(associationPatterns, actualAssociationPatterns);
    assertSame(attributePatterns, actualAttributePatterns);
  }
}
