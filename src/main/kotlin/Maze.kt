import pt.isel.canvas.*
import java.io.File

data class Block(val position: Position)

data class Maze(val blocks : List<Block>)

const val BLOCK_BORDER_THICKNESS = 3

fun Canvas.drawRectTile(position: Position, color: Int, thickness : Int = 0){
    drawRect(position.trueX(), position.trueY(), TILE_SIDE, TILE_SIDE, color, thickness)
}

fun Canvas.drawBlock(block: Block){
    drawRectTile(block.position, BLACK)
    drawRectTile(block.position, WHITE, BLOCK_BORDER_THICKNESS)
}

fun Position.parallel() = copy(x = x + GRID_WIDTH)
fun Position.trueParallel() = copy(x = x + GRID_WIDTH * TILE_SIDE)
fun Block.parallel() = copy(position = position.parallel())

fun Canvas.drawMaze(maze: Maze){
    maze.blocks.forEach { drawBlock(it.parallel()) }
}

fun getMaze(name: String) : Maze{
    val file = File("src/main/resources/Mazes/$name.txt")

    val lines = file.readLines()

    val blocks = mutableListOf<Block>()

    for(i in lines.indices){
        for(j in lines[i].indices){
            if(lines[i][j] == '#'){
                blocks.add(Block(Position(j, i)))
            }
        }
    }

    return Maze(blocks)
}