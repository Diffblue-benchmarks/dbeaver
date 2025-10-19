package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleLazyReferenceDiffblueTest {
  /**
   * Test {@link OracleLazyReference#OracleLazyReference(String, String)}.
   *
   * <p>Method under test: {@link OracleLazyReference#OracleLazyReference(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleLazyReference.<init>(String, String)"})
  public void testNewOracleLazyReference() {
    // Arrange and Act
    OracleLazyReference actualOracleLazyReference =
        new OracleLazyReference("Schema Name", "Object Name");

    // Assert
    assertEquals("Object Name", actualOracleLazyReference.objectName);
    assertEquals("Schema Name", actualOracleLazyReference.schemaName);
  }
}
