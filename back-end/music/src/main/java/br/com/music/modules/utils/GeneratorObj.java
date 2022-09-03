package br.com.music.modules.utils;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

// Temporary class created to create random objects
public class GeneratorObj {

  private GeneratorObj() {}

  public static final EasyRandom EASY_RANDOM =
      new EasyRandom(new EasyRandomParameters().stringLengthRange(1, 5).collectionSizeRange(1, 5));
}
