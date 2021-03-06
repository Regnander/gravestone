package de.maxhenkel.gravestone.entity;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.player.RemoteClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class DummyPlayer extends RemoteClientPlayerEntity {

    private final byte model;

    public DummyPlayer(ClientWorld world, GameProfile gameProfile, NonNullList<ItemStack> equipment, byte model) {
        super(world, gameProfile);
        this.model = model;
        for (EquipmentSlotType type : EquipmentSlotType.values()) {
            setItemStackToSlot(type, equipment.get(type.ordinal()));
        }
        recalculateSize();
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public EntitySize getSize(Pose pose) {
        return new EntitySize(super.getSize(pose).width, Float.MAX_VALUE, true);
    }

    @Override
    public boolean isWearing(PlayerModelPart part) {
        return (model & part.getPartMask()) == part.getPartMask();
    }
}
