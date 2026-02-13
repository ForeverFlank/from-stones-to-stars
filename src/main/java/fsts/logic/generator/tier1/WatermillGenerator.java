package fsts.logic.generator.tier1;

import fsts.logic.generator.GeneratorDefinition;
import fsts.math.BigNum;

public final class WatermillGenerator extends GeneratorDefinition {

    private static final String NAME = "Watermill";
    private static final BigNum BASE_COST = new BigNum(100_000);
    private static final BigNum BASE_GENERATION = new BigNum(5_000);
    private static final BigNum COST_SCALING = new BigNum(1.25);

    public WatermillGenerator() {
        super(NAME, BASE_COST, BASE_GENERATION, COST_SCALING);
    }
}