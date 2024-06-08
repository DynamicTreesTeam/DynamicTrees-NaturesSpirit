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

public class CoconutCellKit extends CellKit {

    private final Cell[] coconutCells = {
            CellNull.NULL_CELL,
            new CoconutCell(1),
            new CoconutCell(2),
            new CoconutCell(3),
            new CoconutCell(4),
            new CoconutCell(5),
            new CoconutCell(6),
            new CoconutCell(7)
    };

    private final CellKits.BasicSolver palmSolver = new CellKits.BasicSolver(new short[]
            {0x0514, 0x0413, 0x0312, 0x0221});

    public CoconutCellKit(ResourceLocation registryName) {
        super(registryName);
    }

    private final Cell coconutBranch = new Cell() {
        @Override
        public int getValue() {
            return 5;
        }

        @Override
        public int getValueFromSide(Direction side) {
            return side == Direction.UP ? getValue() : 0;
        }

    };

    @Override
    public Cell getCellForLeaves(int hydro) {
        return coconutCells[hydro];
    }

    @Override
    public Cell getCellForBranch(int radius, int meta) {
        return radius > 1 ? CellNull.NULL_CELL : coconutBranch;
    }

    @Override
    public SimpleVoxmap getLeafCluster() {
        return LeafClusters.PALM;
    }

    @Override
    public CellSolver getCellSolver() {
        return palmSolver;
    }

    @Override
    public int getDefaultHydration() {
        return 4;
    }

    public static class CoconutCell extends MatrixCell {

        public CoconutCell(int value) {
            super(value, valMap);
        }

        static final byte[] valMap = {
                0, 0, 0, 0, 0, 0, 0, 0, // D Maps * -> 0
                0, 0, 0, 0, 4, 0, 0, 0, // U Maps 4 -> 4, * -> 0
                0, 1, 2, 3, 0, 0, 0, 0, // N Maps 4 -> 0, * -> *
                0, 1, 2, 3, 0, 0, 0, 0, // S Maps 4 -> 0, * -> *
                0, 1, 2, 3, 0, 0, 0, 0, // W Maps 4 -> 0, * -> *
                0, 1, 2, 3, 0, 0, 0, 0  // E Maps 4 -> 0, * -> *
        };

    }

}
