package exnihilocreatio.registries.types;

import exnihilocreatio.util.BlockInfo;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@EqualsAndHashCode(exclude = {"textureOverride"})
public class Meltable {

    @Getter
    private String fluid;

    @Getter
    @Setter
    private int amount;

    @Getter
    private BlockInfo textureOverride;

    public Meltable(String fluid, int amount) {
        this.fluid = fluid;
        this.amount = amount;
        this.textureOverride = null;
    }

    public Meltable copy() {
        return new Meltable(fluid, amount, textureOverride);
    }

    public Meltable setTextureOverride(BlockInfo textureOverride) {
        this.textureOverride = textureOverride;
        return this;
    }
}
