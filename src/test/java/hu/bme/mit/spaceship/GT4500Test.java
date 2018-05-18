package hu.bme.mit.spaceship;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @Before
  public void init(){
    primary = mock(TorpedoStore.class);
    secondary = mock(TorpedoStore.class);
    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_FirstEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(0)).fire(1);
    verify(secondary, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_SecondEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_SecondEmpty_FireBoth(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(2)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(true, result1 && result2);
  }

  @Test
  public void fireTorpedo_Single_SecondEmpty_TryFireBoth(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);

    when(primary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);

    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(false, result1 && result2);
  }

  @Test
  public void fireTorpedo_Single_BothEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_Single_BothFull(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_Single_BothFull_Both(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result1 = ship.fireTorpedo(FiringMode.SINGLE);
    boolean result2 = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
    assertEquals(true, result1 && result2);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(1)).fire(1);
    verify(secondary, times(1)).fire(1);
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_All_FirstEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(false);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_SecondEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(false);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(true);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(false, result);
  }

  @Test
  public void fireTorpedo_All_BothEmpty(){
    // Arrange
    when(primary.isEmpty()).thenReturn(true);
    when(secondary.isEmpty()).thenReturn(true);
    when(primary.fire(1)).thenReturn(false);
    when(secondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    verify(primary, times(0)).fire(1);
    verify(secondary, times(0)).fire(1);
    assertEquals(false, result);
  }

}
