package fsts.logic.generator.tier1;

import fsts.logic.generator.GeneratorDefinition;
import fsts.math.BigNum;

public final class HumanPowerGenerator extends GeneratorDefinition {

    private static final String NAME = "Human Power";
    private static final BigNum BASE_COST = new BigNum(500);
    private static final BigNum BASE_GENERATION = new BigNum(100);
    private static final BigNum COST_SCALING = new BigNum(1.25);

    public HumanPowerGenerator() {
        super(NAME, BASE_COST, BASE_GENERATION, COST_SCALING);
    }
}