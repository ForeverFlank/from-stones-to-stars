package fsts.manager;

import fsts.math.BigNum;

public class ResearchManager {

    private BigNum research;
    private BigNum capacity;
    private BigNum researchPerEnergy;
    private double researchFraction;

    public ResearchManager() {
        research = new BigNum(0);
        capacity = new BigNum(1_000);
        researchPerEnergy = new BigNum(0.01);
        researchFraction = 0.0;
    }

    public BigNum getResearch() {
        return research;
    }

    public void setResearch(BigNum research) {
        this.research = BigNum.clamp(research, BigNum.ZERO, capacity);
    }

    public BigNum getCapacity() {
        return capacity;
    }

    public void setCapacity(BigNum capacity) {
        this.capacity = BigNum.max(capacity, BigNum.ZERO);
    }

    public BigNum getResearchPerEnergy() {
        return researchPerEnergy;
    }

    public void setResearchPerEnergy(BigNum researchPerEnergy) {
        this.researchPerEnergy = BigNum.max(researchPerEnergy, BigNum.ZERO);
    }

    public double getResearchFraction() {
        return researchFraction;
    }

    public void setResearchFraction(double researchFraction) {
        this.researchFraction = Math.clamp(researchFraction, 0.0, 1.0);
    }
}
