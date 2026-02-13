package fsts.logic.generator.tier1;

import fsts.logic.generator.GeneratorDefinition;
import fsts.math.BigNum;

public final class CampfireGenerator extends GeneratorDefinition {

    private static final String NAME = "Campfire";
    private static final BigNum BASE_COST = new BigNum(10_000);
    private static final BigNum BASE_GENERATION = new BigNum(1_000);
    private static final BigNum COST_SCALING = new BigNum(1.25);

    public CampfireGenerator() {
        super(NAME, BASE_COST, BASE_GENERATION, COST_SCALING);
    }
}