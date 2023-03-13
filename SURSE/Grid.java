import java.util.ArrayList;

public class Grid extends ArrayList {
    Cell[][] map;
    Integer length;
    Integer width;
    String CurrentCharacter;
    Cell CurrentCell;
    private Grid(Integer length, Integer width, String name, int Ox, int Oy, boolean StateOfCell, Cell.TypeCell type, CellElement cell){
        map = new Cell[5][5];
        this.length = length;
        this.width = width;
        CurrentCharacter = name;
        CurrentCell = new Cell(Ox, Oy, StateOfCell, type, cell);
    }

    public static Grid generateMap() {
        Grid table = new Grid(4,4,"Fighter", 0,0,true,Cell.TypeCell.EMPTY,null);
        Shop shop1 = new Shop();
        Shop shop2 = new Shop();
        Shop shop3 = new Shop();
        Enemy enemy = new Enemy();
        table.map[0][0] = new Cell(0,0,false, Cell.TypeCell.EMPTY,null);
        table.map[0][1] = new Cell(0,1,false, Cell.TypeCell.EMPTY,null);
        table.map[0][2] = new Cell(0,2,false, Cell.TypeCell.EMPTY,null);
        table.map[0][3] = new Cell(0,3,false, Cell.TypeCell.SHOP,shop1);
        table.map[0][4] = new Cell(0,4,false, Cell.TypeCell.EMPTY,null);
        table.map[1][0] = new Cell(1,0,false, Cell.TypeCell.EMPTY,null);
        table.map[1][1] = new Cell(1,1,false, Cell.TypeCell.EMPTY,null);
        table.map[1][2] = new Cell(1,2,false, Cell.TypeCell.EMPTY,null);
        table.map[1][3] = new Cell(1,3,false, Cell.TypeCell.SHOP, shop2);
        table.map[1][4] = new Cell(1,4,false, Cell.TypeCell.EMPTY,null);
        table.map[2][0] = new Cell(2,0,false, Cell.TypeCell.SHOP, shop3);
        table.map[2][1] = new Cell(2,1,false, Cell.TypeCell.EMPTY,null);
        table.map[2][2] = new Cell(2,2,false, Cell.TypeCell.EMPTY,null);
        table.map[2][3] = new Cell(2,3,false, Cell.TypeCell.EMPTY,null);
        table.map[2][4] = new Cell(2,4,false, Cell.TypeCell.EMPTY,null);
        table.map[3][0] = new Cell(3,0,false, Cell.TypeCell.EMPTY,null);
        table.map[3][1] = new Cell(3,1,false, Cell.TypeCell.EMPTY,null);
        table.map[3][2] = new Cell(3,2,false, Cell.TypeCell.EMPTY,null);
        table.map[3][3] = new Cell(3,3,false, Cell.TypeCell.EMPTY,null);
        table.map[3][4] = new Cell(3,4,false, Cell.TypeCell.ENEMY,enemy);
        table.map[4][0] = new Cell(4,0,false, Cell.TypeCell.EMPTY,null);
        table.map[4][1] = new Cell(4,1,false, Cell.TypeCell.EMPTY,null);
        table.map[4][2] = new Cell(4,2,false, Cell.TypeCell.EMPTY,null);
        table.map[4][3] = new Cell(4,3,false, Cell.TypeCell.EMPTY,null);
        table.map[4][4] = new Cell(4,4,false, Cell.TypeCell.FINISH,null);
        return table;
    }
    public void showMap(){
        for(int i = 0; i <= 4; i++){
            for(int j = 0; j <= 4; j++){
                if(CurrentCell.Ox == i && CurrentCell.Oy == j){
                    System.out.print("P");
                }
                if(!map[i][j].StateOfCell && !(CurrentCell.Ox == i && CurrentCell.Oy == j))
                    System.out.print("? ");
                if ((map[i][j].StateOfCell || (CurrentCell.Ox == i && CurrentCell.Oy == j)) ) {
                    if (map[i][j].type == Cell.TypeCell.EMPTY)
                        System.out.print("N ");
                    else if (map[i][j].type == Cell.TypeCell.FINISH)
                        System.out.print("F ");
                    else if (map[i][j].type == Cell.TypeCell.ENEMY)
                        System.out.print("E ");
                    else if (map[i][j].type == Cell.TypeCell.SHOP)
                        System.out.print("S ");
                }

            }
            System.out.print("\n");
        }
    }
    public void goNorth(){
        if((CurrentCell.Ox - 1) <= width && (CurrentCell.Ox - 1) >= 0)
            CurrentCell.Ox--;
        else
            System.out.println("Ati depasit tabla de joc!");
    }
    public void goSouth(){
        if((CurrentCell.Ox + 1) <= width && (CurrentCell.Ox + 1) >= 0)
            CurrentCell.Ox++;
        else
            System.out.println("Ati depasit tabla de joc!");
    }
    public void goEast(){
        if((CurrentCell.Oy - 1) <= length && (CurrentCell.Oy - 1) >= 0)
            CurrentCell.Oy--;
        else
            System.out.println("Ati depasit tabla de joc!");
    }
    public void goWest(){
        if((CurrentCell.Oy + 1) <= length && (CurrentCell.Oy + 1) >= 0)
            CurrentCell.Oy++;
        else
            System.out.println("Ati depasit tabla de joc!");
    }

}
