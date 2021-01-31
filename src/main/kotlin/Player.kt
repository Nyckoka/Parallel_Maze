import pt.isel.canvas.*

data class Player(val position: Position)

const val PLAYER_COLOR = RED
const val PLAYER_VELOCITY = 1

fun Player.move(dir : Char) = copy(position = position.change(dir, PLAYER_VELOCITY))


fun Player.collidesWithMaze(maze: Maze) = maze.blocks.any { position == it.position }
fun Player.collidesWithFinish(finishBlock: Block) = position == finishBlock.position
fun Player.inBounds() = position.x in GRID_X && position.y in GRID_Y

fun Player.collidesWithEnemy(enemy: Enemy) : Boolean{
    position.inPixels().apply {
        val collides = x + TILE_SIDE >= enemy.position.x &&
                       x <= enemy.position.x + ENEMY_WIDTH &&
                       y + TILE_SIDE >= enemy.position.y &&
                       y <= enemy.position.y + ENEMY_HEIGHT

        if(collides) println("Collides!!!")

        return collides
    }
}


fun Canvas.drawPlayer(player: Player){
    player.apply { drawRectTile(position, PLAYER_COLOR) }
}