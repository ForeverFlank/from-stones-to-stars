package fsts.manager;

import fsts.logic.generator.GeneratorDefinition;
import fsts.logic.generator.GeneratorState;
import fsts.logic.generator.tier1.CampfireGenerator;
import fsts.logic.generator.tier1.HumanPowerGenerator;
import fsts.logic.generator.tier1.WatermillGenerator;

import java.util.*;
import java.util.stream.Collectors;

public class GeneratorManager {

    public final List<GeneratorState> generatorStates;

    public GeneratorManager() {
        GeneratorDefinition[] generatorDefinitions = {
            new HumanPowerGenerator(),
            new CampfireGenerator(),
            new WatermillGenerator(),
        };
        generatorStates = Arrays.stream(generatorDefinitions)
                                .map(GeneratorState::new)
                                .collect(Collectors.toCollection(ArrayList::new));
    }
}
