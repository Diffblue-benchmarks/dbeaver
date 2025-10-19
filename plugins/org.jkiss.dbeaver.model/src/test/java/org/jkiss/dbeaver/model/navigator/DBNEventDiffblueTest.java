package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.navigator.DBNEvent.Action;
import org.jkiss.dbeaver.model.navigator.DBNEvent.NodeChange;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBNEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBNEvent#DBNEvent(Object, Action, NodeChange, DBNNode)}
   *   <li>{@link DBNEvent#toString()}
   *   <li>{@link DBNEvent#getAction()}
   *   <li>{@link DBNEvent#getNode()}
   *   <li>{@link DBNEvent#getNodeChange()}
   *   <li>{@link DBNEvent#getSource()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBNEvent.<init>(Object, Action, NodeChange, DBNNode)",
    "Action DBNEvent.getAction()",
    "DBNNode DBNEvent.getNode()",
    "NodeChange DBNEvent.getNodeChange()",
    "Object DBNEvent.getSource()",
    "String DBNEvent.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    // Act
    DBNEvent actualDbnEvent =
        new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, NodeChange.BEFORE_LOAD, node);
    String actualToStringResult = actualDbnEvent.toString();
    Action actualAction = actualDbnEvent.getAction();
    DBNNode actualNode = actualDbnEvent.getNode();
    NodeChange actualNodeChange = actualDbnEvent.getNodeChange();

    // Assert
    assertEquals("ADD:BEFORE_LOAD:node://#empty", actualToStringResult);
    assertEquals(Action.ADD, actualAction);
    assertEquals(NodeChange.BEFORE_LOAD, actualNodeChange);
    assertSame(node, actualNode);
    assertSame(DBNEvent.FORCE_REFRESH, actualDbnEvent.getSource());
  }

  /**
   * Test {@link DBNEvent#DBNEvent(Object, Action, DBNNode)}.
   *
   * <p>Method under test: {@link DBNEvent#DBNEvent(Object, Action, DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNEvent.<init>(Object, Action, DBNNode)"})
  public void testNewDBNEvent() {
    // Arrange
    DBNEmptyNode node = new DBNEmptyNode();

    // Act
    DBNEvent actualDbnEvent = new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, node);

    // Assert
    DBNNode node2 = actualDbnEvent.getNode();
    assertTrue(node2 instanceof DBNEmptyNode);
    assertEquals(Action.ADD, actualDbnEvent.getAction());
    assertEquals(NodeChange.REFRESH, actualDbnEvent.getNodeChange());
    assertSame(node, node2);
    assertSame(DBNEvent.FORCE_REFRESH, actualDbnEvent.getSource());
  }
}
