package manager;

import math.BigNum;

public class EnergyManager {
    private BigNum totalEnergy;

    public EnergyManager(BigNum totalEnergy) {
        this.totalEnergy = totalEnergy;
    }

    public BigNum getTotalEnergy() {
        return totalEnergy;
    }

    public BigNum setEnergy(BigNum value) {
        return BigNum.max(value, new BigNum(0));
    }

    public boolean useEnergy(BigNum amount) {
        if (totalEnergy.cmp(amount) == -1) {
            return false;
        }
        totalEnergy = totalEnergy.sub(amount);
        return true;
    }
}
