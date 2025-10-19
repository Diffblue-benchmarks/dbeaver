package org.jkiss.dbeaver.model.qm.meta;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMetaObjectTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMMetaObjectType#getId()}
   *   <li>{@link QMMetaObjectType#getObjectClass()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int QMMetaObjectType.getId()", "Class QMMetaObjectType.getObjectClass()"})
  public void testGettersAndSetters() {
    // Arrange
    QMMetaObjectType valueOfResult = QMMetaObjectType.valueOf("CONNECTION_INFO");

    // Act
    int actualId = valueOfResult.getId();
    Class<? extends QMMObject> actualObjectClass = valueOfResult.getObjectClass();

    // Assert
    assertEquals(1, actualId);
    Class<QMMConnectionInfo> expectedObjectClass = QMMConnectionInfo.class;
    assertEquals(expectedObjectClass, actualObjectClass);
  }
}
