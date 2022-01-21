package com.github.kydzombie.stapitest.util.machine.power;

public interface PowerStorage {

    int getCurrentPower();

    int getMaxPower();

    int charge(int chargeAmount, int side, boolean simulate);

    int consume(int consumeAmount, int side, boolean simulate);

}
