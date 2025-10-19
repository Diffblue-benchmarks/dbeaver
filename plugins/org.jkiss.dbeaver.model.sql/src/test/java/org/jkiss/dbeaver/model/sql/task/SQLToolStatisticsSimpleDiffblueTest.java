package org.jkiss.dbeaver.model.sql.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPObject;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute;
import org.jkiss.dbeaver.model.data.DBDPseudoAttribute.PropagationPolicy;
import org.jkiss.dbeaver.model.data.DBDPseudoAttributeType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLToolStatisticsSimpleDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLToolStatisticsSimple#setStatusMessage(String)}
   *   <li>{@link SQLToolStatisticsSimple#getStatusMessage()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLToolStatisticsSimple.getStatusMessage()",
    "void SQLToolStatisticsSimple.setStatusMessage(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);
    SQLToolStatisticsSimple sqlToolStatisticsSimple = new SQLToolStatisticsSimple(object, true);

    // Act
    sqlToolStatisticsSimple.setStatusMessage("Not all who wander are lost");

    // Assert
    assertEquals("Not all who wander are lost", sqlToolStatisticsSimple.getStatusMessage());
  }

  /**
   * Test {@link SQLToolStatisticsSimple#SQLToolStatisticsSimple(DBPObject, boolean)}.
   *
   * <ul>
   *   <li>Then return StatusMessage is {@code OK}.
   * </ul>
   *
   * <p>Method under test: {@link SQLToolStatisticsSimple#SQLToolStatisticsSimple(DBPObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLToolStatisticsSimple.<init>(DBPObject, boolean)"})
  public void testNewSQLToolStatisticsSimple_thenReturnStatusMessageIsOk() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);
    SQLToolStatisticsSimple object2 = new SQLToolStatisticsSimple(object, true);

    // Act
    SQLToolStatisticsSimple actualSqlToolStatisticsSimple =
        new SQLToolStatisticsSimple(object2, false);

    // Assert
    assertEquals("OK", actualSqlToolStatisticsSimple.getStatusMessage());
    assertSame(object2, actualSqlToolStatisticsSimple.getObject());
  }

  /**
   * Test {@link SQLToolStatisticsSimple#SQLToolStatisticsSimple(DBPObject, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return StatusMessage is {@code ERROR}.
   * </ul>
   *
   * <p>Method under test: {@link SQLToolStatisticsSimple#SQLToolStatisticsSimple(DBPObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLToolStatisticsSimple.<init>(DBPObject, boolean)"})
  public void testNewSQLToolStatisticsSimple_whenTrue_thenReturnStatusMessageIsError() {
    // Arrange
    DBDPseudoAttribute object =
        new DBDPseudoAttribute(
            DBDPseudoAttributeType.ROWID,
            "Name",
            "Query Expression",
            "Alias",
            "The characteristics of someone or something",
            true,
            PropagationPolicy.GLOBAL_VARIABLE);

    // Act
    SQLToolStatisticsSimple actualSqlToolStatisticsSimple =
        new SQLToolStatisticsSimple(object, true);

    // Assert
    assertEquals("ERROR", actualSqlToolStatisticsSimple.getStatusMessage());
    assertSame(object, actualSqlToolStatisticsSimple.getObject());
  }
}
