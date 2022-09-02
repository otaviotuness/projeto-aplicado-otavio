package br.com.music.modules.configTest;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class GeneratorObj {

    private GeneratorObj(){}

    public static final EasyRandom EASY_RANDOM =
            new EasyRandom(new EasyRandomParameters().stringLengthRange(1,5).collectionSizeRange(1,5));

}
