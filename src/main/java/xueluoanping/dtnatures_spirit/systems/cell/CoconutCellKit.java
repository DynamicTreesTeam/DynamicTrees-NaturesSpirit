package xueluoanping.dtnatures_spirit.systems.cell;


import com.dtteam.dynamictrees.api.cell.Cell;
import com.dtteam.dynamictrees.api.cell.CellKit;
import com.dtteam.dynamictrees.api.cell.CellNull;
import com.dtteam.dynamictrees.api.cell.CellSolver;
import com.dtteam.dynamictrees.api.voxmap.SimpleVoxmap;
import com.dtteam.dynamictrees.systems.cell.CellKits;
import com.dtteam.dynamictrees.systems.cell.LeafClusters;
import com.dtteam.dynamictrees.systems.cell.MatrixCell;
// import com.dtteam.dynamictrees.utility.SimpleVoxmap;
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
