package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.qm.meta.QMMObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMMetaEventDiffblueTest {
  /**
   * Test {@link QMMetaEvent#QMMetaEvent(QMMObject, QMEventAction, long, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return SessionId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link QMMetaEvent#QMMetaEvent(QMMObject, QMEventAction, long, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMMetaEvent.<init>(QMMObject, QMEventAction, long, String)"})
  public void testNewQMMetaEvent_whenNull_thenReturnSessionIdIs42() {
    // Arrange and Act
    QMMetaEvent actualQmMetaEvent = new QMMetaEvent(null, QMEventAction.BEGIN, 10L, "42");

    // Assert
    assertEquals("42", actualQmMetaEvent.getSessionId());
    assertNull(actualQmMetaEvent.getObject());
    assertEquals(10L, actualQmMetaEvent.getTimestamp());
    assertEquals(QMEventAction.BEGIN, actualQmMetaEvent.getAction());
  }
}
