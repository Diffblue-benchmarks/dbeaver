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

public class DBSLogicalSchemaDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBSLogicalSchema}
   *   <li>{@link DBSLogicalSchema#setEntities(List)}
   *   <li>{@link DBSLogicalSchema#getEntities()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSLogicalSchema.<init>()",
    "List DBSLogicalSchema.getEntities()",
    "void DBSLogicalSchema.setEntities(List)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSLogicalSchema actualDbsLogicalSchema = new DBSLogicalSchema();
    ArrayList<DBSLogicalEntity> entities = new ArrayList<>();
    actualDbsLogicalSchema.setEntities(entities);
    List<DBSLogicalEntity> actualEntities = actualDbsLogicalSchema.getEntities();

    // Assert
    assertTrue(actualEntities.isEmpty());
    assertSame(entities, actualEntities);
  }
}
