package org.jkiss.dbeaver.model.impl.local;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.exec.DBCAttributeMetaData;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalResultSetMetaDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalResultSetMeta#LocalResultSetMeta(List)}
   *   <li>{@link LocalResultSetMeta#getAttributes()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalResultSetMeta.<init>(List)",
    "List LocalResultSetMeta.getAttributes()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<DBCAttributeMetaData> attributes = new ArrayList<>();

    // Act
    List<? extends DBCAttributeMetaData> actualAttributes =
        new LocalResultSetMeta(attributes).getAttributes();

    // Assert
    assertTrue(actualAttributes.isEmpty());
    assertSame(attributes, actualAttributes);
  }
}
