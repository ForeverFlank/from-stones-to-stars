package fsts.manager;

import fsts.logic.generator.GeneratorDefinition;
import fsts.logic.generator.GeneratorState;
import fsts.math.BigNum;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    private static Game instance;

    public static double deltaTimeSeconds = 0.05;

    public final EnergyManager energyManager;
    public final GeneratorManager generatorManager;
    public final ResearchManager researchManager;
    public final TimeManager timeManager;

    public static void init() {
        instance = new Game();
    }

    public static Game getInstance() {
        return instance;
    }

    private Game() {
        energyManager = new EnergyManager();
        generatorManager = new GeneratorManager();
        researchManager = new ResearchManager();
        timeManager = new TimeManager();

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        long periodMillis = (long) (deltaTimeSeconds * 1000);
        scheduler.scheduleAtFixedRate(this::update, 0, periodMillis, TimeUnit.MILLISECONDS);
    }

    public void update() {
        energyManager.addEnergy(getFinalEnergyGeneration().mul(timeManager.getDeltaTime()));
    }

    public BigNum getTotalEnergyGeneration() {
        BigNum totalEnergy = BigNum.ZERO;

        for (GeneratorState generator : generatorManager.generatorStates) {
            GeneratorDefinition definition = generator.definition;
            BigNum count = generator.getCount();
            BigNum energyGenerated = definition.baseGeneration.mul(count);

            totalEnergy = totalEnergy.add(energyGenerated);
        }
        return totalEnergy;
    }

    public BigNum getFinalEnergyGeneration() {
        BigNum totalEnergy = getTotalEnergyGeneration();
        return totalEnergy.mul(1.0 - researchManager.getResearchFraction());
    }

    public BigNum getFinalResearchGeneration() {
        BigNum totalEnergy = getTotalEnergyGeneration();
        BigNum usedEnergy = totalEnergy.mul(researchManager.getResearchFraction());
        return usedEnergy.mul(researchManager.getResearchPerEnergy());
    }

    // TODO: (..., BigNum amount) param
    public void buyGenerator(GeneratorState generatorState) {
        BigNum cost = generatorState.getCost();
        if (energyManager.hasEnoughEnergy(cost)) {
            energyManager.removeEnergy(cost);
            generatorState.addCount(BigNum.ONE);
        }
    }

}
