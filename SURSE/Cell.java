public class Cell {
    int Ox, Oy;
    boolean StateOfCell;
    enum TypeCell {
        EMPTY,
        ENEMY,
        SHOP,
        FINISH
    }
    TypeCell type;
    CellElement cell;
    public Cell(int Ox, int Oy, boolean StateOfCell, TypeCell type, CellElement cell) {
        this.Ox = Ox;
        this.Oy = Oy;
        this.StateOfCell = StateOfCell;
        this.type = type;
        this.cell = cell;
    }
}
