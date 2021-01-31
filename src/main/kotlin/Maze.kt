import pt.isel.canvas.*
import java.io.File

data class Block(val position: Position, val type : BlockType)

data class Maze(val blocks : List<Block>, val finishBlock: Block)

enum class BlockType(val color: Int){
    Wall(BLACK),
    Finish(GREEN)
}

const val BLOCK_BORDER_THICKNESS = 1

/**
 * Block with parallel position.
 */
fun Block.parallel() = copy(position = position.parallel())

/**
 * Draw a tile, a rectangle that:
 * - has width = height = [TILE_SIDE]
 * - its position is already fixed to the center of the window.
 * - is going to be drawn in its true pixel position
 */
fun Canvas.drawRectTile(position: Position, color: Int, thickness : Int = 0){
    position.fixToWindowCenter().inPixels().apply {
        drawRect(x, y, TILE_SIDE, TILE_SIDE, color, thickness)
    }
}

fun Canvas.drawBlock(block: Block){
    drawRectTile(block.position, block.type.color)
    drawRectTile(block.position, WHITE, BLOCK_BORDER_THICKNESS)
}

fun Canvas.drawMaze(maze: Maze){
    maze.blocks.forEach { drawBlock(it.parallel()) }
    drawBlock(maze.finishBlock.parallel())
}

/**
 * Reads a .txt file and returns a maze.
 */
fun getMaze(name: String) : Maze{
    val file = File("src/main/resources/Mazes/$name.txt")

    val lines = file.readLines()

    val blocks = mutableListOf<Block>()
    var finishBlock = Block(Position(-1, -1), BlockType.Finish)

    for(y in lines.indices){
        for(x in lines[y].indices){
            if(lines[y][x] == '#'){
                blocks.add(Block(Position(x, y), BlockType.Wall))
            }
            if(lines[y][x] == 'F'){
                finishBlock = Block(Position(x, y), BlockType.Finish)
            }
        }
    }
    if(finishBlock == Block(Position(-1, -1), BlockType.Finish)){
        println("Couldn't find finish block in Mazes/$name.txt")
    }

    return Maze(blocks, finishBlock)
}