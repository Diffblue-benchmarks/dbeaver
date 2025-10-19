package org.jkiss.dbeaver.model.impl.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BaseValueHandlerDiffblueTest {
  /**
   * Test {@link BaseValueHandler#getValueContentType(DBSTypedObject)}.
   *
   * <p>Method under test: {@link BaseValueHandler#getValueContentType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String BaseValueHandler.getValueContentType(DBSTypedObject)"})
  public void testGetValueContentType() {
    // Arrange, Act and Assert
    assertEquals(
        "text/plain",
        DefaultValueHandler.INSTANCE.getValueContentType(SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link BaseValueHandler#createNewValueObject(DBCSession, DBSTypedObject)}.
   *
   * <p>Method under test: {@link BaseValueHandler#createNewValueObject(DBCSession, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.lang.Object BaseValueHandler.createNewValueObject(DBCSession, DBSTypedObject)"
  })
  public void testCreateNewValueObject() throws DBCException {
    // Arrange, Act and Assert
    assertNull(
        DefaultValueHandler.INSTANCE.createNewValueObject(
            mock(DBCSession.class), SimpleTypedObject.DEFAULT_TYPE));
  }

  /**
   * Test {@link BaseValueHandler#getSupportedOperators(DBSTypedObject)}.
   *
   * <p>Method under test: {@link BaseValueHandler#getSupportedOperators(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCLogicalOperator[] BaseValueHandler.getSupportedOperators(DBSTypedObject)"})
  public void testGetSupportedOperators() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new DBCLogicalOperator[] {DBCLogicalOperator.EQUALS, DBCLogicalOperator.NOT_EQUALS},
        DefaultValueHandler.INSTANCE.getSupportedOperators(SimpleTypedObject.DEFAULT_TYPE));
  }
}
