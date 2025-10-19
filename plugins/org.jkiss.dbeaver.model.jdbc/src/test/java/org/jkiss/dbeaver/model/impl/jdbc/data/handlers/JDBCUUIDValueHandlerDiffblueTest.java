package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.UUID;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCUUIDValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCUUIDValueHandler#getValueObjectType(DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given {@link JDBCUUIDValueHandler} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@link UUID}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUUIDValueHandler#getValueObjectType(DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class JDBCUUIDValueHandler.getValueObjectType(DBSTypedObject)"})
  public void testGetValueObjectType_givenJDBCUUIDValueHandler_whenNull_thenReturnUuid() {
    // Arrange and Act
    Class<UUID> actualValueObjectType = new JDBCUUIDValueHandler().getValueObjectType(null);

    // Assert
    Class<UUID> expectedValueObjectType = UUID.class;
    assertEquals(expectedValueObjectType, actualValueObjectType);
  }

  /**
   * Test new {@link JDBCUUIDValueHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link JDBCUUIDValueHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCUUIDValueHandler.<init>()"})
  public void testNewJDBCUUIDValueHandler() {
    // Arrange, Act and Assert
    assertNull(new JDBCUUIDValueHandler().getComparator());
  }
}
