package com.github.kydzombie.stapitest.item.tool;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.util.Vec3i;
import net.modificationstation.stationapi.api.registry.Identifier;

import java.util.ArrayList;

public class Chainsaw extends ElectricTool {
    public static int veinMineCap = 50;

    public Chainsaw(Identifier identifier) {
        super(identifier);
    }

    @Override
    public boolean postMine(ItemInstance saw, int blockId, int x, int y, int z, Living player) {
        if (!player.method_1373() && blockId == BlockBase.LOG.id) {
            VeinMine(saw, blockId, x, y, z, player);
        }
        return super.postMine(saw, blockId, x, y, z, player);
    }

    private ArrayList<Vec3i> checkNearbyBlocks(Level level, int blockId, Vec3i pos) {
        ArrayList<Vec3i> foundBlocks = new ArrayList<>();
        for (int x = pos.x - 1; x <= pos.x + 1; x++) {
            for (int y = pos.y; y <= pos.y + 1; y++) {
                for (int z = pos.z - 1; z <= pos.z + 1; z++) {
                    if (level.getTileId(x, y, z) == blockId) {
                        foundBlocks.add(new Vec3i(x, y, z));
                    }
                }
            }
        }

        return foundBlocks;
    }

    private void VeinMine(ItemInstance saw, int blockId, int x, int y, int z, Living player) {
        Level level = player.level;

        // player.method_1373() = player.isSneaking()

        ArrayList<Vec3i> blocksToCheck = new ArrayList<>();
        ArrayList<Vec3i> blocksChecked = new ArrayList<>();
        blocksToCheck.add(new Vec3i(x, y, z));

        ArrayList<Vec3i> check;
        while (blocksToCheck.size() > 0 && blocksChecked.size() < veinMineCap) {

            check = checkNearbyBlocks(level, blockId, blocksToCheck.get(0));

            blocksChecked.add(blocksToCheck.get(0));
            blocksToCheck.removeAll(blocksChecked);
            check.removeAll(blocksChecked);

            blocksToCheck.addAll(check);
        }

        int size = blocksChecked.size();

        if (size > veinMineCap) {
            blocksChecked.subList(veinMineCap, size).clear();
        }

        blocksChecked.remove(0);

        blocksChecked.forEach((pos) -> {
            if (consume(saw, 5, true) == 5) {
                BlockBase block = BlockBase.BY_ID[level.getTileId(pos.x, pos.y, pos.z)];
                int meta = level.getTileMeta(pos.x, pos.y, pos.z);
                level.setTile(pos.x, pos.y, pos.z, 0);
                block.afterBreak(level, (PlayerBase) player,  pos.x, pos.y, pos.z, meta);
                applyDamage(saw, 1, player);
            }
        });

    }
}
