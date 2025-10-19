package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.qm.meta.QMMObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMetaEventEntityDiffblueTest {
  /**
   * Test {@link QMMetaEventEntity#QMMetaEventEntity(QMMObject, QMEventAction, long, String,
   * QMSessionInfo)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Object is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMMetaEventEntity#QMMetaEventEntity(QMMObject, QMEventAction,
   * long, String, QMSessionInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMMetaEventEntity.<init>(QMMObject, QMEventAction, long, String, QMSessionInfo)"
  })
  public void testNewQMMetaEventEntity_whenNull_thenReturnObjectIsNull() {
    // Arrange
    QMSessionInfo sessionInfo = new QMSessionInfo("janedoe", "User Domain", "User Ip");

    // Act
    QMMetaEventEntity actualQmMetaEventEntity =
        new QMMetaEventEntity(null, QMEventAction.BEGIN, 1L, "42", sessionInfo);

    // Assert
    assertNull(actualQmMetaEventEntity.getObject());
    assertEquals(1L, actualQmMetaEventEntity.getId());
    assertEquals(QMEventAction.BEGIN, actualQmMetaEventEntity.getAction());
    assertSame(sessionInfo, actualQmMetaEventEntity.getSessionInfo());
  }
}
