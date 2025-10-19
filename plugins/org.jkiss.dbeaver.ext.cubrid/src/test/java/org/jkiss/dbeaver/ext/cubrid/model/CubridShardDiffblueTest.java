package org.jkiss.dbeaver.ext.cubrid.model;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.cubrid.model.CubridShard.ShardTypeProvider;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CubridShardDiffblueTest {
  /**
   * Test ShardTypeProvider {@link ShardTypeProvider#allowCustomValue()}.
   *
   * <p>Method under test: {@link ShardTypeProvider#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ShardTypeProvider.allowCustomValue()"})
  public void testShardTypeProviderAllowCustomValue() {
    // Arrange, Act and Assert
    assertTrue(new ShardTypeProvider().allowCustomValue());
  }

  /**
   * Test ShardTypeProvider {@link ShardTypeProvider#getPossibleValues(CubridShard)} with {@code
   * CubridShard}.
   *
   * <p>Method under test: {@link ShardTypeProvider#getPossibleValues(CubridShard)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] ShardTypeProvider.getPossibleValues(CubridShard)"})
  public void testShardTypeProviderGetPossibleValuesWithCubridShard() {
    // Arrange and Act
    Object[] actualPossibleValues = new ShardTypeProvider().getPossibleValues(null);

    // Assert
    assertTrue(actualPossibleValues instanceof String[]);
    assertArrayEquals(new String[] {"SHARD ID", "SHARD VAL"}, actualPossibleValues);
  }
}
