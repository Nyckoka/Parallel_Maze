import pt.isel.canvas.*

data class Position(val x: Int, val y : Int)
data class Velocity(val dx: Int, val dy : Int)

data class Player(val position: Position)

const val PLAYER_COLOR = RED
const val PLAYER_VELOCITY = 1

fun Player.move(dir : Char) = copy(position = position.change(dir, PLAYER_VELOCITY))

fun Position.change(dir: Char, velocity: Int) =
    when(dir){
        UP -> copy(y = y - velocity)
        LEFT -> copy(x = x - velocity)
        DOWN -> copy(y = y + velocity)
        else -> copy(x = x + velocity)
    }

fun Player.collidesWithMaze(maze: Maze) = maze.blocks.any { position == it.position }
fun Player.inBounds() = position.x in GRID_X && position.y in GRID_Y

fun Player.collidesWithEnemy(enemy: Enemy){
    val collides = (position.trueX() + TILE_SIDE >= enemy.position.x &&
            position.trueX() <= enemy.position.x + ENEMY_WIDTH &&
            position.trueY() + TILE_SIDE >= enemy.position.y && position.trueY() <= enemy.position.y + ENEMY_HEIGHT)

    if(collides) println("Collides!!!")
}

operator fun Position.plus(velocity: Velocity) = Position(x + velocity.dx, y + velocity.dy)

fun Position.trueX() = x * TILE_SIDE
fun Position.trueY() = y * TILE_SIDE


fun Canvas.drawPlayer(player: Player){
    drawRectTile(player.position, PLAYER_COLOR)
}