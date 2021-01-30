import pt.isel.canvas.*

data class Position(val x: Int, val y : Int)

data class Player(val position: Position)

const val PLAYER_COLOR = RED
const val VELOCITY = 1

fun Player.move(dir : Char) = copy(position = position.change(dir))

fun Position.change(dir: Char) =
    when(dir){
        UP -> copy(y = y - VELOCITY)
        LEFT -> copy(x = x - VELOCITY)
        DOWN -> copy(y = y + VELOCITY)
        else -> copy(x = x + VELOCITY)
    }

fun Position.trueX() = x * TILE_SIDE
fun Position.trueY() = y * TILE_SIDE


fun Canvas.drawPlayer(player: Player){
    drawRectTile(player.position, PLAYER_COLOR)
}