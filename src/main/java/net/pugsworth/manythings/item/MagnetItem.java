package net.pugsworth.manythings.item;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pugsworth.manythings.ManyThingsMod;

public class MagnetItem extends Item {

    public static final Predicate<Entity> MAGNET_ENTITY = (entity) -> {
        return entity.isAlive() && (entity instanceof ItemEntity || entity instanceof ExperienceOrbEntity);
    };

    public MagnetItem(Settings item$Settings_1) {
        super(item$Settings_1);

        // this.addPropertyGetter(identifier_1, itemPropertyGetter_1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        // return super.use(world_1, playerEntity_1, hand_1);
        // ManyThingsMod.logger.info("magnet: use");

        Vec3d origin = playerEntity.getPos();

        this.getNearbyItems(world, origin).forEach((entity) -> {

            int range = ManyThingsMod.CONFIG.magnetRange;
            double scalar = 0.5D;

            // Get directional vector
            Vec3d unit = new Vec3d(playerEntity.x - entity.x, playerEntity.y + (double)playerEntity.getStandingEyeHeight() / 2.0D - entity.y, playerEntity.z - entity.z);
            double lengthSqr = unit.lengthSquared();
            double inverseSqr = 1.0D - Math.sqrt(lengthSqr) / (double)range;
            double force = Math.pow(inverseSqr, 2) * scalar;
            unit = unit.normalize().multiply(force);
            entity.addVelocity(unit.x, unit.y, unit.z);

            // ManyThingsMod.logger.info("force: {}, lengthSqr: {}, double_3 {}", force, lengthSqr, inverseSqr);
        });

        return new TypedActionResult<>(ActionResult.PASS, playerEntity.getStackInHand(hand));
    }

    @Override
    public void onStoppedUsing(ItemStack itemStack, World world, LivingEntity livingEntity, int remainderUseTime) {
        ManyThingsMod.logger.info("magnet: onStoppedUsing");
        super.onStoppedUsing(itemStack, world, livingEntity, remainderUseTime);
    }

    @Override
    public int getMaxUseTime(ItemStack itemStack_1) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack itemStack_1) {
        return UseAction.NONE;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    private ArrayList<Entity> getNearbyItems(World world, Vec3d pos)
    {
        ArrayList<Entity> list = new ArrayList<>();

        int range = ManyThingsMod.CONFIG.magnetRange;
        Vec3d vRange = new Vec3d(range, range, range);

        Box box = new Box(pos.subtract(vRange), pos.add(vRange));

        List<Entity> found = world.getEntities(Entity.class, box, MAGNET_ENTITY);

        for (Entity entity : found) {
            list.add(entity);
        }

        return list;
    }
}
