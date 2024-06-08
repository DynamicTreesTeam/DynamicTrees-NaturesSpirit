package xueluoanping.dtnatures_spirit.systems.cell;

import com.ferreusveritas.dynamictrees.api.cell.Cell;
import com.ferreusveritas.dynamictrees.api.cell.CellKit;
import com.ferreusveritas.dynamictrees.api.cell.CellNull;
import com.ferreusveritas.dynamictrees.api.cell.CellSolver;
import com.ferreusveritas.dynamictrees.cell.CellKits;
import com.ferreusveritas.dynamictrees.cell.LeafClusters;
import com.ferreusveritas.dynamictrees.cell.MatrixCell;
import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class SingleCellKit extends CellKit {
    private final Cell acaciaBranch = new Cell() {
        final int[] map = new int[]{0, 3, 5, 5, 5, 5};

        public int getValue() {
            return 5;
        }

        public int getValueFromSide(Direction side) {
            return this.map[side.ordinal()];
        }
    };
    private final Cell[] acaciaLeafCells;
    private final CellKits.BasicSolver acaciaSolver;

    {
        this.acaciaLeafCells = new Cell[]{CellNull.NULL_CELL,
                new SingleCell(1),
                new SingleCell(2),
                new SingleCell(3),
                new SingleCell(4),
                new SingleCell(5),
                new SingleCell(6),
                new SingleCell(7)};
        this.acaciaSolver = new CellKits.BasicSolver(new short[]{1300, 1059, 1042, 786, 529});
    }

    public SingleCellKit(ResourceLocation registryName) {
        super(registryName);
    }

    public Cell getCellForLeaves(int hydro) {
        return this.acaciaLeafCells[hydro];
    }

    public Cell getCellForBranch(int radius, int meta) {
        return radius == 1 ? this.acaciaBranch : CellNull.NULL_CELL;
    }

    public SimpleVoxmap getLeafCluster() {
        return LeafClusters.ACACIA;
    }

    public CellSolver getCellSolver() {
        return this.acaciaSolver;
    }

    public int getDefaultHydration() {
        return 4;
    }

    public static class SingleCell extends MatrixCell {

        public SingleCell(int value) {
            super(value, valMap);
        }

        static final byte[] valMap = {
                0, 0, 0, 0, 0, 0, 0, 0, // D Maps * -> 0
                0, 0, 0, 3, 3, 0, 0, 0, // U Maps 3 -> 3, 4 -> 3, * -> 0
                0, 0, 0, 0, 0, 0, 0, 0, // N Maps * -> *
                0, 0, 0, 0, 0, 0, 0, 0, // S Maps * -> *
                0, 0, 0, 0, 0, 0, 0, 0, // W Maps * -> *
                0, 0, 0, 0, 0, 0, 0, 0  // E Maps * -> *
        };

    }

}
